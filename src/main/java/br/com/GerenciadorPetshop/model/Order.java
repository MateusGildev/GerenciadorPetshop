package br.com.GerenciadorPetshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @Size(min = 0)
    @ElementCollection
    private List<Long> tarefaId;

    @Size(min = 0)
    @ElementCollection
    private List<Long> productId;

    @PositiveOrZero
    private Double totalPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate orderDate;

    private String staffNotes;

    @NotEmpty
    @NotBlank
    private String status;

}

