package com.davidkurkov;

/**
 * Created by david on 3/25/16.
 */

class CustomArray implements list {
    Number[] myArray;
    int index = 0;
    int memorySpots = 10;
    int multiplierAndBuffer = 3;

    CustomArray() {
        this.myArray = new Number[this.memorySpots];
    }

    @Override
    public void insert(Number value) {
        checkOverflow();
        this.myArray[index] = value;
        this.index += 1;
    }

    @Override
    public void remove(Number value) {
        int valueOfIndex = findIndexOfValue(value);
        if (valueOfIndex != -1) {
            int count = valueOfIndex;
            while (count < this.index) {
                this.myArray[count] = this.myArray[count+1];
                count += 1;
            }
            this.index -= 1;
        }
    }

    @Override
    public int size() {
        return this.index;
    }

    @Override
    public void clear() {
        this.myArray = new Number[memorySpots];
        this.index = 0;
        this.memorySpots = 10;
        this.multiplierAndBuffer = 3;
    }

    @Override
    public String printElements() {
        StringBuilder elements = new StringBuilder();
        if (this.index == 0) {
            elements.append("[]");
        }
        else {
            for (int i=0; i < this.index; i++) {
                if (i == 0) {
                    elements.append(this.myArray[i]);
                }
                else {
                    elements.append(", ");
                    elements.append(this.myArray[i]);
                }
            }
            elements.insert(0, "[");
            elements.append("]");
        }
        System.out.println(elements.toString());
        return elements.toString();
    }

    private int findIndexOfValue(Number value) {
        int indexOfValue = -1;
        for (int i=0; i < this.index; i++) {
            if (this.myArray[i] == value) {
                indexOfValue = i;
                break;
            }
        }
        return indexOfValue;
    }

    private void checkOverflow() {
        if (this.myArray.length - size() <= this.multiplierAndBuffer) {
            int tempArrayIndex = 0;
            int newLength = this.myArray.length * this.multiplierAndBuffer;
            Number[] tempArray = new Number[newLength];
            for (int i=0; i < size(); i++) {
                tempArray[tempArrayIndex] = this.myArray[i];
                tempArrayIndex += 1;
            }
            this.myArray = tempArray;
        }
    }
}
