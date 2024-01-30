package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture.NewFatturaDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture.FatturaResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteService clienteService;
    private FatturaResponseDTO convertToDTO(Fattura fattura){
        return new FatturaResponseDTO(fattura.getNumero(),fattura.getDataEmissione(),
                fattura.getImporto(), fattura.getCliente().getPartitaIva(),
                fattura.getCliente().getRagioneSociale());
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

    public Fattura findByNumero(long numero){
        return this.fatturaRepository.findById(numero).orElseThrow(()->new NotFoundException(numero));
    }
    public FatturaResponseDTO save(NewFatturaDTO body){
        Cliente found = this.clienteService.findByPartitaIva(body.idCliente());
        Fattura nuovaFattura = new Fattura(body.importo(), found);
        fatturaRepository.save(nuovaFattura);
        return new FatturaResponseDTO(nuovaFattura.getNumero(),
                nuovaFattura.getDataEmissione(),
                nuovaFattura.getImporto(),
                found.getPartitaIva(),
                found.getRagioneSociale());
    }
    public void findByNumeroAndDelete(long numero) {
        Fattura found = this.findByNumero(numero);
        fatturaRepository.delete(found);
    }
}
