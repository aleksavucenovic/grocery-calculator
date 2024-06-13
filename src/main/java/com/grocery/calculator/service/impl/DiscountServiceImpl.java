package com.grocery.calculator.service.impl;

import com.grocery.calculator.model.BeerType;
import com.grocery.calculator.model.BreadDiscount;
import com.grocery.calculator.model.VegetableDiscount;
import com.grocery.calculator.model.VegetableType;
import com.grocery.calculator.service.DiscountService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

  private List<String> discountRules;

  @PostConstruct
  public void init() {
    discountRules = new ArrayList<>();
    for (BreadDiscount breadDiscount : BreadDiscount.values()) {
      String rule = "Breads " + breadDiscount.getAgeInDays() + " days old - " +
              breadDiscount.getDescription();
      discountRules.add(rule);
    }

    for (VegetableDiscount vegetableDiscount : VegetableDiscount.values()) {
      String rule = "Vegetables up to " + vegetableDiscount.getMaxWeight() + "g - " +
              (int) (vegetableDiscount.getDiscountRate() * 100) + "% discount";
      discountRules.add(rule);
    }

    for (BeerType beerType : BeerType.values()) {
      String beerDiscountRule = String.format("Beers: 6-pack discount - €%.2f for %s",
              beerType.getDiscountPerPack(), beerType.getName());
      discountRules.add(beerDiscountRule);
    }
  }

  public List<String> getDiscountRules() {
    return discountRules;
  }

}
