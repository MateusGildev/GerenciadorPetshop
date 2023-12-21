package br.com.GerenciadorPetshop.service;

import br.com.GerenciadorPetshop.model.Tarefa;
import br.com.GerenciadorPetshop.repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class PetshopServices {

    @Autowired
    private TarefasRepository tarefasRepository;

    public List<Tarefa> findAll() {
        List<Tarefa> resultado = tarefasRepository.findAll();
        return resultado;
    }

    public Tarefa createService(Tarefa tarefa) {
        Tarefa resultado = tarefasRepository.save(tarefa);
        System.out.println("Serviço criado!");
        return resultado;
    }


    public Tarefa findByid(Long id) {
        Optional<Tarefa> resultado = tarefasRepository.findById(id);

        if (resultado.isPresent()) {
            System.out.println("Serviço de ID:" + id + " encontrado!");
            return resultado.get();
        } else {
            throw new RuntimeException("Serviço do id " + id + " não encontrado!");
        }
    }

    public void deleteById(Long id) {
        tarefasRepository.deleteById(id);
        System.out.println("Serviço de id:" + id + " excluido com sucesso!");
    }

    public Tarefa updateService(Tarefa tarefa) {
        Tarefa newTarefa = tarefasRepository.save(tarefa);
        System.out.println("Serviço de id:" + tarefa.getId() + " alterado com sucesso");
        return newTarefa;
    }

}
