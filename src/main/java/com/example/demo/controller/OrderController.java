package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.OrderDTO;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // 1) Endpoint para receber pedido do Produto Externo A
    //    Exemplo de JSON: {
    //       "externalOrderId": "A-123",
    //       "productValues": [12.50, 7.45, 3.10]
    //    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@Valid @RequestBody CreateOrderRequest req) {
        // Processa e retorna o Order que foi salvo
        Order saved = orderService.processOrder(req.getExternalOrderId(),
                req.getProductValues());

        // Converte o domain Order -> OrderDto
        return new OrderDTO(saved.getExternalOrderId(),
                saved.getTotalValue(),
                saved.getStatus());
    }

    // Consulta de pedidos (GET), ex.: para Produto B
    @GetMapping
    public List<OrderDTO> listOrders() {
        return orderService.findAllOrders().stream()
                .map(o -> new OrderDTO(o.getExternalOrderId(),
                        o.getTotalValue(),
                        o.getStatus()))
                .collect(Collectors.toList());
    }

    // Exemplo: marcar como "enviado ao B"
    @PostMapping("/{externalOrderId}/mark-sent")
    public void markAsSent(@PathVariable String externalOrderId) {
        orderService.markAsSentToB(externalOrderId);
    }

}
