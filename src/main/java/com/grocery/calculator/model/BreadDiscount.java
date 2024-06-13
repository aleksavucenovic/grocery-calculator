package com.grocery.calculator.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BreadDiscount {

  NEW(0, 1.0, "No discount"),
  THREE_DAYS_OLD(3, 2.0, "Buy 1 take 2"),
  SIX_DAYS_OLD(6, 3.0, "Pay 1 take 3");

  private final int ageInDays;
  private final double multiplier;
  private final String description;

}
