package com.samsonmarikwa.OrdersResourceServer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderRest {
   
   private String orderId;
   private String productId;
   private String userId;
   private int quantity;
   private OrderStatus orderStatus;
   
}
