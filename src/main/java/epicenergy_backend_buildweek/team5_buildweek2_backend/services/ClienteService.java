package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Indirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.enums.TipoAzienda;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti.*;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.indirizzo.NewIndirizzoDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ClienteRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private IndirizzoService indirizzoService;
    @Autowired
    private Cloudinary cloudinary;
    public Page<Cliente> findAll(int pageNumber, int size, String orderBy){
        if(size>100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return this.clienteRepository.findAll(pageable);
    }
    public Cliente findByPartitaIva(UUID pi){
        return this.clienteRepository.findById(pi).orElseThrow(()->new NotFoundException(pi));
    }

    public void findByPartitaIvaAndDelete(UUID pi) {
        Cliente found = this.findByPartitaIva(pi);
        clienteRepository.delete(found);
    }

    public Cliente saveWithFullInfo(NewClienteDTO body) {
        Indirizzo in1 = this.indirizzoService.save(body.addressSedeLegale());
        Indirizzo in2 = this.indirizzoService.save(body.addressSedeOperativo());

        Cliente cliente = new Cliente();
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setRagioneSociale(body.ragioneSociale());
        cliente.setEmailAziendale(body.emailAziendale());
        cliente.setPecAziendale(body.pecAziendale());
        cliente.setTelefonoAziendale(body.telefonoAziendale());
        cliente.setUrlLogoAziendale(body.urlLogoAziendale());

        cliente.setIndirizzoSedeLegale(in1);
        cliente.setIndirizzoSedeOperativa(in2);

        String tipo = body.tipoAziendale();
        if(tipo.equalsIgnoreCase("pa")) {
            cliente.setTipo(TipoAzienda.PA);
        } else if (tipo.equalsIgnoreCase("sas")) {
            cliente.setTipo(TipoAzienda.SAS);
        } else if (tipo.equalsIgnoreCase("spa")) {
            cliente.setTipo(TipoAzienda.SPA);
        } else if (tipo.equalsIgnoreCase("srl")) {
            cliente.setTipo(TipoAzienda.SRL);
        }
        cliente.setFatturatoAnnuale(body.fatturatoAnnuale());
        cliente.setDataUltimoContatto(LocalDate.now());

        return clienteRepository.save(cliente);
    }

    public Cliente save(NewClienteDTOIdIndirizzo body){
        Indirizzo indirizzo = indirizzoService.findById(body.idIndirizzo());

        Cliente newCliente = new Cliente();
        newCliente.setRagioneSociale(body.ragioneSociale());
        newCliente.setEmailAziendale(body.emailAziendale());
        newCliente.setIndirizzoSedeOperativa(indirizzo);

        return clienteRepository.save(newCliente);
    }

    public Cliente saveWithAddress(NewClienteDTOIndirizzoCompleto body){
        NewIndirizzoDTO newIndirizzoDTO = new NewIndirizzoDTO(body.via(), body.civico(), body.località(), body.CAP(), body.idcomune());
        Indirizzo indirizzo = this.indirizzoService.save(newIndirizzoDTO);
        Cliente newCliente = new Cliente(body.ragioneSociale(), body.emailAziendale(),indirizzo);
        return clienteRepository.save(newCliente);
    }

    public Cliente updateDataUltimoContatto(UUID piCliente, LocalDate dataUltimoContatto){
        Cliente found = this.findByPartitaIva(piCliente);
        found.setDataUltimoContatto(dataUltimoContatto);
        return this.clienteRepository.save(found);
    }

    public Cliente findByPartitaIvaAndUpdate (UpdateClienteDTO body, UUID partitaIva){

        Cliente found = findByPartitaIva(partitaIva);
        if (body.nomeContatto() != null)
        {found.setNomeContatto(body.nomeContatto());}

        if (body.telefonoContatto() != null)
        {found.setTelefonoContatto(body.telefonoContatto());}

        if (body.emailContatto() != null)
        {found.setEmailContatto(body.emailContatto());}

        if (body.ragioneSociale() != null)
        {found.setRagioneSociale(body.ragioneSociale());}

        if (body.pecAziendale() != null)
        {found.setPecAziendale(body.pecAziendale());}

        if (body.telefonoAziendale() != null)
        {found.setTelefonoAziendale(body.telefonoAziendale());}
        if(body.tipo()!=null){
            if (body.tipo().equalsIgnoreCase("pa")){
                found.setTipo(TipoAzienda.PA);
            } else if (body.tipo().equalsIgnoreCase("sas")) {
                found.setTipo(TipoAzienda.SAS);
            } else if (body.tipo().equalsIgnoreCase("spa")) {
                found.setTipo(TipoAzienda.SPA);
            } else if (body.tipo().equalsIgnoreCase("srl")) {
                found.setTipo(TipoAzienda.SRL);
            }
        }
        if(body.fatturatoAnnuale()!=null){
            found.setFatturatoAnnuale(body.fatturatoAnnuale());
        }
        return this.clienteRepository.save(found);
    }

    public Cliente findByPartitaIvaAndUpdateIndirizzoSedeLegale (NewIndirizzoDTO body, UUID partitaIva){
        Indirizzo newIndirizzo = this.indirizzoService.save(body);
        Cliente clientefound = this.findByPartitaIva(partitaIva);

        clientefound.setIndirizzoSedeLegale(newIndirizzo);
        return this.clienteRepository.save(clientefound);
    }

    public Cliente findByPartitaIvaAndUpdateIndirizzoSedeOperativa (NewIndirizzoDTO body, UUID partitaIva){
        Indirizzo newIndirizzo = this.indirizzoService.save(body);
        Cliente clientefound = this.findByPartitaIva(partitaIva);

        clientefound.setIndirizzoSedeOperativa(newIndirizzo);
        return this.clienteRepository.save(clientefound);
    }
    public Cliente findByRagioneSociale(String ragioneSociale){
        return clienteRepository.findByRagioneSociale(ragioneSociale);
    }

    public List<Cliente> getClienteByFatturatoAnnuale(int fatturato){
        return this.clienteRepository.findByFatturatoAnnuale(fatturato);
    }
    public List<Cliente> getClientiByFatturatoRange(int minFatturato, int maxFatturato){
        return this.clienteRepository.findByRangeImporti(minFatturato,maxFatturato);
    }
    public List<Cliente> getClientiByFatturatoLowerThan(int maxFatturato){
        return this.clienteRepository.findByFatturatoLowerThan(maxFatturato);
    }
    public List<Cliente> getClientiByFatturatoGreaterThan(int minFatturato) {
        return this.clienteRepository.findByFatturatoBiggerThan(minFatturato);
    }

    public List<Cliente> getClienteByDataInserimento(LocalDate date) {
        if(date == null) date = LocalDate.now();
        return clienteRepository.findByDataInserimento(date);
    }

    public List<Cliente> getClienteByUtimoContatto(LocalDate date) {
        return clienteRepository.findByDataUltimoContatto(date);
    }

    public List<Cliente> getClienteByPartRagioneSociale(String ragioneSociale) {
        return clienteRepository.findByPartRagioneSociale(ragioneSociale);
    }
    public Cliente uploadLogoAziendale(MultipartFile file, UUID pi) throws IOException {
        String url = (String) cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        Cliente found = this.findByPartitaIva(pi);
        found.setUrlLogoAziendale(url);
        return this.clienteRepository.save(found);
    }

    public void savedatarunner (Cliente cliente) {
        clienteRepository.save(cliente);
        System.out.println("cliente salvato nel db");
    }

}
