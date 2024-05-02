package br.com.GerenciadorPetshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestData {
    private Long clientId;
    private List<Long> tarefaId;
    private List<Long> productId;
    private Double totalPrice;
    private String paymentMethod;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate orderDate;
    private String staffNotes;
    private String status;
}