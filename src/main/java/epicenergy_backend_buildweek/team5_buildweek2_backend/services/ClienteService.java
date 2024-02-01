package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Indirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti.NewClienteDTOIdIndirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti.UpdateClienteDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.indirizzo.NewIndirizzoDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private IndirizzoService indirizzoService;
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
    //public create
    //meteo create/save cliente da fare

    public Cliente save(NewClienteDTOIdIndirizzo body){
        Indirizzo indirizzo = indirizzoService.findById(body.idIndirizzo());

        Cliente newCliente = new Cliente();
        newCliente.setRagioneSociale(body.ragioneSociale());
        newCliente.setEmailAziendale(body.emailAziendale());
        newCliente.setIndirizzoSedeOperativa(indirizzo);

        return clienteRepository.save(newCliente);
    }


    public Cliente findByPartitaIvaAndUpdate (UpdateClienteDTO body, UUID partitaIva){

        Cliente found = findByPartitaIva(partitaIva);
        if (body.nomeContatto() != null)
        {found.setNomeContatto(body.nomeContatto());}

        if (body.telefonoContatto() != 0)
        {found.setTelefonoContatto(body.telefonoContatto());}

        if (body.emailContatto() != null)
        {found.setEmailContatto(body.emailContatto());}

        if (body.ragioneSociale() != null)
        {found.setRagioneSociale(body.ragioneSociale());}

        if (body.indirizzoSedeOperativa() != null)
        {found.setIndirizzoSedeOperativa(body.indirizzoSedeOperativa());}

        if (body.indirizzoSedeLegale() != null)
        {found.setIndirizzoSedeLegale(body.indirizzoSedeLegale());}
        return null;
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

}
