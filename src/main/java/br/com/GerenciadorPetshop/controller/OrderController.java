package br.com.GerenciadorPetshop.controller;

<<<<<<< HEAD
import br.com.GerenciadorPetshop.model.Client;
=======
<<<<<<< HEAD
>>>>>>> 8000ec5c8b5904f6aa266370ca9e1ed72074390b
import br.com.GerenciadorPetshop.model.Order;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.repository.OrderRepository;
import br.com.GerenciadorPetshop.service.OrderService;
<<<<<<< HEAD
=======
import org.aspectj.weaver.ast.Or;
=======
import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.model.Order;
import br.com.GerenciadorPetshop.model.OrderRequest;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.repository.OrderRepository;
import br.com.GerenciadorPetshop.service.OrderService;
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
>>>>>>> 8000ec5c8b5904f6aa266370ca9e1ed72074390b
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
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

<<<<<<< HEAD
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
<<<<<<< HEAD

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
=======
=======
    @GetMapping(value = "/findByCLient/{id}")
    @Transactional
    public ResponseEntity<List<Order>> findOrderById(@PathVariable Long id) {
        List<Order> resultado = orderService.findById(id);

        if (resultado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/createOrder/{clientId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long clientId, @RequestBody OrderRequest orderRequest) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            Order order = new Order();

            // Setando os atributos do Order com base nos dados recebidos na requisição
            order.setClient(client);
            order.setTotalPrice(orderRequest.getTotalPrice());
            order.setOrderDate(orderRequest.getOrderDate());
            order.setStaffNotes(orderRequest.getStaffNotes());
            order.setStatus(orderRequest.getStatus());
            order.setServiceIds(orderRequest.getServiceIds());
            order.setProductIds(orderRequest.getProductIds());

            // Salvar e retornar a ordem criada
            Order createdOrder = orderRepository.save(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
>>>>>>> 8000ec5c8b5904f6aa266370ca9e1ed72074390b
}

