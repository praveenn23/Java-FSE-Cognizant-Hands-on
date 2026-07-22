package com.cognizant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingCartTest {

    private ShoppingCart cart;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
        cart.addItem("Laptop", 800.00);
        cart.addItem("Mouse", 25.00);
    }

    @After
    public void tearDown() {
        cart.clear();
        cart = null;
    }

    @Test
    public void testInitialCartHasTwoItems() {
        // Arrange - done in setUp()

        // Act
        int itemCount = cart.getItemCount();

        // Assert
        assertEquals("Cart should have 2 items after setUp()", 2, itemCount);
    }

    @Test
    public void testAddItemUpdatesCountAndTotal() {
        // Arrange
        double keyboardPrice = 45.00;
        double expectedTotal = 825.00 + keyboardPrice;

        // Act
        cart.addItem("Keyboard", keyboardPrice);

        // Assert
        assertEquals("Item count should be 3 after adding keyboard", 3, cart.getItemCount());
        assertEquals("Total price should be $870.00", expectedTotal, cart.getTotalPrice(), 0.001);
    }

    @Test
    public void testRemoveItemUpdatesCartCorrectly() {
        // Arrange - cart already has Laptop + Mouse from setUp()

        // Act
        boolean removed = cart.removeItem("Mouse", 25.00);

        // Assert
        assertTrue("removeItem() should return true for an existing item", removed);
        assertEquals("Item count should be 1 after removing Mouse", 1, cart.getItemCount());
        assertEquals("Total price should be $800.00 after removing Mouse", 800.00, cart.getTotalPrice(), 0.001);
    }

    @Test
    public void testRemoveNonExistentItemReturnsFalse() {
        // Arrange
        int countBefore = cart.getItemCount();
        double totalBefore = cart.getTotalPrice();

        // Act
        boolean removed = cart.removeItem("Monitor", 200.00);

        // Assert
        assertFalse("Removing a non-existent item should return false", removed);
        assertEquals("Item count should be unchanged", countBefore, cart.getItemCount());
        assertEquals("Total price should be unchanged", totalBefore, cart.getTotalPrice(), 0.001);
    }

    @Test
    public void testApplyDiscountReturnsCorrectPrice() {
        // Arrange
        double discountPercent = 10.0;
        double expectedDiscountedTotal = 825.00 - (825.00 * 0.10);

        // Act
        double discountedTotal = cart.applyDiscount(discountPercent);

        // Assert
        assertEquals("10% discount on $825 should give $742.50", expectedDiscountedTotal, discountedTotal, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testApplyInvalidDiscountThrowsException() {
        // Arrange - cart pre-loaded in setUp()

        // Act
        cart.applyDiscount(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullItemThrowsException() {
        // Arrange - cart pre-loaded in setUp()

        // Act
        cart.addItem(null, 10.0);
    }

    @Test
    public void testCartIsNotEmptyAfterSetup() {
        // Arrange - done in setUp()

        // Act
        boolean empty = cart.isEmpty();

        // Assert
        assertFalse("Cart should NOT be empty after setUp()", empty);
    }

    @Test
    public void testClearEmptiesCart() {
        // Arrange - cart has items from setUp()

        // Act
        cart.clear();

        // Assert
        assertTrue("Cart should be empty after clear()", cart.isEmpty());
        assertEquals("Item count should be 0 after clear()", 0, cart.getItemCount());
        assertEquals("Total price should be 0.0 after clear()", 0.0, cart.getTotalPrice(), 0.001);
    }
}
