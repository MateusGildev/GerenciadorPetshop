package br.com.GerenciadorPetshop.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
=======
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "tb_client")
public class Client {
    @Id //java.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
<<<<<<< HEAD
    @NotBlank
    @NotEmpty
=======
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
    private String nome;

    public Client(String nome) {
        this.nome = nome;
    }

<<<<<<< HEAD
    @NotBlank
    @NotEmpty
    private String nomeAnimal;

    @NotBlank
    @NotEmpty
    private String tipoAnimal;

    @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$")
    private String cpf;

    @Column(columnDefinition = "TEXT")
    @NotEmpty
    @NotBlank
    private String endereco;

    @NotEmpty
    @NotBlank
=======
    private String nomeAnimal;


    private String tipoAnimal;

    private String cpf;

    @Column(columnDefinition = "TEXT")
    private String endereco;
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
    private String telefone;


    @Override
    public boolean equals(Object o) {  //esses dois metodos fazem a diferenciação de dois objetos por meio de seu Id
                                        //posso usar a annotation @EqualsAndHashCode para reduzir codigo!
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nomeAnimal='" + nomeAnimal + '\'' +
                ", tipoAnimal='" + tipoAnimal + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
