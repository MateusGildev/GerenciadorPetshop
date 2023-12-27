package br.com.GerenciadorPetshop.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
=======
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
=======
import org.hibernate.annotations.Comment;
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
import org.springframework.stereotype.Component;

import java.util.Objects;

@Entity
@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, unique = true)
<<<<<<< HEAD
    @NotBlank
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String description;

    @Column(nullable = false)
    @PositiveOrZero
    private Double price;

    @Column(nullable = false)
    @Min(1)
    private Integer quantity;

    @Column(nullable = false)
    private Integer quantityMax;

=======
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer quantityMax;
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
    @Column(nullable = false)
    private Integer quantityMin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", quantityMax=" + quantityMax +
                ", quantityMin=" + quantityMin +
                '}';
    }
}
