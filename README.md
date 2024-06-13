# GROCERY Calculator

## Title

This project is a calculator for a grocery store.
It calculates the total cost of an order and prints a receipt.

## Features

- Items can only be breads, vegetables, or beers
- Discounts:
    - Breads:
        - No discount if the bread is one day old or newer
        - Buy 1 take 2 if the bread is 3 days old
        - Pay 1 take 3 if the bread is 6 days old
        - Breads older than 6 days cannot be added to orders
    - Vegetables:
        - 5% discount if total weight is between 0g and 100g
        - 7% discount if total weight is between 100g and 500g
        - 10% discount if total weight is more than 500g
    - Beers:
        - €3.00 discount for each Belgium beer pack (6 beers)
        - €2.00 discount for each Dutch beer pack (6 beers)
        - €4.00 discount for each German beer pack (6 beers)

## Requirements

- Java 17
- Maven

## Run the App

- The application will start on `http://localhost:8080`.

## API Endpoints

- Calculate Order:
    - URL: `/api/orders/calculate_order`
    - Method: `POST`
        - Body:
          [
              {
                  "itemName": "Beer",
                  "type": "BELGIUM",
                  "quantity": 13
              },
              {
                  "itemName": "Beer",
                  "type": "DUTCH",
                  "quantity": 26
              },
              {
                  "itemName": "Bread",
                  "breadType": "White",
                  "oldBreadInDays": 3,
                  "quantity": 3
              },
              {
                  "itemName": "Vegetable",
                  "vegetableType": "Tomato",
                  "weight": 400
              },
              {
                  "itemName": "Vegetable",
                  "vegetableType": "Garlic",
                  "weight": 600
              }
          ]

    - Response:
      Receipt:
        1. 13 x BELGIUM Beers €6.50
        2. 26 x DUTCH Beers €9.00
        3. 3 x White Bread (No discount) €2.00
        4. 400g Tomato €0.37
        5. 600g Garlic €1.62
           = Total: €19.49


- List Current Discount Rules:
    - URL: `/api/discounts/discount_rules`
    - Method: `GET`
        - Response:
          [
              "Breads 0 days old - No discount",
              "Breads 3 days old - Buy 1 take 2",
              "Breads 6 days old - Pay 1 take 3",
              "Vegetables up to 100g - 5% discount",
              "Vegetables up to 500g - 7% discount",
              "Vegetables up to 9999g - 10% discount",
              "Beers: 6-pack discount - €3.00 for Belgium",
              "Beers: 6-pack discount - €2.00 for Dutch",
              "Beers: 6-pack discount - €4.00 for German"
          ]


- List All Prices:
    - URL: `/api/prices`
    - Method: `GET`
      - Response:
        [
            "Vegetable": {
                "Potato (per 100g)": 0.2,
                "Garlic (per 100g)": 0.3,
                "Onion (per 100g)": 0.5,
                "Tomato (per 100g)": 0.1,
                "Corn (per 100g)": 0.4
            },
            "Beer": {
                "Belgium (per 6-pack)": 3.0,
                "German (per 6-pack)": 3.0,
                "Belgium (per bottle)": 0.5,
                "German (per bottle)": 0.5,
                "Dutch (per 6-pack)": 3.0,
                "Dutch (per bottle)": 0.5
            },
            "Bread": {
                "Baguette (per loaf)": 2.0,
                "White (per loaf)": 1.0,
                "Focaccia (per loaf)": 3.0
            }
        ]

        
## Contact

For any questions or issues, please contact aleksavucenovic1@gmail.com :)
