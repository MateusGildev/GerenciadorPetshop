package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.model.Order;
import br.com.GerenciadorPetshop.model.Product;
import br.com.GerenciadorPetshop.model.Tarefa;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.repository.OrderRepository;
import br.com.GerenciadorPetshop.repository.ProductRepository;
import br.com.GerenciadorPetshop.repository.TarefasRepository;
import br.com.GerenciadorPetshop.service.ClientService;
import br.com.GerenciadorPetshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderRepository orderRepository;
    @Autowired
    private TarefasRepository tarefasRepository;
    @Autowired
    private ProductRepository productRepository;

    private OrderService orderService;

    private ClientRepository clientRepository;
    private ClientService clientService;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderService orderService, ClientRepository clientRepository, ClientService clientService) {
        this.clientService = clientService;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/findOrderById/{id}")
    @Transactional
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/findOrderByClient/{clientId}")
    public ResponseEntity<List<Order>> findOrderByClientId(@PathVariable Long clientId) {
        try {
            List<Order> orderList = orderService.findOrderByClientId(clientId);
            return ResponseEntity.ok(orderList);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/createOrder/{clientId}")
    public ResponseEntity<Object> createOrder(@RequestBody Order orderData, @PathVariable Long clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna resposta 404 se o cliente não for encontrado
        }

        Client client = optionalClient.get();
        orderData.setClient(client);

        List<Tarefa> tarefas = orderData.getTarefaId().stream()
                .map(tarefaId -> tarefasRepository.findById(tarefaId)
                        .orElse(null))
                .filter(tarefa -> tarefa != null)
                .collect(Collectors.toList());

        orderData.setTarefas(tarefas);

        List<Product> products = orderData.getProductId().stream()
                .map(productId -> productRepository.findById(productId)
                        .orElse(null))
                .filter(product -> product != null)
                .collect(Collectors.toList());

        orderData.setProducts(products);

        orderData.setOrderDate(orderData.getOrderDate());
        orderData.setStaffNotes(orderData.getStaffNotes());
        orderData.setTotalPrice(orderData.getTotalPrice());
        orderData.setProductId(orderData.getProductId());


        Order savedOrder = orderRepository.save(orderData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);

    }


    @GetMapping(value = "/findAll")
    public List<Order> findAllOrder() {
        return orderService.findAll();
    }


    @DeleteMapping(value = "/deleteOrderById/{orderId}")
    public void deleteById(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
    }

    @PutMapping(value = "/updateOrderById/{id}")
    public ResponseEntity<String> updateOrderById(@RequestBody Order order, @PathVariable Long id) {
        return orderService.updateOrder(id, order);
    }
}

