package solver;

import solver.data.Matrix;
import solver.data.Row;

public class SwapRowsCommand extends Command{
    private Matrix matrix;
    private int rowAInt;
    private int rowBInt;

    public SwapRowsCommand(Matrix matrix, int a, int b){
        this.matrix = matrix;
        this.rowAInt = a;
        this.rowBInt = b;
    }

    @Override
    public void execute() {
        Row temp = matrix.getRow(rowAInt);
        matrix.addRow(matrix.getRow(rowBInt),rowAInt);
        matrix.addRow(temp, rowBInt);
        System.out.println("Rows were swapped");
    }

    @Override
    public void unexecute() {
        System.out.println("Rows were unswapped");
    }
}
