package solver.data;

public class Matrix {
    private ComplexNumber[] variables;
    private Row[] rows;

    public Matrix (int len) {
        this.rows = new Row[len];
    }

    public int getLength() {
        return rows.length;
    }

    public int getVarLen() {
        return variables.length;
    }

    public Row[] getAllRows() {
        return rows;
    }

    public Row getRow(int i) {
        return rows[i];
    }
    public void addRow(Row row, int l) {
     this.rows[l] = row;
    }

    public ComplexNumber[] getVariables() {
        return variables;
    }

    public void setVariables(ComplexNumber[] variables) {
        this.variables = variables;
    }

    public String variablesToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < variables.length; i++) {
            sb.append(variables[i].toString() + "\n");
        }
        return sb.toString();
    }
}
