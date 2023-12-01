package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientRepository clientRepository;
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }


    @GetMapping(value = "/id/{id}")
    public Client findById(@PathVariable Long id) {
        Client resultado = clientService.findById(id);
        return resultado;
    }

    @GetMapping(value = "/clients")
    public List<Client> findAll() {
        List<Client> resultado = clientRepository.findAll();
        return resultado;
    }

        @GetMapping(value = "/byTipoAnimal/{tipoAnimal}")
    public List<Client> findByTipoAnimal(@PathVariable String tipoAnimal) {
        return clientRepository.findByTipoAnimal(tipoAnimal);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Client> createNewClient(@RequestBody Client client){
        Client newClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }
}
