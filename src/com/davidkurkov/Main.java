package com.davidkurkov;

import junit.framework.TestCase;

public class Main extends TestCase {
    public static void main(String[] args) {
        testCalculator();
    }

    private static void testCalculator() {
        Calculator calculator = new Calculator();
        assertEquals(206, calculator.calculate("52*4+2-4"));
        assertEquals(8, calculator.calculate("8"));
        assertEquals(3.333333, (Float) calculator.calculate("10/3"), 0.0001);
        assertEquals(17.5, (Float) calculator.calculate("4+3*9/2"), 0.00f);
        assertEquals(-4, calculator.calculate("10-14"));
        assertEquals(-79678, calculator.calculate("244-178*449"));
        assertEquals(1774962, calculator.calculate("24584+17861*98"));
        assertEquals(-400204.25, (Float) calculator.calculate("10*48*54-645*648-6941-1212+15/4+5-4*5"), 0.00f);
        assertEquals(-1, calculator.calculate("*10+11-12"));
        assertEquals(-1, calculator.calculate("10+15-"));
        assertNull(calculator.calculate("244/0"));
    }
}