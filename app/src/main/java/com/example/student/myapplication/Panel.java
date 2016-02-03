package com.example.student.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//suit 1 = clubs
//suit 2 = hearts
//suit 3 = spades
//suit 4 = diamonds

public class Panel extends SurfaceView implements SurfaceHolder.Callback{
    //GLOBAL VARIABLES
    Paint paint;
    private CanvasThread canvasthread;
    int localscore;
    ////////////////

CardDraw carddraw;

    public Panel(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        getHolder().addCallback(this);
        canvasthread = new CanvasThread(getHolder(), this);
        setFocusable(true);
        paint = new Paint();
        carddraw = new CardDraw(context);

    }




    public Panel(Context context) {
        super(context);
        getHolder().addCallback(this);
        canvasthread = new CanvasThread(getHolder(), this);
        setFocusable(true);

    }


    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        for (int q = 0; q<=1; q++){
            carddraw.deal(canvas, q, (80*q), -600);

            if (GetterSetter.buttonpressed == 1){
                scoreit(q, true, false);
            }
        }


        for(int q = 0; q <= GetterSetter.hit; q++){
            carddraw.deal(canvas, q, (80*q), 0);

            if (GetterSetter.buttonpressed == 1){
                scoreit(q, false, true);
            }
        }

        for(int x = (GetterSetter.hit + 1); x <= GetterSetter.dealerhit; x++){
            carddraw.deal(canvas, x, (80*x), -600);
            if (GetterSetter.buttonpressed == 1) {
                scoreit(x, true, false);
            }
        }

        GetterSetter.buttonpressed = 0;
        //carddraw.deal(canvas);

    }

    public void scoreit(int q, boolean dealer, boolean player){

        if (GetterSetter.card[q].rank == 12){
            localscore = 11;
        }else if (GetterSetter.card[q].rank >= 8){
            localscore = 10;
        }else{
            localscore = GetterSetter.card[q].rank + 2;
        }

        if(player) {
            GetterSetter.playerScore = GetterSetter.playerScore + localscore;
        }
        if(dealer) {
            GetterSetter.dealerScore = GetterSetter.dealerScore + localscore;
        }
        //localscore = 0;
    }

    public void update() {


    }



    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        canvasthread.setRunning(true);
        canvasthread.start();


    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        boolean retry = true;
        canvasthread.setRunning(false);
        while (retry) {
            try {
                canvasthread.join();
                retry = false;
            } catch (InterruptedException e) {
                // we will try it again and again...
            }
        }





    }


}