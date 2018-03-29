package com.example.admin.bouncingball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private int x, y, dx, dy, radius;



    public Ball(int x, int y, int dx, int dy, int radius) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void moving (){
        x+= dx;
        y+= dy;
    }

    public void hitAnotherBall (Ball anotherBall){
        if (Math.sqrt(Math.pow((this.x - anotherBall.x),2) + Math.pow((this.y - anotherBall.y),2)) < this.radius + anotherBall.radius - 8)
        {
           this.dx = - this.dx;
           anotherBall.dx = - anotherBall.dx;

        }
    }

    public void drawBall (Canvas canvas, Paint paint, String color) {
        paint.setColor(Color.parseColor(color));
        canvas.drawCircle(x, y, radius, paint);
    }

    public void drawBallBitmap (Canvas canvas, Bitmap bitmap){
        canvas.drawBitmap(bitmap, x, y, null);
    }

    public void checkTouchingBoundary (int canvasWidth, int canvasHeight){
        if (x> canvasWidth || x <0){
            dx = -dx;

        }
        if ( y < 0){
            dy = -dy;
        }

    }

    public void ballHitBar (Bar bar){
        if((bar.getX() < this.x && this.x < bar.getX() + bar.getWidth()) && (bar.getY() - radius < y && y < bar.getY()))
        {
            dy = -dy;
        }
    }
    public boolean ballHitBrick(Brick brick){

        if((brick.getX() < this.x && this.x < brick.getX() + brick.getWidth()) && (brick.getY() + brick.getHeight() < y && y < brick.getY() + brick.getHeight() + radius)){
            brick.setBroken(true);
            dy = -dy;

            return  true;
        }
        return  false;
    }


}
