package br.com.GerenciadorPetshop.services;

import br.com.GerenciadorPetshop.model.Service;
import br.com.GerenciadorPetshop.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class PetshopServices {

    @Autowired
    private ServicesRepository servicesRepository;

    public List<Service> findAll() {
        List<Service> resultado = servicesRepository.findAll();
        return resultado;
    }

    public Service createService(Service service) {
        Service resultado = servicesRepository.save(service);
        System.out.println("Serviço criado!");
        return resultado;
    }

    @Transactional(readOnly = true)
    public Service findByid(Long id) {
        Optional<Service> resultado = servicesRepository.findById(id);

        if (resultado.isPresent()) {
            System.out.println("Serviço de ID:" + id + " encontrado!");
            return resultado.get();
        } else {
            throw new RuntimeException("Serviço do id " + id + " não encontrado!");
        }
    }

    public void deleteById(Long id) {
        servicesRepository.deleteById(id);
        System.out.println("Serviço de id:" + id + " excluido com sucesso!");
    }

    public Service updateService(Service service) {
        Service newService = servicesRepository.save(service);
        System.out.println("Serviço de id:" + service.getId() + " alterado com sucesso");
        return newService;
    }

}
