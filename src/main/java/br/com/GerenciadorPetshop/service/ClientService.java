package br.com.GerenciadorPetshop.service;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService { //O service da entidade aplica as regras de negocio!

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true) //Só fará leitura dos dados do banco.
    public Client findById(Long id) {
        Optional<Client> resultado = clientRepository.findById(id);

        if (resultado.isPresent()) {
            System.out.println("Cliente do id: " + id + " encontrado!");
            return resultado.get();
        } else {
            throw new RuntimeException("Cliente do id " + id + " não encontrado!");
        }

    }

    @Transactional(readOnly = true)
    public List<Client> findByTipoAnimal(String tipoAnimal) {
        List<Client> resultado = clientRepository.findByTipoAnimal(tipoAnimal);
        return resultado;
    }

    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteById(Long id){
        clientRepository.deleteById(id);

    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteAll(){
        clientRepository.deleteAll();

    }
    @Transactional(readOnly = true)
    public List<Client> findByNome(String nome){
        List<Client> resultado = clientRepository.findByNome(nome);
        return resultado;
    }

    public Long StringToLongCpf(String cpfString) {
        // Remover caracteres não numéricos e converter para Long
        String cpfNumerico = cpfString.replaceAll("[^\\d]", "");
        return Long.parseLong(cpfNumerico);
    }

    public Long StringToLongTelefone(String telefoneString) {
        // Remover caracteres não numéricos e converter para Long
        String telefoneNumerico = telefoneString.replaceAll("[^\\d]", "");
        return Long.parseLong(telefoneNumerico);
    }



}


