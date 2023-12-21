package br.com.GerenciadorPetshop.service;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.model.Order;
import br.com.GerenciadorPetshop.model.Product;
import br.com.GerenciadorPetshop.model.Tarefa;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.repository.OrderRepository;
import br.com.GerenciadorPetshop.repository.ProductRepository;
import br.com.GerenciadorPetshop.repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TarefasRepository servicesRepository;

    @Autowired
    private ClientService clientService;


    public Order createOrder(Order orderData, Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            Order order = new Order();
            order.setClient(client);
            order.setTotalPrice(orderData.getTotalPrice());
            order.setOrderDate(orderData.getOrderDate());
            order.setStaffNotes(orderData.getStaffNotes());
            order.setStatus(orderData.getStatus());

            // Obter e adicionar serviços
            List<Tarefa> services = servicesRepository.findAllById(orderData.getServiceIds());
            order.setServices(services);

            // Obter e adicionar produtos
            List<Product> products = productRepository.findAllById(orderData.getProductIds());
            order.setProducts(products);

            return orderRepository.save(order);
        } else {
            return null;
        }
    }



    public List<Order> findById(Long id) {
        Optional<Order> listOrders = orderRepository.findById(id);
        return listOrders.map(Collections::singletonList).orElse(Collections.emptyList());
    }
}

