package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class CreateOrderRequest {

    @NotBlank(message = "externalOrderId cannot be blank")
    private String externalOrderId;
    @NotNull(message = "productValues cannot be null")
    private List<BigDecimal> productValues;

    public CreateOrderRequest() {}

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public List<BigDecimal> getProductValues() {
        return productValues;
    }

    public void setProductValues(List<BigDecimal> productValues) {
        this.productValues = productValues;
    }
}
