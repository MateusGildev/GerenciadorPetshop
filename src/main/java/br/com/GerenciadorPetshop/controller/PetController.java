package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Pet;
import br.com.GerenciadorPetshop.repository.PetRepository;
import br.com.GerenciadorPetshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/animal")
public class PetController {

    @Autowired
    PetService petService;
    @Autowired
    PetRepository petRepository;

    @GetMapping(value = "/types")
    public ResponseEntity<List<String>> findAllTypes(){
        List<String> types = petService.findAllDistinctTypes();

        if (types.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(types, HttpStatus.OK);

    }

    @PostMapping(value = "/newPet")
    public ResponseEntity<Pet> createNewPet(@RequestBody Pet pet){
        System.out.println("Dados recebidos: "+pet.toString());

        Pet newPet = petService.createNewPet(pet);
        return ResponseEntity.ok("Dados recebidos com sucesso!").status(HttpStatus.CREATED).body(newPet);
    }
}
