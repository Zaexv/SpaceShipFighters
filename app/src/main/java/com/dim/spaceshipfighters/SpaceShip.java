package com.dim.spaceshipfighters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

//This is the SpaceShip class used.
public class SpaceShip {

    private static int MAX_LIFES;

    private int lifes;
    private Shoot shoot;
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

    public Shoot getShoot() {
        return shoot;
    }


    public void shoot(float x2, float y2) {
        if(!shooting) {
            bulletView.setVisibility(View.VISIBLE);
            setShooting(true);
            float centreX = imageView.getX() - imageView.getWidth() / 2;
            float centreY = imageView.getY() + imageView.getHeight() / 2;
            float deltaX = (float) (x2 - centreX);
            float deltaY = (float) (y2 - centreY);
            float angle = (float) atan2(deltaY, deltaX);
            float speed = 10;
            float degrees = (float) ((angle > 0 ? angle : (2 * PI + angle)) * 360 / (2 * PI));
            float posX = (float) (centreX - speed * 1000 * cos(angle));
            float posY = (float) (centreY - speed * 1000 * sin(angle));
            //System.out.println(angle);
            bulletView.setX(posX);
            bulletView.setY(posY);

            //Shoot aniation;
            ObjectAnimator animation1;
            ObjectAnimator animation2;
            animation1 = ObjectAnimator.ofFloat(bulletView, "x", centreX, posX);
            animation2 = ObjectAnimator.ofFloat(bulletView, "y", centreY, posY);
            AnimatorSet set = new AnimatorSet();
            set.setDuration(1000);
            set.playTogether(animation1, animation2);
            set.start();
            shoot = new Shoot(bulletView, this, set);
            shoot.start();
        }
    }


}