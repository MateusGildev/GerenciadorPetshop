package br.com.GerenciadorPetshop.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Min(1)
    private Integer quantity;
    private Integer quantityMax;
    private Integer quantityMin;
}
