package com.grocery.calculator.service.impl;

import com.grocery.calculator.model.BeerType;
import com.grocery.calculator.model.BreadType;
import com.grocery.calculator.model.VegetableType;
import com.grocery.calculator.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PriceServiceImpl implements PriceService {

  public Map<String, Map<String, Double>> getAllPrices() {
    Map<String, Map<String, Double>> allPrices = new HashMap<>();

    Map<String, Double> beerPrices = new HashMap<>();
    for (BeerType beerType : BeerType.values()) {
      String keyPerBottle = beerType.getName() + " (per bottle)";
      beerPrices.put(keyPerBottle, beerType.getPricePerBottle());

      String keyPerPack = beerType.getName() + " (per 6-pack)";
      double pricePerPack = beerType.getPricePerBottle() * 6;
      beerPrices.put(keyPerPack, pricePerPack);
    }
    allPrices.put("Beer", beerPrices);

    Map<String, Double> vegetablePrices = new HashMap<>();
    for (VegetableType vegetableType : VegetableType.values()) {
      String key = vegetableType.getName() + " (per 100g)";
      vegetablePrices.put(key, vegetableType.getPricePer100g());
    }
    allPrices.put("Vegetable", vegetablePrices);

    Map<String, Double> breadPrices = new HashMap<>();
    for (BreadType breadType : BreadType.values()) {
      String key = breadType.getName() + " (per loaf)";
      breadPrices.put(key, breadType.getPricePerLoaf());
    }
    allPrices.put("Bread", breadPrices);

    return allPrices;
  }

}
