package br.com.GerenciadorPetshop.repository;

import br.com.GerenciadorPetshop.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAll();

    Tarefa save(Tarefa tarefa);

    Optional<Tarefa> findById(Long id);

    void deleteById(Long id);
    
}
