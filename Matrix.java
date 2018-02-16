package mnk;

public class Matrix {
    public double[][] A;

    public Matrix(){
    }
    public   double[][] createMartix(double[][]Arr){
        return new double[][]{{8, Arr[0][0], Arr[0][1], Arr[0][2], Arr[1][0]},
                {Arr[0][1], Arr[0][2], Arr[0][3], Arr[0][4], Arr[1][1]},
                {Arr[0][2], Arr[0][3], Arr[0][4], Arr[0][5], Arr[1][2]},
                {Arr[0][3], Arr[0][4], Arr[0][5], Arr[0][6], Arr[1][3]}};

    }


    public void print(double[][]Arr){
        for (int i=0; i<Arr.length; i++){
            for (int j=0; j<Arr[0].length; j++){
                System.out.print(Arr[i][j]+"\t");

            }
            System.out.println();
        }
    }
    public void setPoint(double[]Arr, double[]cons) {
        for (int i = 1; i < Arr.length; i++) {
            Arr[i]=cons[0]+cons[1]*0+cons[2]*i*i+cons[3]*(i^3);
            System.out.println(Arr[i]);
        }
    }
}
