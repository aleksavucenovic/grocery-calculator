package com.grocery.calculator.model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VegetableDiscount {

  UP_TO_100G(100, 0.05),
  UP_TO_500G(500, 0.07),
  OVER_500G(9999, 0.10);

  private final int maxWeight;
  private final double discountRate;

}