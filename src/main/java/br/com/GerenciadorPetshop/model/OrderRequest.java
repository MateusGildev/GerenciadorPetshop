package br.com.GerenciadorPetshop.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Double totalPrice;
    private LocalDate orderDate;
    private String staffNotes;
    private String status;
    private List<Long> serviceIds;
    private List<Long> productIds;
}
