package com.grocery.calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VegetableType {

  TOMATO("Tomato", 0.10),
  POTATO("Potato", 0.20),
  GARLIC("Garlic", 0.30),
  CORN("Corn", 0.40),
  ONION("Onion", 0.50);

  private final String name;
  private final double pricePer100g;

  public static VegetableType fromName(String name) {
    for (VegetableType type : VegetableType.values()) {
      if (type.getName().equalsIgnoreCase(name)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown vegetable type: " + name);
  }
}
