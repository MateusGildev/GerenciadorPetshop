package br.com.GerenciadorPetshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
<<<<<<< HEAD
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
=======
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
<<<<<<< HEAD
    @Min(1)
=======
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
    private Integer quantity;
    private Integer quantityMax;
    private Integer quantityMin;
}
