package mnk;

import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import aprox_mnk.Imnk;

/**
 * Created by romanvenger on 11.02.2018.
 */

public class Mnk extends Matrix implements Imnk{
    public static double[][] selectPoints = new double[2][6];
    private  double [][] Arr = new double[2][7];
    public  static   double [] points = new double[7];
    public static ArrayList<Entry> yVals;

    private static ArrayList setYAxisValues(double[] points){
        ArrayList yVals = new ArrayList<>();



        float r=0f;
        r=Math.abs ((float)points[0]-(float)selectPoints[1][0]*271);




        for (int i=0;i<points.length;i++)
            yVals.add(new Entry( i, (((float)points[i])*3)+r));
        for (int i=0;i<5;i++) {
            yVals.add(new Entry(i, (float) selectPoints[1][i] * 271));
            // yVals.remove(5);

        }

        return yVals;
    }
   public Mnk(){

      System.out.println("object mnk");
      makeArr();
       A=createMartix(Arr);
      Gause gause=new Gause();
      gause.gauss( A);
       System.out.println("create mat");
     setPoint(points,gause.params(A));
       System.out.println("create mat");
       yVals = setYAxisValues(points);
      // point();


   }
   public void point (){
       int r=0;
      // r= yVals.get(1)+yVals.get(2);
       for (int i=0;i<points.length;i++)
       {
           yVals.get(1);

       }
       System.out.println  (yVals.get(1));
       System.out.println  (yVals.get(2));
       System.out.println  (yVals.get(3));
       System.out.println  (yVals.get(4));
       System.out.println  (yVals.get(5));
       System.out.println  (yVals.get(6));
    //   System.out.println  (yVals.get(5));

   }

   public void makeArr() {
      System.out.println("jhfgr");

      for (int i = 0; i <= 5; i++) {

            Arr[0][0] += ++i;
            Arr[0][1] += i ^ 2;
            Arr[0][2] += i ^ 3;
            Arr[0][3] += i ^ 4;
            Arr[0][4] += i ^ 5;
            Arr[0][5] += i ^ 6;

            Arr[1][0] += selectPoints[1][i];
            Arr[1][1] += selectPoints[1][i] * Arr[0][1];
            Arr[1][2] += selectPoints[1][i] * Arr[0][2];
            Arr[1][3] += selectPoints[1][i] * Arr[0][3];
         }
      }


      }
