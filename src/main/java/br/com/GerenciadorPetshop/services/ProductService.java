package br.com.GerenciadorPetshop.services;

import br.com.GerenciadorPetshop.model.Product;
import br.com.GerenciadorPetshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        List<Product> resultado = productRepository.findAll();
        return resultado;
    }
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        Optional<Product> resultado = productRepository.findById(id);

        if (resultado.isPresent()) {
            System.out.println("produto do id:" + id + " encontrado");
            return resultado.get();
        } else {

            throw new RuntimeException("Produto de id:" + id + " não encontrado");
        }
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
        System.out.println("Produto de id:" + id + " excluido com sucesso!");
    }

    public Product save(Product product) {
        Product resultado = productRepository.save(product);
        System.out.println("Produto:" + product.getName() + " salvo com sucesso");
        return resultado;
    }

    public Product updateProduct(Product product) {
        Product updateProduct = productRepository.save(product);
        System.out.println("Produto de id: " + product.getId() + " alterado com sucesso!");
        return updateProduct;
    }
}
