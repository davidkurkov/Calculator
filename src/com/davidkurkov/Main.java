package com.davidkurkov;

import junit.framework.TestCase;

public class Main extends TestCase {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        testCalculator(calculator);
        testRobbsCases(calculator);
        testCalculatorAdvanced(calculator);
        testErrorCases(calculator);
    }

    private static void testCalculator(Calculator calculator) {
        assertEquals(206, calculator.calculate("52*4+2-4"));
        assertEquals(8, calculator.calculate("8"));
        assertEquals(3.333333, (Float) calculator.calculate("10/3"), 0.0001);
        assertEquals(17.5, (Float) calculator.calculate("4+3*9/2"), 0.00f);
        assertEquals(-4, calculator.calculate("10-14"));
        assertEquals(-79678, calculator.calculate("244-178*449"));
        assertEquals(1774962, calculator.calculate("24584+17861*98"));
        assertEquals(-400204.25, (Float) calculator.calculate("10*48*54-645*648-6941-1212+15/4+5-4*5"), 0.00f);
    }

    private static void testRobbsCases(Calculator calculator) {
        assertEquals(1, calculator.calculate("2 -1"));
        assertEquals(3, calculator.calculate("2+1"));
        assertEquals(2, calculator.calculate("2"));
        assertEquals(1, calculator.calculate("2 -1"));
        assertEquals(1, calculator.calculate("0+ 1"));
        assertEquals(-1, calculator.calculate("1-2"));
        assertEquals(6, calculator.calculate("2+1+3"));
        assertEquals(9, calculator.calculate("2+1 +2+1+2+1"));
        assertEquals(9, calculator.calculate("2+1++2+1+2+1"));
    }

    private static void testCalculatorAdvanced(Calculator calculator) {
        assertEquals(9, calculator.calculate("5+4"));
        assertEquals(-14, calculator.calculate("-9+-5"));
        assertEquals(-7, calculator.calculate("5+-12"));
        assertEquals(3, calculator.calculate("12-9"));
        assertEquals(-21, calculator.calculate("-12+-9"));
        assertEquals(-20, calculator.calculate("-12-8"));
        assertEquals(40, calculator.calculate("5*8"));
        assertEquals(40, calculator.calculate("-5*-8"));
        assertEquals(-60, calculator.calculate("-5*12"));
        assertEquals(4, calculator.calculate("12/3"));
        assertEquals(2.4, (Float) calculator.calculate("12/5"), 0.01f);
        assertEquals(3, calculator.calculate("-9/-3"));
        assertEquals(2.5, (Float) calculator.calculate("12.5/5"), 0.00f);
        assertEquals(-3.2, (Float) calculator.calculate("16/-5"), 0.01f);
        assertEquals(0, calculator.calculate("0/12"));
    }

    private static void testErrorCases(Calculator calculator) {
        assertEquals(-1, calculator.calculate("*10+11-12"));
        assertEquals(-1, calculator.calculate("10+15-"));
        assertNull(calculator.calculate("244/0"));
    }
}