package com.grocery.calculator.service.impl;

import com.grocery.calculator.model.*;
import com.grocery.calculator.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


/**
 * Service implementation for processing orders in the grocery store.
 * This service calculates discounts, generates receipts, and handles business logic.
 */
@Service
public class OrderServiceImpl implements OrderService {


  /**
   * Generates a receipt for a list of items.
   * Applies the necessary discounts and returns the formatted receipt.
   *
   * @param items List of items in the order.
   * @return The generated receipt as a String.
   */
  @Override
  public String generateReceipt(List<Item> items) {
    StringBuilder receipt = new StringBuilder("Receipt:\n");
    int itemNumber = 1;

    for (Item item : items) {
      if (item instanceof Beer) {
        Beer beer = (Beer) item;
        double discountedPrice = calculateBeerDiscountedPrice(beer);
        receipt.append(itemNumber++)
                .append(". ")
                .append(beer.getQuantity()).append(" x ").append(beer.getType()).append(" Beers")
                .append(" €").append(String.format("%.2f", discountedPrice))
                .append("\n");
      } else if (item instanceof Bread) {
        Bread bread = (Bread) item;
        double discountedPrice = calculateBreadDiscountedPrice(bread);
        String discountDescription = getBreadDiscountDescription(bread.getOldBreadInDays());
        receipt.append(itemNumber++)
                .append(". ")
                .append(bread.getQuantity()).append(" x ").append(bread.getBreadType().getName()).append(" Bread (").append(discountDescription).append(")")
                .append(" €").append(String.format("%.2f", discountedPrice))
                .append("\n");
      } else if (item instanceof Vegetable) {
        Vegetable vegetable = (Vegetable) item;
        double discountedPrice = calculateVegetableDiscountedPrice(vegetable);
        receipt.append(itemNumber++)
                .append(". ")
                .append(String.format("%.0f", vegetable.getWeight())).append("g ").append(vegetable.getVegetableType().getName())
                .append(" €").append(String.format("%.2f", discountedPrice))
                .append("\n");
      }
    }

    double totalCost = items.stream()
            .mapToDouble(this::calculateTotalPrice)
            .sum();

    receipt.append("Total: €").append(String.format("%.2f", totalCost));
    return receipt.toString();
  }

public double calculateBeerDiscountedPrice(Beer beer) {
  // Calculate the number of full packs of 6 beers
  int packs = beer.getQuantity() / 6;
  // Calculate the number of remaining beers that don't form a full pack
  int remainingBeers = beer.getQuantity() % 6;

  double discount = beer.getType().getDiscountPerPack() * packs;
  double totalPackPrice = discount;
  double remainingPrice = remainingBeers * beer.getType().getPricePerBottle();

  // Return the sum of the discounted pack price and the remaining beers price
  return totalPackPrice + remainingPrice;
}

  public double calculateBreadDiscountedPrice(Bread bread) {
    // Get the price per loaf from the bread type
    double pricePerLoaf = bread.getBreadType().getPricePerLoaf();

    if (bread.getOldBreadInDays() > 6) {
      throw new IllegalArgumentException("Bread older than 6 days cannot be added to orders.");
    } else {
      // Iterate through all possible discounts
      for (BreadDiscount discount : BreadDiscount.values()) {
        // Check if the bread age matches the discount age
        if (bread.getOldBreadInDays() == discount.getAgeInDays()) {
          // Calculate the effective quantity to pay for based on the discount multiplier
          int effectiveQuantity = (int) Math.ceil(bread.getQuantity() / discount.getMultiplier());
          // Return the total price after applying the discount
          return pricePerLoaf * effectiveQuantity;
        }
      }
    }
    // If no discount is applicable, return the total price for the quantity
    return pricePerLoaf * bread.getQuantity();
  }


  public double calculateVegetableDiscountedPrice(Vegetable vegetable) {
    // Get the price per 100 grams from the vegetable type
    double pricePer100g = vegetable.getVegetableType().getPricePer100g();
    // Get the total weight of the vegetables
    double totalWeight = vegetable.getWeight();
    // Calculate the total price without discount
    double totalPrice = (totalWeight / 100) * pricePer100g;

    // Iterate through all possible discounts
    for (VegetableDiscount discount : VegetableDiscount.values()) {
      // Apply the discount if the total weight is less than or equal to the max weight for the discount
      if (totalWeight <= discount.getMaxWeight()) {
        // Return the total price after applying the discount
        double discountedPrice = totalPrice * (1 - discount.getDiscountRate());
        return roundToTwoDecimalPlaces(discountedPrice);
      }
    }
    return roundToTwoDecimalPlaces(totalPrice);

  }

  private double roundToTwoDecimalPlaces(double value) {
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  public double calculateTotalPrice(Item item) {
    if (item instanceof Beer) {
      return calculateBeerDiscountedPrice((Beer) item);
    } else if (item instanceof Bread) {
      return calculateBreadDiscountedPrice((Bread) item);
    } else if (item instanceof Vegetable) {
      return calculateVegetableDiscountedPrice((Vegetable) item);
    } else {
      throw new IllegalArgumentException("Unknown item type: " + item.getClass().getName());
    }
  }

  private String getBreadDiscountDescription(int oldBreadInDays) {
    for (BreadDiscount discount : BreadDiscount.values()) {
      if (oldBreadInDays == discount.getAgeInDays()) {
        return discount.getDescription();
      }
    }
    throw new IllegalArgumentException("Bread older than 6 days cannot be added to orders.");
  }


}
