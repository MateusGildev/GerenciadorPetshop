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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    private TarefasRepository tarefasRepository;


    public ResponseEntity<String> updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();

            List<Tarefa> tarefaList = tarefasRepository.findAllById(updatedOrder.getTarefasId());
            List<Product> productList = productRepository.findAllById(updatedOrder.getProductsId());

            if (!tarefaList.isEmpty()) {
                existingOrder.setTarefas(tarefaList);
            } else {
                existingOrder.setTarefas(Collections.emptyList());
            }

            if (!productList.isEmpty()) {
                existingOrder.setProducts(productList);
            } else {
                existingOrder.setProducts(Collections.emptyList());
            }

            existingOrder.setClient(updatedOrder.getClient());
            existingOrder.setTotalPrice(calculateProductsPrice(productList)+calculateTarefasPrice(tarefaList));
            existingOrder.setPaymentMethod(updatedOrder.getPaymentMethod());
            existingOrder.setStaffNotes(updatedOrder.getStaffNotes());
            existingOrder.setStatus(updatedOrder.getStatus());

            orderRepository.save(existingOrder);
            return new ResponseEntity<>("Pedido atualizado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ordem com o ID " + orderId + " não encontrada. Não é possível atualizar.", HttpStatus.NOT_FOUND);
        }
    }

    public Order createOrder(Order orderData, Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();


            List<Long> tarefaIds = orderData.getTarefasId();
            List<Tarefa> tarefaList = tarefasRepository.findAllById(tarefaIds);


            List<Long> productIds = orderData.getProductsId();
            List<Product> productList = productRepository.findAllById(productIds);

            Order order = new Order();
            order.setClient(client);
            order.setTarefasId(tarefaIds);
            order.setProductsId(productIds);
            order.setPaymentMethod(orderData.getPaymentMethod());
            order.setTarefas(tarefaList); // Definir as instâncias de Tarefa na ordem
            order.setProducts(productList); // Definir as instâncias de Product na ordem
            order.setTotalPrice(calculateProductsPrice(productList) + calculateTarefasPrice(tarefaList));
            order.setStaffNotes(orderData.getStaffNotes());
            order.setStatus(orderData.getStatus());
            return orderRepository.save(order);
        } else {
            return null; // Indica que o cliente não foi encontrado
        }
    }

    private Double calculateTarefasPrice(List<Tarefa> tarefaList) {
        double totalPrice = 0.0;
        for (Tarefa tarefa : tarefaList) {
            totalPrice += tarefa.getPrice();
        }
        return totalPrice;
    }

    private Double calculateProductsPrice(List<Product> productList) {
        double totalPrice = 0.0;
        for (Product product : productList) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
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

