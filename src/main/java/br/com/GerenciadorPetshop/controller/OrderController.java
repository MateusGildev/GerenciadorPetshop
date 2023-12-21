package br.com.GerenciadorPetshop.controller;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.model.Order;
import br.com.GerenciadorPetshop.model.OrderRequest;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.repository.OrderRepository;
import br.com.GerenciadorPetshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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


}

