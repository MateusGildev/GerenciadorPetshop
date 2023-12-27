package br.com.GerenciadorPetshop.repository;

import br.com.GerenciadorPetshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);

    Order save(Order order);
<<<<<<< HEAD

    List<Order> findByClientId(Long clientId);

    void deleteById(Long id);

=======
>>>>>>> 2189bd40a2cd069c46c86aa689abdc4d16d05811
}
