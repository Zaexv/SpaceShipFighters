package com.dim.spaceshipfighters;

import android.view.View;
import android.widget.ImageView;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

//This is the SpaceShip class used.
public class SpaceShip {

    private static int MAX_LIFES;

    private int lifes;
    private int pointerused;
    private boolean shield, shooting;
    private ImageView imageView, bulletView;
    private String name;

    public SpaceShip(ImageView view){
        name = "default";
        imageView = view;
        lifes = MAX_LIFES;
        shooting = false;
        shield = false;
        pointerused = -1;
    }

    public SpaceShip(ImageView view , ImageView bulletView){
        name = "default";
        imageView = view;
        lifes = MAX_LIFES;
        shooting = false;
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

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public void shoot(float x2, float y2) {

        if(!shooting) {
            float centreX = imageView.getX() - imageView.getWidth() / 2;
            float centreY = imageView.getY() + imageView.getHeight() / 2;
            float deltaX = (float) (x2 - centreX);
            float deltaY = (float) (y2 - centreY);
            float angle = (float) atan2(deltaY, deltaX);
            float speed = 10;
            float degrees = (float) ((angle > 0 ? angle : (2 * PI + angle)) * 360 / (2 * PI));

            float posX = (float) (centreX - speed * 20 * cos(angle));
            float posY = (float) (centreY - speed * 20 * sin(angle));
            //System.out.println(angle);
            bulletView.setX(posX);
            bulletView.setY(posY);

            bulletView.setVisibility(View.VISIBLE);
            Shoot s = new Shoot(posX, posY, bulletView, angle, this);
            setShooting(true);
            Thread t = new Thread(s);
            t.start();

        }
    }
}