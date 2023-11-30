package br.com.GerenciadorPetshop.model;

import jakarta.persistence.*;
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
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String nomeAnimal;

    //@Column(columnDefinition = "TEXT")
    private String tipoAnimal;

    private long cpf;

    @Column(columnDefinition = "TEXT")
    private String endereco;
    private Long telefone;


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


}
