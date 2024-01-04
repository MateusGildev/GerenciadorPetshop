package br.com.GerenciadorPetshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotBlank
    private String tipoAnimal;

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", tipoAnimal='" + tipoAnimal + '\'' +
                '}';
    }
}
