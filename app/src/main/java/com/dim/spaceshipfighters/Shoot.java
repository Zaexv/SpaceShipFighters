package com.dim.spaceshipfighters;

import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Shoot implements Runnable {
    private SpaceShip shooter;
    private float posX,posY; //Parameters to create the line
    private ImageView bulletView;
    private static int speed = 10;
    private float angle;

    public Shoot(float posX, float posY, ImageView bulletView, float angle, SpaceShip shooter) {
        this.shooter = shooter;
        this.posX = posX;
        this.posY = posY;
        this.bulletView = bulletView;
        this.angle = angle;
    }

    @Override
    public void run() {
        int x = 0;
        while (x < 150) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("POSX: " + posX + " posY: " + posY + "ANGLE: " + angle);
            float posXT = (float) (posX - speed * x * 1 * cos(angle));
            float posYT = (float) (posY - speed * x * 1 * sin(angle));

            bulletView.setX(posXT);
            bulletView.setY(posYT);
            x++;
            posX = posXT;
            posY = posYT;
        }

        bulletView.setVisibility(View.INVISIBLE);
        bulletView.setX(0);
        bulletView.setY(0);
        shooter.setShooting(false);

    }

}
