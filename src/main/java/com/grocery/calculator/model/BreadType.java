package com.grocery.calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum BreadType {


  WHITE_BREAD("White", 1.00),
  BAGUETTE("Baguette", 2.00),
  FOCACCIA("Focaccia", 3.00);

  private final String name;
  private final double pricePerLoaf;

  public static BreadType fromName(String name) {
    for (BreadType type : BreadType.values()) {
      if (type.getName().equalsIgnoreCase(name)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown vegetable type: " + name);
  }

}
