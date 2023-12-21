package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Tarefa;
import br.com.GerenciadorPetshop.repository.TarefasRepository;
import br.com.GerenciadorPetshop.service.PetshopServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/service")
public class TarefasController {

    private TarefasRepository tarefasRepository;
    private PetshopServices petshopServices;

    @Autowired
    public TarefasController(PetshopServices petshopServices, TarefasRepository tarefasRepository) {
        this.petshopServices = petshopServices;
        this.tarefasRepository = tarefasRepository;
    }
    @Transactional(readOnly = true)
    @GetMapping(value = "/allServices")
    public List<Tarefa> findAll() {
        List<Tarefa> resultado = petshopServices.findAll();
        return resultado;
    }

    @PostMapping(value = "/createServ")
    public ResponseEntity<Tarefa> createService(@RequestBody Tarefa tarefa) {
        System.out.println("Dados recebidos: " + tarefa.toString());

        Tarefa newTarefa = petshopServices.createService(tarefa);
        return ResponseEntity.ok("Dados recebidos com sucesso!").status(HttpStatus.CREATED).body(newTarefa);
    }

    @GetMapping(value = "/id/{id}")
    public Tarefa findByid(@PathVariable Long id){
        Tarefa resultado = petshopServices.findByid(id);
        return resultado;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id){
        petshopServices.deleteById(id);
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<Tarefa> updateService(@PathVariable Long id, @RequestBody Tarefa tarefa){
        Tarefa updateTarefa = petshopServices.findByid(id);

        if (updateTarefa != null){
            updateTarefa.setDescription(tarefa.getDescription());
            updateTarefa.setPrice(tarefa.getPrice());

            Tarefa updatedTarefa = petshopServices.updateService(updateTarefa);
            return ResponseEntity.ok(updatedTarefa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
