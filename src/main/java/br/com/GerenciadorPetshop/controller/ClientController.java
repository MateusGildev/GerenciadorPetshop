package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<Client> createNewClient(Client client){
    System.out.println("Dados recebidos do formulario: "+client.toString());
        System.out.println(client.getNomeAnimal());

        Client newClient = clientService.createClient(client);
        return ResponseEntity.ok("Dados recebidos com sucesso!").status(HttpStatus.CREATED).body(newClient);
    }

    @GetMapping(value = "/findByNome/{nome}")
    public List<Client> findByNome(@PathVariable String nome){
        return clientRepository.findByNome(nome);
    }
}
