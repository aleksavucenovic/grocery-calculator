package com.grocery.calculator.model;
import lombok.Data;


@Data
public class Beer extends Item{

  private BeerType type;
  private int quantity;

}
