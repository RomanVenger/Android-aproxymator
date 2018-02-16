package mnk;

/**
 * Created by romanvenger on 12.02.2018.
 */

public class Gause extends Matrix {
    private  double[] cons= new double[4];



    public  void gauss(double[][]mt1) {
//        mt1.print();
        System.out.println();


        int rows = mt1.length;
        int cols = mt1[0].length;
        double result = 1;
       //Matrix mt = new Matrix();
       double[][]mt=new double[rows][cols];
        //mt.setSize(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mt[i][j]=mt1[i][j];
            }
        }
        //строим треугольную матрицу
        for (int i = 0; i < cols; i++) {

            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    mt1[x][y]=mt[x][y];

                }
            }
            for (int j = i + 1; j < rows; j++) {
                for (int k = i; k < cols; k++) {
                    double tmp = mt[j][k] - ((mt[i][k] * mt1[j][i]) /mt[i][i]);
                    mt[j][k]=Math.round(tmp);
                }
            }

        }
        for (int x = 0; x < rows; x++) {
            result = result * mt[x][x];
        }
        print(mt);
    }

    public  double[] params(double[][]A) {

        cons[3] = A[3][4] / A[3][3];
        cons[2] = A[2][4] -(A[2][3]*cons[3])/A[2][2];
        cons[1] = A[1][4] -(A[1][2]*cons[2]+ A[1][3]*cons[3])/A[1][1] ;
        cons[0] = A[0][4] -(A[0][1]*cons[1]+ A[0][2]*cons[2]+ A[0][3]*cons[3])/ A[0][0] ;
        for (int i =0; i<cons.length;i++){
           // System.out.println(cons[i]);
        }
        return cons;
    }
}
