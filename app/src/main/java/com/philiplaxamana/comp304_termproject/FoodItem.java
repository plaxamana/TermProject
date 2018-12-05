package com.philiplaxamana.comp304_termproject;

// POJO for food item

public class FoodItem {

    private String name;
    private int calories;

    public FoodItem(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }
}
