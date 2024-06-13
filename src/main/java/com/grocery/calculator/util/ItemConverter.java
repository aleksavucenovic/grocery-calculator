package com.grocery.calculator.util;

import com.grocery.calculator.dto.ItemDTO;
import com.grocery.calculator.model.*;

import java.util.ArrayList;
import java.util.List;

public class ItemConverter {

  public static List<Item> convertToDomainObjects(List<ItemDTO> itemDTOs) {
    List<Item> items = new ArrayList<>();

    for (ItemDTO itemDTO : itemDTOs) {
      switch (itemDTO.getItemName()) {
        case "Beer":
          items.add(convertToBeer(itemDTO));
          break;
        case "Bread":
          items.add(convertToBread(itemDTO));
          break;
        case "Vegetable":
          items.add(convertToVegetable(itemDTO));
          break;
        default:
          throw new IllegalArgumentException("Unknown item type: " + itemDTO.getItemName());
      }
    }

    return items;
  }

  private static Beer convertToBeer(ItemDTO itemDTO) {
    Beer beer = new Beer();
    beer.setItemName(itemDTO.getItemName());
    BeerType beerType = BeerType.valueOf(itemDTO.getType().toUpperCase());
    beer.setType(beerType);
    beer.setQuantity(itemDTO.getQuantity());
    return beer;
  }

  private static Bread convertToBread(ItemDTO itemDTO) {
    Bread bread = new Bread();
    bread.setItemName(itemDTO.getItemName());
    bread.setOldBreadInDays(itemDTO.getOldBreadInDays());
    bread.setQuantity(itemDTO.getQuantity());
    bread.setBreadType(BreadType.fromName(itemDTO.getBreadType()));
    return bread;
  }

  private static Vegetable convertToVegetable(ItemDTO itemDTO) {
    Vegetable vegetable = new Vegetable();
    vegetable.setItemName(itemDTO.getItemName());
    vegetable.setWeight(itemDTO.getWeight());
    vegetable.setVegetableType(VegetableType.fromName(itemDTO.getVegetableType()));
    return vegetable;
  }
}