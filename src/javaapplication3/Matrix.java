/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.Scanner;

/**
 * @author Ivleva1
 */
public class Matrix {

    private int height;
    private int width;
    private int arr[][];


    public Matrix() {
        height = 0;
        width = 0;
        arr = new int[height][width];

    }

    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        arr = new int[height][width];
    }

    public void setValue(String... str) {
        if (str.length < height) {
            System.out.println("Попытка ввести матрицу меньше исходной");
            return;
        }

        Scanner scan;
        for (int i = 0; i < height; i++) {
            scan = new Scanner(str[i]);
            for (int j = 0; j < width; j++) {
                if (scan.hasNextInt()) {
                    arr[i][j] = scan.nextInt();
                } else {
                    System.out.println("Матрица должна быть из чисел");
                }
            }
        }
    }

    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%d ", arr[i][j]);
            }
            System.out.println();
        }
    }

    public Matrix multiplication(Vector vector) {
        Matrix b = new Matrix(height, 1);
        for (int h = 0; h < height; h++) {
            int sum = 0;
            for (int w = 0; w < width; w++) {
                sum += arr[h][w] * vector.getValue()[w];
            }
            b.arr[h][0] = sum;
        }
        return b;
    }
}
