package com.grocery.calculator.service;

import java.util.Map;

public interface PriceService {

  Map<String, Map<String, Double>> getAllPrices();

}
