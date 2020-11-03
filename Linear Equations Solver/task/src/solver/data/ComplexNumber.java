package solver.data;

public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber() {
        this.real = 0.0;
        this.imaginary = 0.0;
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber(ComplexNumber nr) {
        this.real = nr.real;
        this.imaginary = nr.imaginary;
    }

    public void set(ComplexNumber z) {
        this.real = z.real;
        this.imaginary = z.imaginary;
    }

    public void add(ComplexNumber z) {
        set(add(this, z));
    }

    public void subtract(ComplexNumber z) {
        set(subtract(this, z));
    }

    public void multiply(ComplexNumber z) {
        set(multiply(this, z));
    }

    public void divide(ComplexNumber z) {
        set(divide(this, z));
    }

    public void multiplyBy(double z) {
        set(multiplyBy(this, z));
    }

    public static ComplexNumber add (ComplexNumber z1, ComplexNumber z2) {
        return new ComplexNumber(z1.real + z2.real, z1.imaginary + z2.imaginary );
    }

    public static ComplexNumber subtract(ComplexNumber z1, ComplexNumber z2) {
        return new ComplexNumber(z1.real - z2.real, z1.imaginary - z2.imaginary );
    }

    public static ComplexNumber multiplyBy(ComplexNumber z, double multiplyer) {
        return new ComplexNumber(z.real * multiplyer, z.imaginary + multiplyer);
    }

    public static ComplexNumber multiply(ComplexNumber z1, ComplexNumber z2) {
        return new ComplexNumber(z1.real * z2.real - z1.imaginary * z2.imaginary,
                z1.real * z2.imaginary + z2.real * z1.imaginary);
    }

    public ComplexNumber conjugate () {
        return new ComplexNumber(this.real, -this.imaginary);
    }

    public double mod() {
        return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
    }

    public static ComplexNumber divide (ComplexNumber z1, ComplexNumber z2) {
        ComplexNumber up = multiply(z1, z2.conjugate());
        double div = Math.pow(z2.real, 2) + Math.pow(z2.imaginary, 2);
//        double div = Math.pow(z2.real, 2) + Math.pow(z2.imaginary, 2);Math.pow(z2.mod(), 2);
        return new ComplexNumber(up.real/div, up.imaginary/div);
    }

    public boolean equals(double nr) {
        if (this.real == nr && this.imaginary == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static ComplexNumber parseComplex(String s) {
        ComplexNumber parse = new ComplexNumber();
        if (s.contains("+") || s.contains("-") && s.lastIndexOf("-") > 0) {
            //sa dwie cyfry
            String real = null;
            String imaginary = null;
            if (s.substring(s.length() - 2, s.length() - 1).equals("+") || s.substring(s.length() - 2, s.length() - 1).equals("-")) {
                s = s.replace("i", "1");
            } else {
                s = s.replaceAll("i", "");
            }
            if (s.contains("+")) {
                real = s.substring(0, s.lastIndexOf("+"));
                imaginary = s.substring(s.lastIndexOf("+") + 1);
                parse = new ComplexNumber(Double.parseDouble(real), Double.parseDouble(imaginary));
            } else {
                real = s.substring(0, s.lastIndexOf("-"));
                imaginary = s.substring(s.lastIndexOf("-"));
                parse = new ComplexNumber(Double.parseDouble(real), Double.parseDouble(imaginary));
            }
        }else {
            //jest jedna cyfra
            if (s.equals("i") || s.equals(("-i"))) {
                s = s.replaceAll("i", "1");
                parse = new ComplexNumber(0, Double.parseDouble(s));
            } else if(s.endsWith("i")){
                //jest imaginary
                s = s.replaceAll("i", "");
                parse = new ComplexNumber(0, Double.parseDouble(s));
            } else {
                //jest tylko real
                parse = new ComplexNumber(Double.parseDouble(s), 0);
            }
        }
        return parse;
    }
    public String toString()
    {
        String re = "";
        String im = "";
        if (this.real != 0) {
            re = String.format("%.4f", this.real);
        }
        if (this.imaginary < 0) {
            if (this.imaginary == -1) im = "-i";
            else im = String.format("%.4f",this.imaginary) + "i";
        }
        if (this.imaginary > 0) {
            if (this.imaginary == 1 && this.real == 0) im = "i";
            else if (this.imaginary == 1 && this.real != 0) im = "+i";
            else if (this.imaginary != 1 && this.real != 0) im = "+" + String.format("%.4f",this.imaginary) + "i";
            else if (this.imaginary != 1 && this.real == 0) im = String.format("%.4f",this.imaginary) + "i";

        }
        if (this.real == 0 && this.imaginary == 0) {
            re = "0";
        }
        return (re+im).replaceAll(",", ".");
    }
}
