package com.grocery.calculator.controller;

import com.grocery.calculator.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

  @Autowired
  private PriceService priceService;

  @GetMapping()
  public Map<String, Map<String, Double>> listAllPrices() {
    return priceService.getAllPrices();
  }


}
