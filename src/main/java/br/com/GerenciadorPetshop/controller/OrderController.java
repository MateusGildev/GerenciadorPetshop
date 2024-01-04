package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.*;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.repository.OrderRepository;
import br.com.GerenciadorPetshop.repository.ProductRepository;
import br.com.GerenciadorPetshop.repository.TarefasRepository;
import br.com.GerenciadorPetshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TarefasRepository tarefasRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientRepository clientRepository;

    public OrderController(OrderRepository orderRepository, OrderService orderService, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.clientRepository = clientRepository;
    }

    @GetMapping(value = "/findOrderById/{id}")
    @Transactional
    public Order findById(@PathVariable Long id){
        Optional<Order> orderOptional = orderService.findById(id);

        if (orderOptional.isPresent()){
            return orderOptional.get();
        } else {
            throw new RuntimeException("Ordem de id: "+id+" não encontrado!");
        }

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

    @PostMapping("/createOrder/{clientId}")
    public ResponseEntity<Object> createOrder(@RequestBody(required = true) OrderRequestData orderData, @PathVariable Long clientId) {
        try {
            Optional<Client> clientOptional = clientRepository.findById(clientId);
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();

                // Criando um novo pedido
                Order order = new Order();
                order.setClient(client);
                order.setTotalPrice(orderData.getTotalPrice());
                order.setOrderDate(orderData.getOrderDate());
                order.setStaffNotes(orderData.getStaffNotes());
                order.setStatus(orderData.getStatus());

                // Adicionando as tarefas (serviços) ao pedido
                List<Tarefa> tarefas = tarefasRepository.findAllById(orderData.getTarefaId());
                order.setTarefas(tarefas);

                // Adicionando os produtos ao pedido
                List<Product> products = productRepository.findAllById(orderData.getProductId());
                order.setProducts(products);

                Order savedOrder = orderRepository.save(order);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente de ID: " + clientId + " não encontrado. Não é possível criar o pedido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar pedido: " + e.getMessage());
        }
    }




    @DeleteMapping(value = "/deleteOrderById/{orderId}")
    public void deleteById(@PathVariable Long orderId){
        orderService.deleteOrderById(orderId);
    }

    @PutMapping(value = "/updateOrderById/{id}")
    public ResponseEntity<String> updateOrderById(@RequestBody Order order, @PathVariable Long id){
        return orderService.updateOrder(id, order);
    }
}

