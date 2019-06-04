package dev.uniayr.algrabra;

import java.util.Scanner;
import java.util.Set;

public class Matrix {

    private int height;
    private int width;
    private double arr[][];

    public Matrix(int size) {
        this(size, size);
    }

    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        arr = new double[height][width];
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

    // Метод удаляет row-строку и row-столбец
    public Matrix removeRowAndColumn(int row) {
        row--;
        Matrix out = new Matrix(height - 1, width - 1);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == row || j == row)
                    continue;
                out.arr
                        [i > row ? i - 1 : i]
                        [j > row ? j - 1 : j] =
                        arr[i][j];
            }
        }
        return out;
    }

    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%.0f ", arr[i][j]);
            }
            System.out.println();
        }
    }

    // Вычисление tr матрицы
    public double trajectory() {
        double sum = 0;
        for (int i = 0; i < height; i++) {
            sum += arr[i][i];
        }
        return sum;
    }

    // Вычисляем суммы миноров степени degree
    // на главной диагонали
    public double getSumMinorsByDegree(int degree) {
        degree = height - degree;
        if (!isQuadratic())
            throw new RuntimeException("Минор диагоналей есть только в квадратной матрице");
        double sum = 0;

        /*
         * Генерируем все возможные комбинации удаляем строк
         *  Удаляем по одной строке и столбцу за каждый проход
         *  Но: если когда-то был удален элемент меньше i
         *  то мы уменьшим i на соотвественное количество
         *  удаленных элементов, т. е. если у нас матрица размера 7
         *  комбинация удаляемых строк [4,7,2], то после удаления 4
         *  мы не сможем удалить 7 строку, так как она уже стала 6
         *  за это отвечает как раз отвечает offset
         */

        for (Set<Integer> set : Combanations.generate(degree, height)) {
            int[] list = set.stream().mapToInt(Number::intValue).toArray();
            Matrix m = this.copy();

            // Удаляем i-ую строку и столбец
            // b - смещение связанное с предшествущими удалениями
            for (int q = 0; q < degree; q++) {
                int i = list[q];
                int offset = 0;
                for (int c = 0; c < q; c++) {
                    if (list[c] < i)
                        offset++;
                }
                m = m.removeRowAndColumn(i - offset);
            }
            sum += m.determinate();
        }
        return sum;
    }

    public Matrix minor(Matrix matrix, int row, int column) {
        int size = matrix.height - 1;
        Matrix minor = new Matrix(size);
        int dI = 0;
        int dJ = 0;

        for (int i = 0; i <= size; i++) {
            dJ = 0;
            for (int j = 0; j <= size; j++) {
                if (i == row) {
                    dI = 1;
                } else {
                    if (j == column) {
                        dJ = 1;
                    } else {
                        minor.arr[i - dI][j - dJ] = matrix.arr[i][j];
                    }
                }
            }
        }
        return minor;
    }

    public double determinate() {
        double matrixDeterminant = 0;
        if (this.height == 2) {
            matrixDeterminant = this.arr[0][0] * this.arr[1][1] - this.arr[1][0] * this.arr[0][1];
        } else {
            int koeff = 1;
            for (int i = 0; i < this.height; i++) {
                if (i % 2 == 1) {
                    koeff = -1;
                } else {
                    koeff = 1;
                }

                matrixDeterminant += koeff * this.arr[0][i] * minor(this, 0, i).determinate();
            }
        }
        return matrixDeterminant;
    }

    // Высчитываем характеристический многочлен
    public Polynom getCharacterPolynomial() {
        double[] polynom = new double[height + 1];
        polynom[height] = this.determinate();
        polynom[0] = 1;
        polynom[1] = this.trajectory();
        for (int i = 2; i < height; i++) {
            polynom[i] = getSumMinorsByDegree(i);
        }
        for (int i = height; i != 0; i--)
            if (i % 2 == 1)
                polynom[i] = -polynom[i];
        return new Polynom(polynom);
    }

    public boolean isQuadratic() {
        return width == height;
    }

    private Matrix copy() {
        Matrix matrix = new Matrix(height, width);
        matrix.arr = arr;
        return matrix;
    }
}