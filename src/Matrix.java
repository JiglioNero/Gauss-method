import java.util.ArrayList;

public class Matrix {
    private float[][] mat;
    private int n;

    public Matrix(float[][] mat, ArrayList<Integer> vars) {
        this.mat = mat;
        this.n = vars.size();

        for (int i = 0; i < n; i++) {
            if (vars.get(i) != i) {
                swapColumns(vars.get(i), i);
            }
        }
    }

    public void swapLines(int a, int b) {
        if (a != b && a < mat.length & b < mat.length) {
            float tmp;
            for (int i = 0; i < mat[0].length; i++) {
                tmp = mat[a][i];
                mat[a][i] = mat[b][i];
                mat[b][i] = tmp;
            }
            System.out.println("swap lines " + a + "<->" + b);
            print();
        }
    }

    public void swapColumns(int a, int b) {
        if (a != b && a < mat[0].length & b < mat[0].length) {
            float tmp;
            for (int i = 0; i < mat.length; i++) {
                tmp = mat[i][a];
                mat[i][a] = mat[i][b];
                mat[i][b] = tmp;
            }
            System.out.println("swap columns " + a + "<->" + b);
            print();
        }
    }

    public void addLines(int line, int add, float coeff) {
        if (line != add && line < mat[0].length & add < mat[0].length && coeff != 0) {
            for (int i = 0; i < mat[0].length; i++) {
                mat[line][i] = mat[line][i] + mat[add][i] * coeff;
            }
            System.out.println(line + (coeff < 0 ? "-=" : "+=") + coeff + "*" + add);
            print();
        }
    }

    public void multLine(int line, float coeff) {
        if (line < mat[0].length && coeff != 0 && coeff != 1) {
            for (int i = 0; i < mat[0].length; i++) {
                mat[line][i] = mat[line][i] * coeff;
            }
            System.out.println(line + "*" + coeff);
            print();
        }
    }

    public void reduceColumnByLineDown(int line) {
        if (line < n) {
            if (mat[line][line] == 0){
                for (int i = line+1; i < n; i++) {
                    if (mat[i][line] != 0) {
                        swapLines(line, i);
                        break;
                    }
                }
            }
            if (mat[line][line] == 0){
                return;
            }

            multLine(line, 1.f / mat[line][line]);
            for (int i = line + 1; i < n; i++) {
                addLines(i, line, mat[i][line] * (-1.f) / mat[line][line]);
            }
        }
    }

    public void reduceColumnByLineUp(int line) {
        if (line < n) {
            if (mat[line][line] == 0){
                return;
            }

            multLine(line, 1.f / mat[line][line]);
            for (int i = line - 1; i >= 0; i--) {
                addLines(i, line, mat[i][line] * (-1.f) / mat[line][line]);
            }
        }
    }

    public float[][] getMat() {
        return mat;
    }

    public void print() {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
