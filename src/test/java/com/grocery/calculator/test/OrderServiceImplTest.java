package com.grocery.calculator.test;

import com.grocery.calculator.model.*;
import com.grocery.calculator.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceImplTest {

  private OrderServiceImpl orderService;

  @BeforeEach
  public void setUp() {
    orderService = new OrderServiceImpl();
  }

  @Test
  public void testCalculateBreadDiscountedPrice_NewBread() {
    Bread bread = new Bread();
    bread.setBreadType(BreadType.WHITE_BREAD);
    bread.setOldBreadInDays(0);
    bread.setQuantity(1);

    double expectedPrice = 1.00;
    double actualPrice = orderService.calculateBreadDiscountedPrice(bread);

    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  public void testCalculateBreadDiscountedPrice_ThreeDaysOldBread() {
    Bread bread = new Bread();
    bread.setBreadType(BreadType.BAGUETTE);
    bread.setOldBreadInDays(3);
    bread.setQuantity(2);

    double expectedPrice = 2.00; // Buy 1 take 2 discount
    double actualPrice = orderService.calculateBreadDiscountedPrice(bread);

    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  public void testCalculateBreadDiscountedPrice_SixDaysOldBread() {
    Bread bread = new Bread();
    bread.setBreadType(BreadType.FOCACCIA);
    bread.setOldBreadInDays(6);
    bread.setQuantity(6);

    double expectedPrice = 6.00; // Pay 1 take 3 discount, should pay for 2 loaves
    double actualPrice = orderService.calculateBreadDiscountedPrice(bread);

    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  public void testCalculateBreadDiscountedPrice_BreadTooOld() {
    Bread bread = new Bread();
    bread.setBreadType(BreadType.WHITE_BREAD);
    bread.setOldBreadInDays(7);
    bread.setQuantity(1);

    assertThrows(IllegalArgumentException.class, () -> orderService.calculateBreadDiscountedPrice(bread));
  }

  @Test
  public void testCalculateVegetableDiscountedPrice() {
    Vegetable vegetable = new Vegetable();
    vegetable.setVegetableType(VegetableType.TOMATO);
    vegetable.setWeight(200);
    vegetable.setItemName("Vegetable");

    double expectedPrice = 0.19; // 0.20 - 10% discount

    double actualPrice = orderService.calculateVegetableDiscountedPrice(vegetable);

    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  public void testCalculateBeerDiscountedPrice_FullPacks() {
    Beer beer = new Beer();
    beer.setType(BeerType.BELGIUM);
    beer.setQuantity(6);

    double expectedPrice = 3.00; // Discount for 6-pack
    double actualPrice = orderService.calculateBeerDiscountedPrice(beer);

    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  public void testCalculateBeerDiscountedPrice_RemainingBeers() {
    Beer beer = new Beer();
    beer.setType(BeerType.BELGIUM);
    beer.setQuantity(7);

    double expectedPrice = 3.50; // Discount for 6-pack + 1 remaining beer
    double actualPrice = orderService.calculateBeerDiscountedPrice(beer);

    assertEquals(expectedPrice, actualPrice);
  }

  @Test
  public void testGenerateReceipt() {
    Bread bread = new Bread();
    bread.setItemName("Bread");
    bread.setBreadType(BreadType.FOCACCIA);
    bread.setOldBreadInDays(3);
    bread.setQuantity(3);

    Vegetable vegetable = new Vegetable();
    vegetable.setVegetableType(VegetableType.TOMATO);
    vegetable.setWeight(200);

    Beer beer = new Beer();
    beer.setType(BeerType.GERMAN);
    beer.setQuantity(6);

    List<Item> items = Arrays.asList(bread, vegetable, beer);

    String expectedReceipt = "Receipt:\n" +
            "1. 3 x Focaccia Bread (Buy 1 take 2) €6.00\n" +
            "2. 200g Tomato €0.19\n" +
            "3. 6 x GERMAN Beers €4.00\n" +
            "Total: €10.19";

    String actualReceipt = orderService.generateReceipt(items);

    assertEquals(expectedReceipt, actualReceipt);
  }

}
