package solver;

import solver.data.ComplexNumber;
import solver.data.Matrix;
import solver.data.Row;

public class MatrixHandler {
    private Matrix matrix;
    private LinearEquationInvoker invoker;

    public MatrixHandler(Matrix matrix, LinearEquationInvoker invoker) {
        this.matrix = matrix;
        this.invoker = invoker;
    }

    public void analyzeMatrix() {
        Row[] rows = matrix.getAllRows();
        for (int i = 0; i < Math.min(matrix.getVarLen(), matrix.getLength()); i++) {
            if (rows[i].getCoefficients()[i].equals(0)) {
                for (int j = i + 1; j < matrix.getLength(); j++) {
                    if (!rows[j].getCoefficients()[i].equals(0)) {
                        swapRows(i, j);
                        break;
                    }
                }
            }
            if (rows[i].getCoefficients()[i].equals(0)) {
                for (int j = i + 1; j < matrix.getLength(); j++) {
                    if (!rows[i].getCoefficients()[j].equals(0)) {
                        swapCols(i, j);
                        break;
                    }
                }
            }
            if (rows[i].getCoefficients()[i].equals(0)) {
                for (int j = i + 1; j < matrix.getLength(); j++) {
                    for (int k = i + 1; k < matrix.getLength(); k++) {
                        if (!rows[j].getCoefficients()[k].equals(0)) {
                            swapRows(i, j);
                            swapCols(i, k);
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < Math.min(matrix.getVarLen(), matrix.getLength()); i++) {

            if (!rows[i].get(i).equals(1) && !rows[i].get(i).equals(0)) {
                ComplexNumber nr = rows[i].get(i);
                ComplexNumber[] cn = new ComplexNumber[rows[i].getCoefficients().length];
                for (int j = 0; j < rows[i].getCoefficients().length; j++) {
                    cn[j] = new ComplexNumber(ComplexNumber.divide(rows[i].get(j), nr));
                }
                rows[i].setCoefficients(cn);
//                System.out.println("R" + i + " / " + nr.toString() + " -> R" + i);
//                System.out.println(rows[i].toString());
            }
            for (int j = i + 1; j < rows.length; j++) {
                if (!rows[j].get(i).equals(0)) {
                    ComplexNumber nr = rows[j].get(i);
                    ComplexNumber[] cn = new ComplexNumber[rows[j].getCoefficients().length];
                    for (int k = 0; k < rows[j].getCoefficients().length; k++) {
                        ComplexNumber cnTemp = ComplexNumber.multiply(rows[i].get(k), nr);
                        cn[k] = new ComplexNumber(ComplexNumber.subtract(rows[j].get(k), cnTemp));
                    }
                    rows[j].setCoefficients(cn);
//                    System.out.println("R" + j + " - " + nr.toString() + " * R" + i + " -> R" + j);
//                    System.out.println(rows[j].toString());
                }
            }
        }
    }

    public boolean isNoSolution() {
        int variablelen = matrix.getVarLen();
        int rowsLen = matrix.getLength();
        for (int i = 0; i < rowsLen; i++) {
            if(matrix.getRow(i).isZero() && !matrix.getRow(i).getCoefficients()[variablelen].equals(0))
                return true;
        }
        return false;
    }

    public boolean isInfiniteSolution() {
        int variablelen = matrix.getVarLen();
        int rowsLen = matrix.getLength();
        for (int i = 0; i < matrix.getLength(); i++) {
            if(matrix.getRow(i).isZero() && matrix.getRow(i).getCoefficients()[variablelen].equals(0))
                rowsLen--;
        }
        if (variablelen > rowsLen) {
            return true;
        }
        return false;
    }

    private void swapCols(int i, int j) {
        SwapColumnsCommand command = new SwapColumnsCommand(matrix, i, j);
        invoker.setCommand(command);
        invoker.execute();
        CommandHistory.add(command);
        System.out.printf("C%d <-> C%d\n",i ,j);
    }

    private void swapRows(int i, int j) {
        SwapRowsCommand command = new SwapRowsCommand(matrix,i ,j);
        invoker.setCommand(command);
        invoker.execute();
        System.out.printf("R%d <-> R%d\n",i ,j);
    }


    public void setFinalVariables() {
        ComplexNumber[] variables = new ComplexNumber[matrix.getVarLen()];
        Row[] rows = matrix.getAllRows();
        for(int i = matrix.getVarLen() - 1; i >= 0; i--) {
            variables[i] = rows[i].getLastCoefficient();
            for(int j = variables.length - 1; j > i; j--) {
                variables[i].subtract(ComplexNumber.multiply(rows[i].getCoefficients()[j], variables[j]));
            }
        }
        matrix.setVariables(variables);
    }

    public void undoColsSwap() {
        while(CommandHistory.getHistorySize() > 0) {
            Command command = CommandHistory.get();
            invoker.setCommand(command);
            invoker.unexecute();
            System.out.printf("C%d >-< C%d\n",command.colAInt ,command.colBInt);
        }
    }
}
