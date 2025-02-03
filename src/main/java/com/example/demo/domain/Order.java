package com.example.demo.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="orders", uniqueConstraints = @UniqueConstraint(columnNames = "externalOrderId"))
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Identificador vindo do "produto externo A", para evitar duplicação
    @Column(unique=true, nullable=false)
    private String externalOrderId;

    private BigDecimal totalValue; // Soma do valor dos produtos
    private String status;         // Ex.: "PENDING", "CALCULATED", "SENT_TO_B", etc.

    public Order() { }

    public Order(String externalOrderId, BigDecimal totalValue, String status) {
        this.externalOrderId = externalOrderId;
        this.totalValue = totalValue;
        this.status = status;
    }

    // getters e setters usando pattern builder
    public UUID getId() {
        return id;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }
    public Order setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
        return this;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }
    public Order setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public String getStatus() {
        return status;
    }
    public Order setStatus(String status) {
        this.status = status;
        return this;
    }
}
