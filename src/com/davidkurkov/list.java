package com.davidkurkov;

/**
 * Created by david on 3/25/16.
 */
abstract interface list {

    void insert(Number value);

    void remove(Number value);

    int size();

    void clear();

    String printElements();

}
