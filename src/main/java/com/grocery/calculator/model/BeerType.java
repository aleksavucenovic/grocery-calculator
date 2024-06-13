package com.grocery.calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BeerType {

  BELGIUM("Belgium", 3.00, 0.50),
  DUTCH("Dutch", 2.00, 0.50),
  GERMAN("German", 4.00, 0.50);

  private final String name;
  private final double discountPerPack;
  private final double pricePerBottle;

}