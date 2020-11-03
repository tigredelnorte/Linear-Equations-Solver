package solver.data;

import java.util.Arrays;

public class Row {
    private ComplexNumber[] coefficients;

    public Row (String[] rowInput) {
        this.coefficients = new ComplexNumber[rowInput.length];
        for (int i = 0; i < rowInput.length; i++) {
            coefficients[i] = ComplexNumber.parseComplex(rowInput[i]);
        }
    }
    public Row (ComplexNumber[] rowInput) {
        this.coefficients = rowInput;
    }

    @Override
    public String toString() {
        return  Arrays.toString(coefficients);
    }

    public ComplexNumber get(int index) {
        return this.coefficients[index];
    }

    public ComplexNumber[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(ComplexNumber[] coefficients) {
        this.coefficients = coefficients;
    }

    public ComplexNumber getLastCoefficient() {
        return coefficients[coefficients.length - 1];
    }
    public Row multiply(ComplexNumber value) {
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i].multiply(value);
        }
        return new Row(coefficients);
    }

    public void divide(ComplexNumber value) {
        for (int i = 0; i < this.coefficients.length; i++) {
            System.out.println(value);
            coefficients[i].divide(value);
        }
    }

    public void subtractRow(Row row) {
        for (int i = 0; i < this.coefficients.length; i++) {
            coefficients[i].subtract(row.get(i));
        }
    }

    public boolean isZero() {
        for(int i = 0; i < coefficients.length - 1; i++) {
            if (!coefficients[i].equals(0)) {
                return false;
            }
        }
        return true;
    }
}
