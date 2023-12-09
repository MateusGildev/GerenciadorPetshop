package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Service;
import br.com.GerenciadorPetshop.repository.ServicesRepository;
import br.com.GerenciadorPetshop.services.PetshopServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/service")
public class ServicesController {

    private ServicesRepository servicesRepository;
    private PetshopServices petshopServices;

    @Autowired
    public ServicesController(PetshopServices petshopServices, ServicesRepository servicesRepository) {
        this.petshopServices = petshopServices;
        this.servicesRepository = servicesRepository;
    }
    @Transactional(readOnly = true)
    @GetMapping(value = "/allServices")
    public List<Service> findAll() {
        List<Service> resultado = petshopServices.findAll();
        return resultado;
    }

    @PostMapping(value = "/createServ")
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        System.out.println("Dados recebidos: " + service.toString());

        Service newService = petshopServices.createService(service);
        return ResponseEntity.ok("Dados recebidos com sucesso!").status(HttpStatus.CREATED).body(newService);
    }

    @GetMapping(value = "/id/{id}")
    public Service findByid(@PathVariable Long id){
        Service resultado = petshopServices.findByid(id);
        return resultado;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id){
        petshopServices.deleteById(id);
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service service){
        Service updateService = petshopServices.findByid(id);

        if (updateService != null){
            updateService.setDescription(service.getDescription());
            updateService.setPrice(service.getPrice());

            Service updatedService = petshopServices.updateService(updateService);
            return ResponseEntity.ok(updatedService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
