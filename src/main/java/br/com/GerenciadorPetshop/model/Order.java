package br.com.GerenciadorPetshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

<<<<<<< HEAD
=======
import jakarta.validation.constraints.Size;
>>>>>>> f01ba6ec46c93442c77bceab52f70689f7337b0f
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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Valid
    private Client client;

    @ElementCollection
<<<<<<< HEAD
    private List<Long> tarefasId;

    // Lista de instâncias de Tarefa
    @Transient
    private List<Tarefa> tarefas;

    // Lista de instâncias de Product
    @Transient
=======
    @JoinColumn(name = "tarefa_id")
    private List<Long> tarefaId;

    @ElementCollection
    private List<Tarefa> tarefas;

    @ElementCollection
    @JoinColumn(name = "product_id")
    private List<Long> productId;

    @ElementCollection
>>>>>>> f01ba6ec46c93442c77bceab52f70689f7337b0f
    private List<Product> products;

    @ElementCollection
    private List<Long> productsId;

    @PositiveOrZero
    private Double totalPrice;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderDate = LocalDate.now();

    private String staffNotes;


    private String status;

}



