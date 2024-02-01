package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.StatoFattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture.NewFatturaDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture.FatturaResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private StatoFatturaService statoFatturaService;
    private FatturaResponseDTO convertToDTO(Fattura fattura){
        return new FatturaResponseDTO(fattura.getNumero(),fattura.getDataEmissione(),
                fattura.getImporto(), fattura.getCliente().getPartitaIva(),
                fattura.getCliente().getRagioneSociale());
    }
    private Fattura findById(long id){
        return this.fatturaRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public Page<FatturaResponseDTO> findAll(int pageNumber, int size, String orderBy){
        if(size>100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        Page<Fattura> paginaDiFatture = this.fatturaRepository.findAll(pageable);
        List<FatturaResponseDTO> dtoList = new ArrayList<>();
        for (Fattura fattura : paginaDiFatture.getContent()){
            dtoList.add(this.convertToDTO(fattura));
        }
                /*paginaDiFatture.stream()
                .map(this::convertToDTO).toList();*/ // meno efficiente
        return new PageImpl<>(dtoList,pageable,paginaDiFatture.getTotalElements());
    }

    public FatturaResponseDTO findByNumero(long numero){
        return this.convertToDTO(this.findById(numero));
    }
    public FatturaResponseDTO save(NewFatturaDTO body){
        Cliente found = this.clienteService.findByPartitaIva(body.idCliente());
        Fattura nuovaFattura = new Fattura(body.importo(), found);
        found.setDataUltimoContatto(LocalDate.now());
        this.clienteService.updateDataUltimoContatto(body.idCliente(),LocalDate.now());
        fatturaRepository.save(nuovaFattura);
        return new FatturaResponseDTO(nuovaFattura.getNumero(),
                nuovaFattura.getDataEmissione(),
                nuovaFattura.getImporto(),
                found.getPartitaIva(),
                found.getRagioneSociale());
    }
    public void findByNumeroAndDelete(long numero) {
        Fattura found = this.findById(numero);
        fatturaRepository.delete(found);
    }

    public List<Fattura> getFattureByDate(LocalDate date) {
        if(date == null) date = LocalDate.now();
        return fatturaRepository.findByDataEmissione(date);
    }
    public List<Fattura> getFattureByRangeOfDates(LocalDate startDate, LocalDate endDate){
        if(startDate == null) startDate = LocalDate.of(2000,1,1);
        if(endDate == null) endDate = LocalDate.now();
        return this.fatturaRepository.findByRangeDiDate(startDate,endDate);
    }
    public List<Fattura> getFattureByYear(int year){
        return this.fatturaRepository.findByYearOfIssue(year);
    }

    public List<Fattura> getFattureByRangeImporti(Double minImporto, Double maxImporto) {
        if (maxImporto == null){
            if (minImporto == null){
                return fatturaRepository.findAll();
            } else {
                return fatturaRepository.findByImportoBiggerThan(minImporto);
            }
        }
        return fatturaRepository.findByRangeImporti(minImporto, maxImporto);
    }

    public List<Fattura> getFattureByPartitaIvaCliente(UUID pi) {
        Cliente found = this.clienteService.findByPartitaIva(pi);
        return this.fatturaRepository.findByCliente(found);
    }
    public List<Fattura> getFattureByRagioneSocialCliente(String rs){
        Cliente found = this.clienteService.findByRagioneSociale(rs);
        return this.fatturaRepository.findByCliente(found);
    }
    public List<Fattura> getFattureByStato(String stato){
        StatoFattura statoFattura = this.statoFatturaService.findByStato(stato);
        return this.fatturaRepository.findByStato(statoFattura);
    }
}

