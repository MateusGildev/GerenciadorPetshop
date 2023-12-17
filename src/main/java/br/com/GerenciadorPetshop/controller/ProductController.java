package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Product;
import br.com.GerenciadorPetshop.model.ProductDto;
import br.com.GerenciadorPetshop.repository.ProductRepository;
import br.com.GerenciadorPetshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;


    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping(value = "/allProducts")
    @Transactional
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/id/{id}")
    @Transactional
    public Product findById(Long id) {
        Product resultado = productService.findById(id);
        return resultado;
    }

    @PostMapping(value = "/newProduct")
    @Transactional
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        System.out.println("Dados recebidos: " + product.toString());

        Product newProduct = productService.save(product);
        return ResponseEntity.ok("Dados recebidos com sucesso!").status(HttpStatus.CREATED).body(newProduct);
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    @Transactional
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping(value = "/edit/{id}")
    @Transactional
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updateProduct = productService.findById(id);

        if (updateProduct != null) {
            updateProduct.setName(product.getName());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setQuantity(product.getQuantity());

            Product updatedProduct = productService.updateProduct(updateProduct);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/vender/{id}")
    public ResponseEntity<String> venderProduto(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Integer quantity = productDto.getQuantity();

        if (quantity != null) {
            boolean vendaRealizada = productService.realizarVenda(id, quantity);
            if (vendaRealizada) {
                return ResponseEntity.ok("Venda realizada com sucesso!");
            } else {
                return ResponseEntity.badRequest().body("Não foi possível realizar a venda do produto de ID: " + id);
            }
        }
        return ResponseEntity.badRequest().body("Quanitdade do produto esta vazia, impossivel realizar a venda");
    }


    @PostMapping("/comprar/{id}")
    public ResponseEntity<String> comprarProduto(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Integer quantity = productDto.getQuantity();

        if (quantity != null) {
            boolean compraRealizada = productService.realizarCompra(id, quantity);
            if (compraRealizada) {
                return ResponseEntity.ok("Compra realizada com sucesso!");
            } else {
                return ResponseEntity.badRequest().body("Não foi possível realizar a compra do produto de ID: " + id);
            }
        } else {
            return ResponseEntity.badRequest().body("Parâmetro 'quantity' ausente ou inválido no corpo da requisição.");
        }
    }
}
