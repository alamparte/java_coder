package com.invoice.segundaPreEntrega.controller;

import com.invoice.segundaPreEntrega.model.ClientModel;
import com.invoice.segundaPreEntrega.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping(path = "api/client")
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    //post método - crear client
    @PostMapping("/")
    public ResponseEntity<ClientModel> createClient(@RequestBody ClientModel client){
        return new ResponseEntity<>(this.clientService.createClient(client), HttpStatus.CREATED);
    }
    // buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> findById(@PathVariable Integer id){
        // en la variable clientFound se guarda el cliente encontrado
        ClientModel clientFound = clientService.findById(id);
        //en caso de ser null
        if (clientFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado. El número de id "+id+" no existe.");
        }
        // retorno el cliente encontrado
        return new ResponseEntity<>(clientFound, HttpStatus.OK);
    }
    // buscar cliente por número de documento
    @GetMapping("/doc/{docNumber}")
    public ResponseEntity<ClientModel> findByDocNumber(@PathVariable String docNumber) {
        // en la variable clientFound se guarda el cliente encontrado
        ClientModel clientFound = clientService.findByDocNumber(docNumber);
        //en caso de ser null
        if (clientFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado. El número de documento "+docNumber+" no existe.");
        }
        // retorno el cliente encontrado
        return new ResponseEntity<>(clientFound, HttpStatus.OK);
    }
    // obtener la lista de todos los clientes directamente de (path = "api/client")
    @GetMapping
    public ResponseEntity<List<ClientModel>> findAll(){
        return new ResponseEntity<>(this.clientService.findAll(), HttpStatus.OK);
    }
    // editar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClientModel> update(@RequestBody ClientModel clientUpdate, @PathVariable Integer id) {
        // en la variable clientFound se guarda el cliente encontrado
        ClientModel clientFound = clientService.update(clientUpdate, id);
        //en caso de ser null
        if (clientFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado. El número de id "+id+" no existe.");
        }
        // retorno el cliente encontrado
        return new ResponseEntity<>(clientFound, HttpStatus.OK);
    }
    // elimnar un cliente
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        ClientModel clientFound = clientService.findById(id);
        if (clientFound == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado. El número de id "+id+" no existe.");
        }
        this.clientService.delete(id);
        return new ResponseEntity<>("Cliente eliminado correctamente.", HttpStatus.OK);
    }
}
