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

    public int setShootLine(int x, int x1, int y1, int x2, int y2){
       return y1 + ((y2 - y1)/(x2 - x1))*(x - x1);
    }

    public void shoot(){

        bulletView.setX(imageView.getX() + 50);
        bulletView.setY(imageView.getY() + 50);
    }
}
