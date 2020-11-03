package solver;

import solver.data.ComplexNumber;
import solver.data.Matrix;
import solver.data.Row;

public class SwapColumnsCommand extends Command{
    private Matrix matrix;
    int colAInt;
    int colBInt;

    public SwapColumnsCommand(Matrix matrix, int a, int b) {
        this.matrix = matrix;
        this.colAInt = a;
        this.colBInt = b;
    }
    @Override
    public void execute() {
        for (int i = 0; i < matrix.getVariables().length; i++) {
            ComplexNumber[] temp = matrix.getRow(i).getCoefficients();
            ComplexNumber tempNr = temp[colAInt];
            temp[colAInt] = temp[colBInt];
            temp[colBInt] = tempNr;
            matrix.addRow(new Row(temp),i);
        }

        System.out.println("Columns were swapped");
    }

    @Override
    public void unexecute() {
        for (int i = 0; i < matrix.getVariables().length; i++) {
            ComplexNumber[] temp = matrix.getRow(i).getCoefficients();
            ComplexNumber tempNr = temp[colAInt];
            temp[colAInt] = temp[colBInt];
            temp[colBInt] = tempNr;
            matrix.addRow(new Row(temp),i);
        }
        System.out.println("Columns were unswapped");
    }
}
