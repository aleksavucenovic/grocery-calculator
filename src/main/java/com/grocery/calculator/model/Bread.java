package com.grocery.calculator.model;
import lombok.Data;

@Data
public class Bread extends Item{

  private int oldBreadInDays;
  private int quantity;
  private BreadType breadType;

}
