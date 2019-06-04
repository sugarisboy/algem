package dev.uniayr.algrabra;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;


public class Main {

    private Matrix matrix;

    public static void main(String[] args) {
        Main app = new Main();
    }

    public Main() {
        readFile();
        matrix.display();
        Polynom characterPolynomial = matrix.getCharacterPolynomial();
        System.out.println(characterPolynomial.getValues());
        Map<Double, Integer> roots = characterPolynomial.roots();
        System.out.println(roots);
    }

    private void readFile() {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("Matrix.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            e.printStackTrace();
        }
        matrix = readArray(scan);
        // vector = readVector(scan);
    }

    private Matrix readArray(Scanner scan) {
        int height = scan.nextInt();
        int width = scan.nextInt();
        scan.nextLine();
        String data[] = new String[height];
        for (int i = 0; i < height; i++)
            data[i] = scan.nextLine();

        Matrix matrix = new Matrix(height, width);
        matrix.setValue(data);
        return matrix;
    }
}