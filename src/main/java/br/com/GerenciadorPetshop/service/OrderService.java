package br.com.GerenciadorPetshop.service;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.model.Order;
import br.com.GerenciadorPetshop.model.Product;
import br.com.GerenciadorPetshop.model.Tarefa;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.repository.OrderRepository;
import br.com.GerenciadorPetshop.repository.ProductRepository;
import br.com.GerenciadorPetshop.repository.TarefasRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TarefasRepository tarefaRepository;

    @Autowired
    private ClientService clientService;


    public Order createOrder(Order orderData, Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            Order order = new Order();
            order.setClient(client);
            order.setTarefaId(orderData.getTarefaId());
            orderData.getTarefaId();
            order.setProductId(orderData.getProductId());
            order.setTotalPrice(orderData.getTotalPrice());
            order.setOrderDate(orderData.getOrderDate());
            order.setStaffNotes(orderData.getStaffNotes());
            order.setStatus(orderData.getStatus());

            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Cliente de id: " + clientId + " não encontrado. Não é possível criar o pedido.");
        }
    }

    public void deleteOrderById(Long orderId){
        orderRepository.deleteById(orderId);
    }

    @Transactional(readOnly = true)
    public List<Order> findOrderByClientId(Long clientId) {
        List<Order> orderList = orderRepository.findByClientId(clientId);

        if (orderList.isEmpty()) {
            throw new RuntimeException("Não foram encontrados pedidos para o cliente de id: " + clientId);
        }
        return orderList;
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(Long id) {
        Optional<Order> listOrders = orderRepository.findById(id);

        if (listOrders.isPresent()) {
            System.out.println("Ordem de serviço de id:" + id + " encontrado!");
            return listOrders;
        } else {
            throw new RuntimeException("Ordem de serviço de id: " + id + " não encontrado");
        }
    }
}

