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
        //if I have a space in the data being sent in, it breaks in here. you end up with an array like so:
        //incoming data = "2 +1"
        //final array: "2", " ", "", "+", "1" <-note that this is more spots in your array than you have room for
        //thus you get an intex out of bounds exception
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
        int pointer = 0; //just a nitpick, but this shouldn't be called a pointer. A pointer is a real thing,
        //it has very specific connotations. The way you're using this variable more closely conforms to something
        //called an 'index'. Or, sometimes, 'idx', or even just 'i'. An index is just a well understood int variable
        //that tracks the position of the spot you're examining in an array. It's just a convention, and there's no
        //hard or fast rules.

        if (data.length == 1) {
            stack.push(Float.parseFloat(data[0]));
        }

        while (pointer < data.length - 1) { //BUG!!!! Off by one error. "2+1" equals 2. You never get to the last plus number.
            String element = data[pointer];
            if (element == null) {
                break;
            }
            if (isNumber(element)) {
                stack.push(Float.valueOf(element));
            }
            else if (isOperator(element)) {
                if (element.compareTo("-") == 0) {
                    String negative = "-";
                    pointer += 1; //you've got this line several times in here. You're looking forward, and should be
                    //looking backwards. That means you've got to hang on to the operation till next time around the loop.
                    negative += data[pointer];
                    stack.push(Float.parseFloat(negative));
                }
                else if (element.compareTo("*") == 0) {
                    pointer += 1;
                    float firstNumber = stack.pop();
                    float nextNumber = Float.parseFloat(data[pointer]);
                    float total;
                    if (firstNumber < 0) {
                        firstNumber = Math.abs(firstNumber);
                        total = firstNumber * nextNumber;
                        total = total *= -1;
                    }
                    else {
                        total = firstNumber * nextNumber;
                    }
                    stack.push(total);
                }
                else if (element.compareTo("/") == 0) {
                    float numberOne = stack.pop();
                    pointer += 1;
                    float nextNumber = Float.parseFloat(data[pointer]);
                    if (numberOne == 0 || nextNumber == 0) {
                        System.out.println("ArithmeticException: Cannot divide by zero");
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
