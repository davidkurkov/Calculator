package com.davidkurkov;

import java.util.StringTokenizer;

/**
 * Created by david on 4/6/16.
 */
public class Calculator {
    CustomStack stack;

    public Number calculate(String data) {
        stack = new CustomStack();
        if (!isNotValidData(data)) {
            String[] filteredData = splitElements(data);
            populateStack(filteredData);
            float total = countUpStack();
            if (total % 1 == 0) {
                System.out.println(data + " = " + Math.round(total));
                return Math.round(total);
            }
            else {
                System.out.println(data + " = " + total);
                return total;
            }
        }
        else {
            System.out.println("IllegalArgument: Operator was found at beginning or end of input.");
            return -1;
        }
    }

    private boolean isNotValidData(String data) {
        return isOperator(data.substring(0, 1)) || isOperator(data.substring(data.length() - 1, data.length()));
    }

    private boolean isOperator(String s) {
        String operators = "*/+-";
        return operators.contains(s);
    }

    private boolean isNumber(String s) {
        String numbers = "0123456789";
        return numbers.contains(s.substring(0, 1));
    }

    private String[] splitElements (String data) {
        String[] split = new String[data.length()];
        int unUnusedIndex = 0;
        int pointer1 = 0;
        int pointer2 = 0;

        while (pointer2 <= data.length() - 1) {
            if (isNumber(data.substring(pointer2, pointer2+1))) {
                pointer2 += 1;
            }
            else {
                split[unUnusedIndex] = data.substring(pointer1, pointer2);
                unUnusedIndex += 1;
                split[unUnusedIndex] = data.substring(pointer2, pointer2+1);
                unUnusedIndex += 1;
                pointer2 += 1;
                pointer1 = pointer2;

            }
        }
        split[unUnusedIndex] = data.substring(pointer1, pointer2);
        return split;
    }

    private void populateStack(String[] data) {
        int pointer = 0;

        if (data.length == 1) {
            stack.push(Float.parseFloat(data[0]));
        }

        while (pointer < data.length - 1) {
            String element = data[pointer];
            if (element == null) {
                break;
            }
            if (isNumber(element)) {
                stack.push(Float.parseFloat(element));
            }
            else if (isOperator(element)) {
                if (element.compareTo("-") == 0) {
                    String negative = "-";
                    pointer += 1;
                    negative += data[pointer];
                    stack.push(Float.parseFloat(negative));
                }
                else if (element.compareTo("*") == 0) {
                    pointer += 1;
                    float firstNumber = stack.pop();
                    float nextNumber = Float.parseFloat(data[pointer]);
                    if (firstNumber < 0) {
                        float previousNumber = stack.pop();
                        stack.push(previousNumber *= -1);
                        firstNumber = Math.abs(firstNumber);
                    }
                    stack.push(firstNumber * nextNumber);
                }
                else if (element.compareTo("/") == 0) {
                    float numberOne = stack.pop();
                    pointer += 1;
                    float nextNumber = Float.parseFloat(data[pointer]);
                    if (numberOne == 0 || nextNumber == 0) {
                        System.out.println("Cannot divide by zero");
                        System.exit(0);
                    }
                    stack.push(numberOne / nextNumber);
                }
            }
            pointer += 1;
        }
    }

    private float countUpStack() {
        float total = 0;
        int size = stack.size();
        int index = 0;
        while (index != size) {
            total += stack.pop();
            index += 1;
        }
        return total;
    }
}
