/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 * @author Ivleva1
 */
public class Vector {

    private int size;
    int arr[];

    public Vector(int size) {
        this.size = size;
        arr = new int[size];
    }

    public void setValue(int... value) {
        for (int i = 0; i < size && i < value.length; i++)
            arr[i] = value[i];
    }

    public int[] getValue() {
        return arr;
    }

    public int getSize() {
        return size;
    }
}
