package com.samsonmarikwa.OrdersResourceServer.controller;

import com.samsonmarikwa.OrdersResourceServer.entity.OrderRest;
import com.samsonmarikwa.OrdersResourceServer.entity.OrderStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class OrdersController {
   
   // Because we have SpringSecurity dependance in the project, the endpoint will not be accessed without a valid JWT
   @GetMapping("/orders")
   public List<OrderRest> getOrders() {
      List<OrderRest> orders =
            Arrays.asList(
                  new OrderRest(UUID.randomUUID().toString(), "product-id-1", "user-id-1", 1, OrderStatus.NEW),
                  new OrderRest(UUID.randomUUID().toString(), "product-id-2", "user-id-1", 2, OrderStatus.NEW));
      return orders;
   
   }
}
