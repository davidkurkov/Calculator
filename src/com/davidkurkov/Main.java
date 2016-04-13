package com.davidkurkov;

import junit.framework.TestCase;

public class Main extends TestCase {

    public static void main(String[] args) {
        testCalculator();
    }

    private static void testCalculator() {
        new Calculator("52*4+2-4"); // 206
        new Calculator("8"); // 8
        new Calculator("10/3"); // 3.3333333
        new Calculator("4+3*9/2"); // 17.5
        new Calculator("10-14"); // -4
        new Calculator("244-178*449"); // 79678
        new Calculator("24584+17866112*98"); // 1750903552
//        new Calculator("244/0"); // ArithmeticException, DivisionByZero
//        new Calculator("*10+11-12"); // IllegalArgumentException
//        new Calculator("10+15-"); // IllegalArgumentException
    }

}