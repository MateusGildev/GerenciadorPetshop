package br.com.GerenciadorPetshop.services;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) //Só fará leitura dos dados do banco.
public class ClientService { //O service da entidade aplica as regras de negocio!

    @Autowired
    private ClientRepository clientRepository;


    public Client findById(Long id) {
        Optional<Client> resultado = clientRepository.findById(id);

        if (resultado.isPresent()) {
            System.out.println("Cliente do id: " + id + " encontrado!");
            return resultado.get();
        } else {
            throw new RuntimeException("Cliente do id " + id + " não encontrado!");
        }

    }


    public List<Client> findByTipoAnimal(String tipoAnimal) {
        List<Client> resultado = clientRepository.findByTipoAnimal(tipoAnimal);
        return resultado;
    }
}


