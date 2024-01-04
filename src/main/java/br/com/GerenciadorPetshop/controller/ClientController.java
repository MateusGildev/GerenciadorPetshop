package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.service.ClientService;
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

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client updateClient = clientService.findById(id);

        if (updateClient != null) {
            updateClient.setNome(client.getNome());
            updateClient.setCpf(client.getCpf());
            updateClient.setEndereco(client.getEndereco());
            updateClient.setTelefone(client.getTelefone());
            updateClient.setNomeAnimal(client.getNomeAnimal());
            updateClient.setTipoAnimal(client.getTipoAnimal());

            Client updatedClient = clientService.updateClient(updateClient);
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/id/{id}")
    public Client findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping(value = "/clients")
    public List<Client> findAll() {
        List<Client> resultado = clientRepository.findAll();
        return resultado;
    }


    @PostMapping(value = "/user")
    public ResponseEntity<Client> createNewClient(@RequestBody Client client) {
        Client newClient = clientService.createClient(client);
        return ResponseEntity.ok("Dados recebidos com sucesso!").status(HttpStatus.CREATED).body(newClient);
    }

    @DeleteMapping(value = "/idDelete/{id}")
    public String deleteClient(@PathVariable Long id) {
        Client client = clientService.findById(id);

        if (client != null) {
            String clientName = client.getNome();

            clientService.deleteById(id);
            return ("Cliente: " + clientName + " excluido com sucesso!!!");
        } else {
            return "Cliente não encontrado...";
        }
    }


}
