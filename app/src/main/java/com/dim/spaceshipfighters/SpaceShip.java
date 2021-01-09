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
       double div = (y2 - y1)/(x2 - x1);

       System.out.println(y1 + " + " + div + "*" + (x-x1)) ;
        return y1 + ((y2 - y1)/(x2 - x1))*(x - x1);
    }

    public void shoot(float x2, float y2) {

                if(getX() > x2){

                    bulletView.setX(imageView.getX() + 250);
                    System.out.println("YValueSet" + (float)setShootLine(
                            50,x2, y2 ,getX(),getY()));
                    bulletView.setY((float)setShootLine(
                            50, getX(), getY() ,x2,y2));
                } else if(getX() < x2){

                    bulletView.setX(imageView.getX() - imageView.getWidth() - 250);
                    System.out.println("YValueSet" + (float)setShootLine(
                            -50,x2, y2 ,getX(),getY()));
                    bulletView.setY((float)setShootLine(
                            -50, getX(), getY() ,x2,y2));
                }
        }

}

