package com.grocery.calculator.dto;

import lombok.Data;

@Data
public class ItemDTO {
  private String itemName;
  private String type;
  private Integer quantity;
  private Integer oldBreadInDays;
  private Double weight;
  private String vegetableType;
  private String breadType;
}
