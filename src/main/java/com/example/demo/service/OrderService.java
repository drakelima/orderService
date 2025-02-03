package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order processOrder(String externalOrderId, List<BigDecimal> productValues) {
        // 1) Verificar se esse pedido já existe (para evitar duplicação):
        // Calcula soma
        BigDecimal total = productValues.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Cria a entidade
        Order newOrder = new Order(externalOrderId, total, "CALCULATED");

        // Tenta salvar
        return orderRepository.save(newOrder);
    }

    // Exemplo de método para "consultar" todos (poderia ter paginação, etc.)
    @Transactional(readOnly = true)
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    // Exemplo: se precisarmos "enviar" ao produto B e atualizar status
    @Transactional
    public void markAsSentToB(String externalOrderId) {
        Optional<Order> opt = orderRepository.findByExternalOrderId(externalOrderId);
        opt.ifPresent(order -> {
            order.setStatus("SENT_TO_B");
            orderRepository.save(order);
        });
    }
}
