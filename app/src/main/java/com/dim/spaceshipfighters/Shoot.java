package com.dim.spaceshipfighters;

import android.animation.AnimatorSet;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Shoot implements Runnable {
    private ImageView bulletView;
    private SpaceShip shooter;
    private AnimatorSet set;
    Thread t;


    public Shoot(ImageView bulletView, SpaceShip shooter, AnimatorSet set) {
        this.shooter = shooter;
        this.bulletView = bulletView;
      }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shooter.setShooting(false);
        System.out.println(bulletView.getX()+  ", "+ bulletView.getY());
    }

    public void start () {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}
