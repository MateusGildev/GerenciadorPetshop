package br.com.GerenciadorPetshop.service;

import br.com.GerenciadorPetshop.model.Client;
import br.com.GerenciadorPetshop.model.Order;
import br.com.GerenciadorPetshop.model.Tarefa;
import br.com.GerenciadorPetshop.repository.ClientRepository;
import br.com.GerenciadorPetshop.repository.OrderRepository;
import br.com.GerenciadorPetshop.repository.ProductRepository;
import br.com.GerenciadorPetshop.repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    private TarefasRepository tarefaRepository;
    @Autowired
    private TarefasRepository servicesRepository;

    @Autowired
    private ClientService clientService;

    public ResponseEntity<String> updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();

            existingOrder.setClient(updatedOrder.getClient());
            existingOrder.setProductId(updatedOrder.getProductId());
            existingOrder.setTarefaId(updatedOrder.getTarefaId());
            existingOrder.setProducts(updatedOrder.getProducts());
            existingOrder.setTarefas(updatedOrder.getTarefas());
            existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            existingOrder.setStaffNotes(updatedOrder.getStaffNotes());
            existingOrder.setStatus(updatedOrder.getStatus());

            Order savedOrder = orderRepository.save(existingOrder);
            return new ResponseEntity<>("Pedido atualizado com sucesso!", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Ordem com o ID " + orderId + " não encontrada. Não é possível atualizar.", HttpStatus.NOT_FOUND);
        }
    }



    public ResponseEntity<String> createOrder(Order orderData, Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            Optional<Tarefa> tarefaOptional = tarefaRepository.findById(orderData.getId());
            if (tarefaOptional.isPresent()) {
                Order order = new Order();
                order.setClient(client);
                order.setTotalPrice(orderData.getTotalPrice());
                order.setOrderDate(orderData.getOrderDate());
                order.setStaffNotes(orderData.getStaffNotes());
                order.setStatus(orderData.getStatus());

                Order savedOrder = orderRepository.save(order);
                return new ResponseEntity<>("Pedido criado com sucesso!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("A tarefa com o ID:  não foi encontrada. Não é possível criar o pedido.", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Cliente de ID: " + clientId + " não encontrado. Não é possível criar o pedido.", HttpStatus.NOT_FOUND);
        }
    }


    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

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


    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}

