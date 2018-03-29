package com.example.admin.bouncingball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by PC on 3/23/2018.
 */

public class Bar {
    private int x,y,height,width;

    public Bar(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void drawPaddle(Canvas canvas, Paint paint, String color){
        paint.setColor(Color.parseColor(color));
        canvas.drawRect(new RectF(x, y, x + width, y + height), paint);
    }

}
