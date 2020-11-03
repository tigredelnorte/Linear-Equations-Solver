package solver;

import solver.data.ComplexNumber;
import solver.data.Matrix;
import solver.data.Row;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File fileIn = new File(getFileIn(args));
//        File fileIn = new File("outTest.txt");
        try (Scanner sc = new Scanner(fileIn)) {
            Matrix matrix = getMatrix(sc);
            LinearEquationInvoker invoker = new LinearEquationInvoker();
            MatrixHandler handler = new MatrixHandler(matrix, invoker);
            handler.analyzeMatrix();
            String output = null;
            if (handler.isNoSolution()) {
                output = "No solutions";
            } else if (handler.isInfiniteSolution()) {
                output = "Infinitely many solutions";
            } else {
                handler.undoColsSwap();
                handler.setFinalVariables();
                output = matrix.variablesToString();
            }
            System.out.println(output);
            File fileOut = new File(getFileOut(args));
            FileWriter writer = new FileWriter(fileOut);
            writer.write(output);
            writer.close();
        }
    }

    private static Matrix getMatrix(Scanner sc) {
        int[] n = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int j = 0;
        Matrix matrix = new Matrix(n[1]);
        matrix.setVariables(new ComplexNumber[n[0]]);
        while(sc.hasNext()) {
            String[] temp = sc.nextLine().split("\\s+");
            Row row = new Row(temp);
            matrix.addRow(row, j++);
        }
        sc.close();
        return matrix;
    }
    private static String getFileIn(String[] args) {
        for(int i = 0; i < args.length; i += 2) {
            if(args[i].contains("in")) {
                return args[i + 1];
            }
        }
        return null;
    }
    private static String getFileOut(String[] args) {
        for(int i = 0; i < args.length; i += 2) {
            if(args[i].contains("out")) {
                return args[i + 1];
            }
        }
        return null;
    }
}
