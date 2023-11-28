package com.invoice.segundaPreEntrega.service;

import com.invoice.segundaPreEntrega.model.ClientModel;
import com.invoice.segundaPreEntrega.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    // crear nuevo cliente
    public ClientModel createClient(ClientModel newClient){
        return this.clientRepository.save(newClient);
    }

    // buscar cliente por ID
    public ClientModel findById(Integer id){
        Optional<ClientModel> client = this.clientRepository.findById(id);
        return client.orElse(null);
    }
    // buscar cliente por número de documento
    public ClientModel findByDocNumber(String docNumber){
        Optional<ClientModel> client = this.clientRepository.findByDocNumber(docNumber);
        return client.orElse(null);
    }
    // obtener la lista de todos los clientes
    public List<ClientModel> findAll(){
        return this.clientRepository.findAll();
    }
    // modificar cliente
    public ClientModel update(ClientModel newClient, Integer id){
        Optional<ClientModel> clientDB = this.clientRepository.findById(id);

        if(clientDB.isEmpty()){
            return null;
        }
        ClientModel updateClient = clientDB.get();
        updateClient.setName( newClient.getName() );
        updateClient.setSurname( newClient.getSurname() );
        updateClient.setDocNumber( newClient.getDocNumber() );
        return this.clientRepository.save(updateClient);
    }
    // eliminar un cliente
    public void delete(Integer id){
        this.clientRepository.deleteById(id);
    }

}
