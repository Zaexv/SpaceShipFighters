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
        long endTime = System.currentTimeMillis() + 1000;
        long shootTime = System.currentTimeMillis() + 100;
        boolean reached = false;
        while(System.currentTimeMillis() < endTime && !reached) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(System.currentTimeMillis() > shootTime){
                for(SpaceShip s : spaceShipSet){
                    System.out.println("Nave Coord" + s.getX() + s.getY());
                   if(s.isActive() &&
                           s.isPointInShip(bulletView.getX(), bulletView.getY())) {
                       s.decreaseLife();
                       bulletView.setVisibility(View.INVISIBLE);
                       reached = true;
                       System.out.println("Le has dado a la nave " + s.getName());
                   }
                }
            }
        }
        shooter.setShooting(false);
    }

    public void start () {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
}
