package com.dim.spaceshipfighters;

import android.view.View;
import android.widget.ImageView;

//This is the SpaceShip class used.
public class SpaceShip {

    private static int MAX_LIFES;

    private int lifes;
    private int pointerused;
    private boolean shield;
    private ImageView imageView;
    private String name;

    public SpaceShip(ImageView view){
        name = "default";
        imageView = view;
        lifes = MAX_LIFES;
        shield = false;
        pointerused = -1;
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



}
