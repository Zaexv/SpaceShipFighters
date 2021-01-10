package com.dim.spaceshipfighters;

import android.widget.ImageView;

public class Shoot implements Runnable {

    private double x1,y1,x2,y2; //Parameters to create the line
    private ImageView bulletView;


    public Shoot(double x1, double y1, double x2, double y2, ImageView bulletView) {
        this.x1 = x1; //getX()
        this.y1 = y1; //getY()
        this.x2 = x2;
        this.y2 = y2;
        this.bulletView = bulletView;
    }

    @Override
    public void run() {
        int x = 0;

        while (x < 10000){
            shoot((float) (x*0.1));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x++;
        }
    }

    private double setShootLine(double x, double x1, double y1, double x2, double y2){
        return y1 + ((y2 - y1)/(x2 - x1))*(x - x1);
    }

    private double setShootLine2(double x, double x1, double y1, double x2, double y2){
        //Same as setShootLine with -m
        return y1 + (-(y2 - y1)/(x2 - x1))*(x - x1);
    }

    private void shoot(float x) {
        if(x1 >= x2){ //X is at Left from Ship
            bulletView.setX((float)x1 + x);
            bulletView.setY((float)setShootLine2(
                    x,x1,x2,x2,y2));
        } else if(x1 < x2){ //X is at Right from Ship
            bulletView.setX((float)x1 - x);
            bulletView.setY((float)setShootLine(
                    x, x1, x2 ,x2,y2));
        }
    }

}
