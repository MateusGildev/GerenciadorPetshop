package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.model.Order;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.repository.OrderRepository;
import br.com.GerenciadorPetshop.service.OrderService;
import org.aspectj.weaver.ast.Or;
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
    @Transactional
    public ResponseEntity<List<Order>> findOrderByClientId(@PathVariable Long clientId) {
        try {
            List<Order> orderList = orderService.findOrderByClientId(clientId);
            return ResponseEntity.ok(orderList);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createOrder/{clientId}")
    public ResponseEntity<Order> createOrder(@RequestBody(required = true) Order orderData, @PathVariable Long clientId) {
        try {
            Order createdOrder = orderService.createOrder(orderData, clientId);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Ou outro código de status adequado para tratamento de exceção
        }
    }

    @DeleteMapping(value = "/deleteOrderById/{orderId}")
    public void deleteById(@PathVariable Long orderId){
        orderService.deleteOrderById(orderId);
    }

    @PutMapping(value = "/updateOrderById/{id}")
    public ResponseEntity<Order> updateOrderById(@RequestBody Order order, @PathVariable Long id){
        Optional<Order> orderUpdate = orderService.findById(id);

        if (orderUpdate.isPresent()){
            Order existingOrder = orderUpdate.get();
            Long existingClientId = order.getClient().getId();
            Optional<Client> clientOptional = clientRepository.findById(existingClientId);

            Client client = clientOptional.get();

            existingOrder.setClient(client);
            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setProductId(order.getProductId());
            existingOrder.setTarefaId(order.getTarefaId());
            existingOrder.setTotalPrice(order.getTotalPrice());
            existingOrder.setStaffNotes(order.getStaffNotes());
            existingOrder.setStatus(order.getStatus());

            Order newOrderUpdated = orderService.updateOrder(existingOrder);

            return ResponseEntity.ok(newOrderUpdated);
        }
        return ResponseEntity.notFound().build();
    }
}

