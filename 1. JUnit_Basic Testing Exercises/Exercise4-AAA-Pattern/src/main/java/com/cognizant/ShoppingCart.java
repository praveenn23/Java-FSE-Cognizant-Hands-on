package com.cognizant;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<String> items;
    private double totalPrice;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addItem(String item, double price) {
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        items.add(item);
        totalPrice += price;
    }

    public boolean removeItem(String item, double price) {
        boolean removed = items.remove(item);
        if (removed) {
            totalPrice -= price;
        }
        return removed;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getItemCount() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
        totalPrice = 0.0;
    }

    public double applyDiscount(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        return totalPrice - (totalPrice * discountPercent / 100);
    }
}
