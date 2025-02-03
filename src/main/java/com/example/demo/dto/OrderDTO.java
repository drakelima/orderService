package com.example.demo.dto;

import java.math.BigDecimal;

public class OrderDTO {

    private String externalOrderId;
    private BigDecimal totalValue;
    private String status;

    // Construtor vazio (necessário se usar Jackson e quiser instanciar sem args)
    public OrderDTO() {}

    public OrderDTO(String externalOrderId, BigDecimal totalValue, String status) {
        this.externalOrderId = externalOrderId;
        this.totalValue = totalValue;
        this.status = status;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public OrderDTO setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
        return this;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public OrderDTO setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrderDTO setStatus(String status) {
        this.status = status;
        return this;
    }
}
