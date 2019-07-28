import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> vars = new ArrayList<>();
    static Matrix matrix;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("data/data.txt"))) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            float[][] mat = new float[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = scanner.nextInt();
                }
            }
            while (scanner.hasNextInt()){
                vars.add(scanner.nextInt()-1);
            }
            matrix = new Matrix(mat, vars);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0;i<vars.size();i++){
            matrix.reduceColumnByLineDown(i);
        }

        for (int i = vars.size()-1;i>=0;i--){
            matrix.reduceColumnByLineUp(i);
        }

    }


}
