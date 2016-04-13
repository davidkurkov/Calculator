package com.davidkurkov;

/**
 * Created by david on 4/6/16.
 */
class CustomStack implements stack {
    float[] myArray = new float[10];
    int size;
    int index;

    @Override
    public void push(float value) {
        this.myArray[index] = value;
        this.size += 1;
        this.index += 1;
    }

    @Override
    public float pop() {
        float value = this.myArray[this.size-1];
        this.size -= 1;
        this.index -=1;
        return value;
    }

    public int size() {
        return this.size;
    }
}
