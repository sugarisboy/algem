/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class JavaApplication3 {

    private Matrix matrix1;

    public static void main(String[] args) {
        JavaApplication3 app = new JavaApplication3();
    }

    public JavaApplication3() {
        readFile();
        matrix1.display();

        Vector vector = new Vector(2);
        vector.setValue(1, 2);

        matrix1.multiplication(vector).display();
    }

    private void readFile() {
        Scanner scan = null;
        try {
            scan = new Scanner(new File("Пример вход.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            e.printStackTrace();
        }

        matrix1 = readArray(scan);
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

    /*public static void main(String[] args) {
        int W = -1;
        String[] a1 = null;
        Matrix matrix1 = null;
        Vector C = null;
        Matrix X = null;
        try {



            BufferedReader in = new BufferedReader(new FileReader("Пример Вход.txt"));
            PrintWriter out = new PrintWriter("Пример Выход.txt");

            String str = in.readLine();
            out.println(str);
            System.out.println(str);
            String[] a = str.trim().split(" +");
            int H = Integer.parseInt(a[0]);
            W = Integer.parseInt(a[1]);
            matrix1 = new Matrix(H, W);
            C = new Vector(W);
            X = new Matrix(1, W);
            for (int i = 0; i < H; i++) {
                str = in.readLine();
                a1 = str.trim().split(" +");
                for (int j = 0; j < W; j++) {
                    //matrix1.a[i][j] = Integer.parseInt(a1[j]);
                }

            }
            str = in.readLine();
            a1 = str.trim().split(" +");
            for (int i = 0; i < W; i++) {
                C.a[1][i] = Integer.parseInt(a1[i]);

            }

            while ((str = in.readLine()) != null) {
                out.println(str);
                System.out.println(str);
            }

        } catch (Exception e) {
            System.out.printf("System error");
        }

        X = matrix1.multiplication(C);
        String spST = "";
        for (int i = 0; i < W; i++) {
            //spST = String.format("\n\n ", X.a[i]);
        }
        try {
            PrintWriter add = new PrintWriter(new FileOutputStream("Пример Выход.txt", false));

            for (int i = 0; i < a1.length; i++)
                add.write(a1[i]);


        } catch (IOException e) {
            System.out.println("Error data file");
        }

    }*/

}