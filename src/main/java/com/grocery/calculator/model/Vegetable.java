package com.grocery.calculator.model;
import lombok.Data;

@Data
public class Vegetable extends Item{

  private double weight;
  private VegetableType vegetableType;

}
