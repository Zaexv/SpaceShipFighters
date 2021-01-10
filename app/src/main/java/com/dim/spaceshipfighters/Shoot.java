package com.dim.spaceshipfighters;

import android.animation.AnimatorSet;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Shoot implements Runnable {

    //Shoot Game Variables
    public static int MAX_END_TIME = 1000;
    public static int SHOOT_TIME = 100;
    public static int RENDER_WAIT_TIME = 10;

    private ImageView bulletView;
    private SpaceShip shooter;
    private Set<SpaceShip> spaceShipSet;
    Thread t;


    public Shoot(ImageView bulletView, SpaceShip shooter, Set<SpaceShip> spaceShipSet) {
        this.shooter = shooter;
        this.bulletView = bulletView;
        this.spaceShipSet = spaceShipSet;
      }

    @Override
    public void run() {
        long endTime = System.currentTimeMillis() + MAX_END_TIME;
        long shootTime = System.currentTimeMillis() + SHOOT_TIME;
        boolean reached = false;
        while(System.currentTimeMillis() < endTime && !reached) {
            try {
                Thread.sleep(RENDER_WAIT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(System.currentTimeMillis() > shootTime){
                for(SpaceShip s : spaceShipSet){
                    if(s.isActive() &&
                           s.isPointInShip(bulletView.getX(), bulletView.getY())) {
                       s.decreaseLife();
                       bulletView.setVisibility(View.INVISIBLE);
                       reached = true;
                       System.out.println("Ship Shooted: " + s.getName());
                   }
                }
            }
        }
        shooter.setShooting(false);
    }

    //Starts Process Safely
    public void start () {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}
