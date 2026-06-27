package com.cognizant;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("--- Setting up Calculator instance ---");
    }

    @After
    public void tearDown() {
        calculator = null;
        System.out.println("--- Tearing down Calculator instance ---");
    }

    @Test
    public void testAddPositiveNumbers() {
        int result = calculator.add(3, 5);
        assertEquals("3 + 5 should equal 8", 8, result);
    }

    @Test
    public void testAddNegativeNumbers() {
        int result = calculator.add(-4, -6);
        assertEquals("-4 + -6 should equal -10", -10, result);
    }

    @Test
    public void testAddWithZero() {
        int result = calculator.add(0, 7);
        assertEquals("0 + 7 should equal 7", 7, result);
    }

    @Test
    public void testSubtractPositiveNumbers() {
        int result = calculator.subtract(10, 4);
        assertEquals("10 - 4 should equal 6", 6, result);
    }

    @Test
    public void testSubtractResultingInNegative() {
        int result = calculator.subtract(3, 8);
        assertEquals("3 - 8 should equal -5", -5, result);
    }

    @Test
    public void testMultiplyPositiveNumbers() {
        int result = calculator.multiply(4, 5);
        assertEquals("4 * 5 should equal 20", 20, result);
    }

    @Test
    public void testMultiplyByZero() {
        int result = calculator.multiply(9, 0);
        assertEquals("Any number multiplied by 0 should equal 0", 0, result);
    }

    @Test
    public void testDividePositiveNumbers() {
        double result = calculator.divide(10, 2);
        assertEquals("10 / 2 should equal 5.0", 5.0, result, 0.001);
    }

    @Test
    public void testDivideResultingInDecimal() {
        double result = calculator.divide(7, 2);
        assertEquals("7 / 2 should equal 3.5", 3.5, result, 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroThrowsException() {
        calculator.divide(10, 0);
    }

    @Test
    public void testAssertTrue() {
        assertTrue("5 > 3 should be true", 5 > 3);
    }

    @Test
    public void testAssertFalse() {
        assertFalse("3 > 5 should be false", 3 > 5);
    }

    @Test
    public void testAssertNotNull() {
        assertNotNull("Calculator object should not be null", calculator);
    }
}
