package br.com.GerenciadorPetshop.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
=======
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "tb_service")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String description;

    @PositiveOrZero
=======
    @Column(columnDefinition = "TEXT")
    private String description;

>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(id, tarefa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
