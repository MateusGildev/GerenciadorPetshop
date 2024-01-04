package br.com.GerenciadorPetshop.repository;

import br.com.GerenciadorPetshop.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAll();

    @Query("SELECT DISTINCT p.tipoAnimal FROM Pet p")
    List<String> findDistinctByTipoAnimal();

    // @Query("SELECT DISTINCT p.tipoAnimal FROM Pet p")

    Pet save(Pet pet);
}
