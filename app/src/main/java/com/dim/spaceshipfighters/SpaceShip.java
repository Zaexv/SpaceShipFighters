package com.dim.spaceshipfighters;

import android.view.View;
import android.widget.ImageView;

//This is the SpaceShip class used.
public class SpaceShip {

    private static int MAX_LIFES;

    private int lifes;
    private int pointerused;
    private boolean shield;
    private ImageView imageView, bulletView;
    private String name;

    public SpaceShip(ImageView view){
        name = "default";
        imageView = view;
        lifes = MAX_LIFES;
        shield = false;
        pointerused = -1;
    }

    public SpaceShip(ImageView view , ImageView bulletView){
        name = "default";
        imageView = view;
        lifes = MAX_LIFES;
        shield = false;
        pointerused = -1;
        this.bulletView = bulletView;
    }

    public void setPointer(int pointer){
        pointerused = pointer;
    }
    public int getPointer(){
       return this.pointerused;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getViewID(){
        return imageView.getId();
    }

    public double getX(){
        return this.imageView.getX();
    }

    public double getY(){
        return this.imageView.getY();
    }

    public double setShootLine(double x, double x1, double y1, double x2, double y2){
        return y1 + ((y2 - y1)/(x2 - x1))*(x - x1);
    }

    public double setShootLine2(double x, double x1, double y1, double x2, double y2){
        //Same as setShootLine with -m
        return y1 + (-(y2 - y1)/(x2 - x1))*(x - x1);
    }

    public void shoot(float x2, float y2) {
                //TODO
        /*if(getX() >= x2){ //X is at Left from Ship
                        bulletView.setX(imageView.getX() + 250);
                        bulletView.setY((float)setShootLine2(
                                50,getX(),getY(),x2,y2));
                } else if(getX() < x2){ //X is at Right from Ship
                    bulletView.setX(imageView.getX() - imageView.getWidth() - 250);
                    bulletView.setY((float)setShootLine(
                            -50, getX(), getY() ,x2,y2));
                }

         */

        Shoot s = new Shoot(getX(),getY(),x2,y2,bulletView);
        Thread t = new Thread(s);
        t.start();
        }
}