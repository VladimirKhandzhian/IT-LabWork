package ua.nure.khandzhian.tripiy.lab2.util;

import java.util.Scanner;

public class MatrixUtil {

    public static final String ROW = "row";
    public static final String COLUMN = "column";
    public static final String INCORRECT_VALUE = "Sorry, but you enter incorrect value. Try again: ";
    public static final String ENTER_PARAMETER = "Please enter %s: ";
    public static final String ENTER_MATRIX = "Enter matrix element: ";
    public static final String ELEMENT = "Element №[%d][%d] --> ";

    private double[][] matrix;

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void fillMatrix() {
        Scanner scanner = new Scanner(System.in);
        matrix = new double[enterParameterOfMatrixSize(scanner, ROW)][enterParameterOfMatrixSize(scanner, COLUMN)];
        enterDataToMatrix(scanner);
    }

    public void printMatrix() {
        for (double[] rows : matrix) {
            for (double column : rows) {
                System.out.print(column + "\t");
            }
            System.out.println();
        }
    }

    private int enterParameterOfMatrixSize(Scanner scanner, String nameOfParameter) {
        System.out.print(String.format(ENTER_PARAMETER, nameOfParameter));
        while (true) {
            int size = scanner.nextInt();
            if (size > 0) {
                return size;
            }
            System.out.print(INCORRECT_VALUE);
        }
    }

    private void enterDataToMatrix(Scanner scanner) {
        System.out.println(ENTER_MATRIX);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(String.format(ELEMENT, i, j));
                matrix[i][j] = scanner.nextInt();
            }
        }
    }
}
