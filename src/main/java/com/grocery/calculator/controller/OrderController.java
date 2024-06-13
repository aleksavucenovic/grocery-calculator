package com.grocery.calculator.controller;

import com.grocery.calculator.dto.ItemDTO;
import com.grocery.calculator.model.*;
import com.grocery.calculator.service.OrderService;
import com.grocery.calculator.util.ItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping("/calculate_order")
  public String calculateOrderPrice(@RequestBody List<ItemDTO> itemDTOs) {
    List<Item> items = ItemConverter.convertToDomainObjects(itemDTOs);
    return orderService.generateReceipt(items);
  }
}
