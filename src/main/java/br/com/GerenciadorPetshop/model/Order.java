package br.com.GerenciadorPetshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "Order.productsAndTarefas", attributeNodes = {
        @NamedAttributeNode("tarefas"),
        @NamedAttributeNode("products")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Valid
    private Client client;

    @ElementCollection
    private List<Long> tarefasId;

    @ElementCollection
    private List<Long> productsId;

    @OneToMany
    private List<Tarefa> tarefas;

    @OneToMany
    private List<Product> products;


    @PositiveOrZero
    private Double totalPrice;

    private String paymentMethod;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderDate = LocalDate.now();

    private String staffNotes;


    private String status;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", tarefasId=" + tarefasId +
                ", productsId=" + productsId +
                ", tarefas=" + tarefas +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderDate=" + orderDate +
                ", staffNotes='" + staffNotes + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}



