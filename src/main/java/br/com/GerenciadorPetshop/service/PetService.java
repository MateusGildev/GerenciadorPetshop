package br.com.GerenciadorPetshop.service;

import br.com.GerenciadorPetshop.model.Pet;
import br.com.GerenciadorPetshop.repository.PetRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Transactional
    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    @Transactional
    public List<String> findAllDistinctTypes(){
        return petRepository.findDistinctByTipoAnimal();
    }

    public Pet createNewPet(Pet pet){
        return petRepository.save(pet);
    }
}
