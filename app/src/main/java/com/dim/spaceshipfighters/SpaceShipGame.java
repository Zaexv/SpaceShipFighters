package com.dim.spaceshipfighters;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;
import android.widget.Space;
import android.widget.TextView;
import android.view.GestureDetector;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class SpaceShipGame extends AppCompatActivity //implements GestureDetector.OnGestureListener,
      // GestureDetector.OnDoubleTapListener{
{

    private int numPlayers; //Stores num of players in-game
    ImageView spaceship1, spaceship2, spaceship3, spaceship4, bullet1,bullet2,bullet3,bullet4,shield1;
    SpaceShip ship1, ship2, ship3,ship4;
    private ViewGroup mainLayout;
    TextView debug,debug2;
    Button button;
    GestureDetector gestureDetector;

    private int xDelta;
    private int yDelta;



    //Define Listener for Images.
    private OnTouchListener onTouchListener() {

        return new OnTouchListener() {



            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                int index = event.getActionIndex();
                int pointer = event.getPointerId(index);
                gestureDetector.onTouchEvent(event);
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                        view.getLayoutParams();
                SpaceShip ship = getShipFromView(view);



                switch (event.getActionMasked() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //This piece of code take the relative position in the layout
                        int location[] = {0,0};

                        view.getLocationOnScreen(location);
                        int xp = (int) event.getX(index) + location[0];
                        int yp = (int) event.getY(index) + location[1];

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;

                        ship1.shoot((float)xp-xDelta,(float)yp-yDelta);
                        SpaceShip closest = getClosestShip(xp - xDelta,yp - yDelta);

                        //TODO Crear vector de disparo y pintarlo


                        debug.setText(
                                        "SHIP1: " + ship1.getX()  + ship1.getY() + "\n"
                                        + "SHIP2: " + ship2.getX()  + ship2.getY()  + "\n"
                                        + "SHIP3: " + ship3.getX()  + ship3.getY()  + "\n"
                                        + "SHIP4: " + ship4.getX()  + ship4.getY()  + "\n"
                                        + "Event: " + (xp - xDelta) + " " + " " + (yp - yDelta) + "\n"
                                        + "Event0: " + (x - xDelta )+ " "+ " " + (y - yDelta) + "\n"
                                        + "Delta: " + xDelta+ " "+ " " + yDelta + "\n"
                                        + "SELECTED: " + ship.getName() + "\n"
                                        +" ClOSEST: " + closest.getName() + "\n"
                                        +" POINTER: " + pointer + "\n"
                        );
                        break;

                    case MotionEvent.ACTION_POINTER_UP:
                       // debug.setText("POINTER UP: " + ship.getName());
                        break;

                    case MotionEvent.ACTION_MOVE:
                        lParams.leftMargin = x - xDelta;
                        lParams.topMargin = y - yDelta;
                        lParams.rightMargin = 0;
                        lParams.bottomMargin = 0;
                        view.setLayoutParams(lParams);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }


        };

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_ship_game);
        numPlayers = getIntent().getIntExtra("numPlayers", 1);
        gestureDetector = new GestureDetector(this,new GestureListener());
        debug = (TextView) findViewById(R.id.debug); //View to Debug

        spaceship1 = (ImageView) findViewById(R.id.imageP1);
        spaceship2 = (ImageView) findViewById(R.id.imageP2);
        spaceship3 = (ImageView) findViewById(R.id.imageP3);
        spaceship4 = (ImageView) findViewById(R.id.imageP4);

        bullet1 = (ImageView)findViewById(R.id.bulletP1);

        //Defining SpaceShip Object
        ship1 = new SpaceShip(spaceship1, bullet1);
        ship1.setName("F21");
        ship2 = new SpaceShip(spaceship2);
        ship2.setName("Z8");
        ship3 = new SpaceShip(spaceship3);
        ship3.setName("A7");
        ship4 = new SpaceShip(spaceship4);
        ship4.setName("032");


        switch(numPlayers){
            case 2:
                spaceship2.setEnabled(false);
                spaceship2.setVisibility(View.INVISIBLE);
                spaceship4.setEnabled(false);
                spaceship4.setVisibility(View.INVISIBLE);
                break;
            case 3:
                spaceship2.setEnabled(false);
                spaceship2.setVisibility(View.INVISIBLE);
                break;
            case 4:
                break;
        }

        mainLayout = (RelativeLayout) findViewById(R.id.main);



        //TODO Mover a los correspondientes cases para evitar bugs.
        spaceship1.setOnTouchListener(onTouchListener());
        spaceship2.setOnTouchListener(onTouchListener());
        spaceship3.setOnTouchListener(onTouchListener());
        spaceship4.setOnTouchListener(onTouchListener());



        // Otro intento de double tap

        button = findViewById(R.id.button_Shield1);
        shield1 = findViewById(R.id.ship_shield1);



    }

    public SpaceShip getShipFromView(View v){
        SpaceShip result = new SpaceShip((ImageView)v); 

        int viewid = v.getId();

        if(ship1.getViewID() == viewid) result = ship1;
        if(ship2.getViewID() == viewid) result = ship2;
        if(ship3.getViewID() == viewid) result = ship3;
        if(ship4.getViewID() == viewid) result = ship4;

        return result;
    }

    public double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public SpaceShip getClosestShip(int px, int py){
        SpaceShip result = null;

        double dp1 = getDistanceBetweenPoints(px,py, ship1.getX(),ship1.getY());
        double dp2 = getDistanceBetweenPoints(px,py, ship2.getX(),ship2.getY());
        double dp3 = getDistanceBetweenPoints(px,py, ship3.getX(),ship3.getY());
        double dp4 = getDistanceBetweenPoints(px,py, ship4.getX(),ship4.getY());

        double min = Math.min(dp1,dp2);
        min = Math.min(min,dp3);
        min = Math.min(min, dp4);

        if(min == dp1) result = ship1;
        if(min == dp2) result = ship2;
        if(min == dp3) result = ship3;
        if(min == dp4) result = ship4;

        return result;



    }


    //gestureListener doubleTap
    public class GestureListener extends
            GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {


        debug.setText("Se detecto el double tap");


            return true;
        }
    }


}