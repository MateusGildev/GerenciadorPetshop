package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Order;
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
    public ResponseEntity<Order> createOrder(@RequestBody Order orderData, @PathVariable Long clientId) {
        Order newOrder = orderService.createOrder(orderData, clientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
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



