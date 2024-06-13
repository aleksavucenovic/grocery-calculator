package com.grocery.calculator.service;


import com.grocery.calculator.model.Item;

import java.util.List;

public interface OrderService {

  String generateReceipt(List<Item> items);

}
