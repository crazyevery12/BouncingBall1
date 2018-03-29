package com.example.admin.bouncingball;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyView extends View implements Runnable {


    Paint paint;
    int radius = 80;
    int vary = 72;
    Bitmap ballResize1, ballResize2, wallpaper;
    private int xHandle = 10, yHandle = 20, width = 160, height = 15;
    Ball ballOne,ballTwo;
    Bar bar;
    Brick brick;
    ArrayList<Brick> lists;


    private SoundManager soundManager;



    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ballOne = new Ball (100, 700, 5, 5, 20);
        ballTwo = new Ball (110, 700, 15, 15, 20);


        bar = new Bar(250,950,40,200);
        lists = new ArrayList<Brick>();

        for(int i = 0; i < 3 ; i++){
            brick = new Brick(245 * i ,0,70,240);
            lists.add(brick);
        }
        for(int i = 0; i < 6 ; i++){
            brick = new Brick(125 * i ,75,70,120);
            lists.add(brick);
        }
        for(int i = 0; i < 12 ; i++){
            brick = new Brick(65 * i ,150,70,60);
            lists.add(brick);
        }

        wallpaper = BitmapFactory.decodeResource(getResources(), R.drawable.milkyway);

        soundManager = SoundManager.getInstance();
        soundManager.init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();



        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.WHITE);
//        canvas.drawPaint(paint);
        canvas.drawBitmap(wallpaper,0, 0, null);
        // Use Color.parseColor to define HTML colors
//        paint.setColor(Color.parseColor("#3e70c1"));
//        canvas.drawBitmap(wallpaper, 0, 0, null);

        bar.drawPaddle(canvas,paint,"#ccccb3");

        ballOne.drawBall(canvas,paint,"#CD5C5C");
        ballTwo.drawBall(canvas,paint,"#CD5C5C");


        ballOne.moving();
        ballTwo.moving();


        ballOne.checkTouchingBoundary(this.getWidth(),this.getHeight());
        ballTwo.checkTouchingBoundary(this.getWidth(),this.getHeight());

        ballOne.ballHitBar(bar);
        ballTwo.ballHitBar(bar);

        ballOne.hitAnotherBall(ballTwo);



        for (Brick brick : lists){
            brick.drawBrick(canvas,paint,"#00cccc");

            if(!brick.isBroken()){
                if(ballOne.ballHitBrick(brick) || ballTwo.ballHitBrick(brick)) {
                    soundManager.playSound(R.raw.explode);
                }
            }




        }

//        // Use Color.parseColor to define HTML colors
//        paint.setColor(Color.parseColor("#CD5C5C"));
//
//        ballTwo.drawBall(canvas, ballResize2);
//
//        handle.setX(this.getWidth() / 2 - handle.getWidth()/2);
//        handle.setY(this.getHeight()- 100);
//        paint.setColor(Color.parseColor("#a58718"));
//        handle.drawHandle(canvas, paint);


        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        boolean handled = false;

        int xTouch;
        int yTouch;
        int actionIndex = event.getActionIndex();


        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                xTouch = (int) event.getX(0);
                yTouch = (int) event.getY(0);



                handled = true;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                xTouch = (int) event.getX(actionIndex);
                yTouch = (int) event.getY(actionIndex);


                handled = true;
                break;

            case MotionEvent.ACTION_MOVE:
                final int pointerCount = event.getPointerCount();

                for (actionIndex = 0; actionIndex < pointerCount; actionIndex++) {

                    xTouch = (int) event.getX(actionIndex);
                    yTouch = (int) event.getY(actionIndex);

                    bar.setX(xTouch);

                }


                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_UP:

                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_POINTER_UP:

                invalidate();
                handled = true;
                break;

            case MotionEvent.ACTION_CANCEL:

                handled = true;
                break;

            default:
                // do nothing
                break;
        }

        return super.onTouchEvent(event) || handled;
    }


    @Override
    public void run() {

    }
}