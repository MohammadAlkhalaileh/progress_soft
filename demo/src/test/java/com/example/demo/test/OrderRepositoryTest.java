package com.example.demo.test;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void shouldSaveValidOrderSuccessfully() {
        Order order = getDefaultOrder();

        orderRepository.save(order);

        assertTrue(orderRepository.findById(order.getId()).isPresent());
    }

    @Test
    void shouldFailToSaveDuplicateEntries(){
        orderRepository.save(getDefaultOrder());

        assertThrows(DataIntegrityViolationException.class, () -> orderRepository.save(getDefaultOrder()));
    }

    private Order getDefaultOrder() {
        Order order = new Order();

        order.setOrderId("123");
        order.setAmount(BigDecimal.TEN);
        order.setFromCurrency("USD");
        order.setToCurrency("JOD");
        return order;
    }
}
