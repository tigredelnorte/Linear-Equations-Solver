package solver;

import solver.data.ComplexNumber;
import solver.data.Matrix;
import solver.data.Row;

public class MultiplyToZeroCommand extends Command{
    private Matrix matrix;
    ComplexNumber multiplier;
    int rowAInt;
    int rowBInt;

    public MultiplyToZeroCommand(Matrix matrix, ComplexNumber multiplyBy, int a, int b) {
        this.matrix = matrix;
        this.multiplier = multiplyBy;
        this.rowAInt = a;
        this.rowBInt = b;
    }

    @Override
    public void execute() {
        Row rowA = matrix.getRow(rowAInt).multiply(multiplier);
        Row rowB = matrix.getRow(rowBInt);
        rowB.subtractRow(rowA);
        matrix.addRow(rowB, rowBInt);
        System.out.println("Rows were updated to Echelon form");
    }

    @Override
    public void unexecute() {
        System.out.println("Rows were unupdated from Echelon form");
    }
}
