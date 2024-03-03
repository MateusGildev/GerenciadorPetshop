package br.com.GerenciadorPetshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import jakarta.validation.constraints.Size;
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
    @JoinColumn(name = "tarefa_id")
    private List<Long> tarefaId;

    @ElementCollection
    private List<Tarefa> tarefas;

    @ElementCollection
    @JoinColumn(name = "product_id")
    private List<Long> productId;

    @ElementCollection
    private List<Product> products;

    @PositiveOrZero
    private Double totalPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate orderDate;

    private String staffNotes;


    private String status;

}

