package com.grocery.calculator.controller;

import com.grocery.calculator.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

  @Autowired
  private DiscountService discountService;

  @GetMapping("/discount_rules")
  public List<String> listDiscountRules() {
    return discountService.getDiscountRules();
  }


}
