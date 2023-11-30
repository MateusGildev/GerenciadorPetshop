package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;


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
}
