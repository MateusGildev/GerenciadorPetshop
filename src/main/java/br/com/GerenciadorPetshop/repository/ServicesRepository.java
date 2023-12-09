package br.com.GerenciadorPetshop.repository;

import br.com.GerenciadorPetshop.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicesRepository extends JpaRepository<Service, Long> {

    List<Service> findAll();

    Service save(Service service);

    Optional<Service> findById(Long id);

    void deleteById(Long id);
    
}
