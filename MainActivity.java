package fgh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.example.romanvenger.myapplication1.Graphics;
import com.example.romanvenger.myapplication1.R;
import com.example.romanvenger.myapplication1.graph;

import java.util.ArrayList;

import mnk.Matrix;
import mnk.Mnk;

public  class MainActivity extends AppCompatActivity {

    public static double[][] selectedPoints= Mnk.selectPoints;
public Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
        setContentView(R.layout.activity_main);
         intent = new Intent(this, graph.class);


    }
    public void onMyButtonClick(View view)
    {
        setContentView(new MyView(this));
    }


    class MyView extends View {

        RectF rectF = new RectF();
        private ArrayList<RectF> points=new ArrayList<>();
        int scrWidth ;
        int scrHeight;
        float w = 0;
        Paint p;
        float y;
        // переменные для перетаскивания
        boolean drag = false;
        float dragY = 0;
        int index =0;

        public MyView(Context context) {
            super(context);
            setDisplaySize();
            p = new Paint();
            p.setColor(Color.BLUE);
            p.setTextSize(100);
            y = scrHeight*0.68f;

        }
        private void setDisplaySize(){
            Display display1 = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display1.getRealSize(size);
             scrWidth = size.x;
             scrHeight = size.y;
             w=scrWidth*0.05f;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        protected void onDraw(Canvas canvas) {


            p.setStrokeWidth(6);
            for (int i=1;i<6;i++) {
               points.add(new RectF(scrWidth*i*0.16f, y, scrWidth*i*0.16f+w, y + w));
            }
            canvas.drawRGB(23,23,23);
            canvas.drawLine(scrWidth*0.02f,scrHeight-scrHeight*0.3f,scrWidth*0.02f,0f,p);
            canvas.drawLine(scrWidth*0.02f,scrHeight-scrHeight*0.3f,scrWidth,scrHeight-scrHeight*0.3f,p);
            canvas.drawText("Start aprox", scrWidth*0.345f, scrHeight-scrHeight*0.215f, p);
            canvas.drawOval(points.get(0),p);
            canvas.drawOval(points.get(1),p);
            canvas.drawOval(points.get(2),p);
            canvas.drawOval(points.get(3),p);
            canvas.drawOval(points.get(4),p);
            for (int i = 1; i<8; i++) {

                canvas.drawLine(5, i*scrHeight*0.1f, 70, i*scrHeight*0.1f,p);
                canvas.drawLine(i*scrWidth*0.16f-scrWidth*0.138f, scrHeight-scrHeight*0.28f, i*scrWidth*0.16f-scrWidth*0.138f, scrHeight-scrHeight*0.32f,p);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // координаты Touch-события
            float evX = event.getX();
            float evY = event.getY();


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                  for(int i =0;i<5;i++) {
                      if (points.get(i).contains(evX, evY)) {
                          drag = true;
                          index = i;
                      }
                  }
                  if ((scrWidth*0.345f<evX)&&( evX< scrWidth*0.345f+scrWidth*0.4f)&&((scrHeight-scrHeight*0.215f<evY)&&(scrHeight-scrHeight*0.215f+120>evY))){
                      System.out.println(22);
                      getPoint();
                      startActivity(intent);
                  }
                    break;
                // тащим
                case MotionEvent.ACTION_MOVE:
                    if (drag) {
                        if (evY<scrHeight-scrHeight*0.32f){
                        points.get(index).top=evY - dragY;
                        points.get(index).bottom=points.get(index).top+w;
                        selectedPoints[0][index]=points.get(index).top;
                        invalidate();
                    }}
                    break;
                case MotionEvent.ACTION_UP:
                    drag = false;
                    index=5;
                    break;
            }
            return true;
        }
        public  void getPoint(){

            for (int i=0;i<5;i++) {
                if ( selectedPoints[1][i]==0)selectedPoints[1][i]=scrHeight-scrHeight*0.3f;
                selectedPoints[1][i]=(7-selectedPoints[0][i]/((scrHeight-scrHeight*0.3f)/7));
                if ( selectedPoints[1][i]==7.0)selectedPoints[1][i]=0.0f;
                System.out.println(selectedPoints[1][i]);


            }
        new Mnk();

            System.out.println(33);
             }

        }
    }


