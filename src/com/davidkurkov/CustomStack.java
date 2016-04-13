package com.davidkurkov;

/**
 * Created by david on 4/6/16.
 */
class CustomStack extends CustomArray implements stack {

    @Override
    public void push(float value) {
        insert(value);
    }

    @Override
    public float pop() {
        this.index -= 1;
        float value = (float) myArray[index];
        return value;
    }

    public int size() {
        return this.index;
    }
}
