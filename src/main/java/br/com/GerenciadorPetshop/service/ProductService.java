package br.com.GerenciadorPetshop.service;

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

    @Autowired
    private Product product;

    public ProductService(ProductRepository productRepository, Product product) {
        this.productRepository = productRepository;
        this.product = product;
    }

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

    public boolean realizarCompra(Long id, Integer quantity) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            Integer quantityMax = product.getQuantityMax();

            if (product.getQuantity() + quantity <= quantityMax) {
                int newQuantity = product.getQuantity() + quantity;
                product.setQuantity(newQuantity);
                productRepository.save(product);
                System.out.println("Compra realizada com sucesso!");
                return true;
            } else {
                System.out.println("A quantidade de compra desejada ultrapassa a permitida, que é "+product.getQuantityMax());
                return false;
            }
        } else {
            System.out.println("produto de id: "+id+" não encontrado");
            return false;
        }
    }

    public boolean realizarVenda(Long id, Integer quantidade) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            Integer quantityMin = product.getQuantityMin();

            if (product.getQuantity() - quantidade >= quantityMin) {
                int newQuantity = product.getQuantity() - quantidade;
                product.setQuantity(newQuantity);
                productRepository.save(product);
                System.out.println("VENDA REALIZADA COM SUCESSO!");
                return true;

            } else {
                System.out.println("Quantidade insuficiente em estoque para realizar a venda.");
                System.out.println("Compre mais produtos de: " + product.getName());
                return false;
            }
        } else {
            System.out.println("Produto de id: " + id + " não encontrado...");
            return false;
        }
    }
}
