package br.com.GerenciadorPetshop.repository;

import br.com.GerenciadorPetshop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//O ClienteRepository permite que você realize operações com a entidade Cliente sem a necessidade de implementar manualmente esses métodos, pois o Spring Data JPA gera automaticamente a implementação para você.
// A não ser que seu metodo seja especifico!

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
        Optional<Client> findById(Long id);
        List<Client> findAll();

        Client save(Client client);

        List<Client> findByNome(String nome);

        List<Client> findByTipoAnimal(String tipoAnimal);
}
