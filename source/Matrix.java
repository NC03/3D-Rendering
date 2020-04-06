import java.util.Arrays;

/**
 * Matrix
 * 
 * @author NC03
 * @version 1.2.2
 * @deprecated
 * 
 */
@Deprecated
public class Matrix {
    private double[][] data;

    public Matrix(double[][] data) {
        this.data = data;
    }

    public double[][] getData() {
        return data;
    }

    private double[] subtract(double[] a, double[] b) {
        double[] out = new double[a.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = a[i] - b[i];
        }
        return out;
    }

    public Matrix rref() {
        // Put in upper triangular form
        for (int col = 0; col < data[0].length - 1; col++) {
            orderRows();
            boolean flag = false;
            int idx = 0;
            for (int i = 0; i < data.length; i++) {
                if (!flag && leadingCoefficientIndex(getRow(i)) == col) {
                    idx = i;
                    flag = true;
                } else if (leadingCoefficientIndex(getRow(i)) == col) {
                    data[i] = subtract(data[i], scalar(data[idx], data[i][col] / data[idx][col]));
                }
            }
        }
        scaleRows();
        // Back propogation
        for (int i = data.length - 1; i >= 0; i--) {
            for (int j = leadingCoefficientIndex(getRow(i)) + 1; j < data[0].length - 1; j++) {
                boolean flag = false;
                for (int k = data.length - 1; k >= 0; k--) {
                    if (!flag && leadingCoefficientIndex(getRow(k)) == j) {
                        data[i] = subtract(data[i], scalar(data[k], data[i][j]));
                        flag = true;
                    }
                }
            }
        }

        return new Matrix(data);
    }

    private double[] getRow(int idx) {
        return data[idx];
    }

    private void scaleRows() {
        for (int i = 0; i < data.length; i++) {
            data[i] = scalar(data[i], 1.0 / data[i][leadingCoefficientIndex(data[i])]);
        }
    }

    private int leadingCoefficientIndex(double[] arr) {
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                idx++;
            } else {
                return idx;
            }
        }
        return arr.length - 1;
    }

    private double[] scalar(double[] row, double scalar) {
        double[] out = new double[row.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = row[i] * scalar;
        }
        return out;
    }

    private void swap(int a, int b) {
        double[] temp = getRow(a);
        data[a] = data[b];
        data[b] = temp;
    }

    private void orderRows() {
        while (!checkOrder()) {
            for (int i = 0; i < data.length - 1; i++) {
                int idx1 = leadingCoefficientIndex(getRow(i));
                int idx2 = leadingCoefficientIndex(getRow(i + 1));
                if (!(idx1 <= idx2)) {
                    swap(idx1, idx2);
                }
            }
        }
    }

    private boolean checkOrder() {
        for (int i = 0; i < data.length - 1; i++) {
            int idx1 = leadingCoefficientIndex(getRow(i));
            int idx2 = leadingCoefficientIndex(getRow(i + 1));
            if (!(idx1 <= idx2)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String out = "";
        for (double[] row : data) {
            out += Arrays.toString(row) + "\n";
        }
        return out;
    }
}
