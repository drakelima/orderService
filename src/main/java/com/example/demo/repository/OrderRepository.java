package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findByExternalOrderId(String externalOrderId);
}
