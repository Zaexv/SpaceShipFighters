package com.dim.spaceshipfighters;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpaceShipGame extends AppCompatActivity {

    private int numPlayers; //Stores num of players in-game
    ImageView
            spaceship1, spaceship2, spaceship3, spaceship4,
            bullet1,bullet2,bullet3,bullet4,
            shield1, shield2, shield3, shield4,
            doubleTapView;

    SpaceShip ship1, ship2, ship3,ship4;
    private ViewGroup mainLayout;
    GestureDetector gestureDetector;
    TextView debug;

    private int xDelta;
    private int yDelta;

    //Define Listener for every ImageViews.
    private OnTouchListener onTouchListener() {

        return new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                int index = event.getActionIndex();

                //If there is a gesture, get which View has invoked it.
                if (gestureDetector.onTouchEvent(event)) doubleTapView = (ImageView) view;

                switch (event.getActionMasked() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();
                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //This piece of code take the relative position in the layout
                        int location[] = {0,0};
                        view.getLocationOnScreen(location);
                        RelativeLayout.LayoutParams laParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();
                        int xp = (int) event.getX(index) + location[0];
                        int yp = (int) event.getY(index) + location[1];

                        xDelta = x - laParams.leftMargin;
                        yDelta = y - laParams.topMargin;

                        Set<SpaceShip> spaceShipSet = getSpaceShipSet();

                        //Get Closest ship to secondary finger and calculate shoot.
                        SpaceShip closest = getClosestShip(xp - xDelta,yp - yDelta);
                        if(closest.isActive()) closest.shoot((float)xp-xDelta,(float)yp-yDelta, spaceShipSet);
                        break;

                    case MotionEvent.ACTION_POINTER_UP:
                       // debug.setText("POINTER UP: " + ship.getName());
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_ship_game);
        //Get Num Players
        numPlayers = getIntent().getIntExtra("numPlayers", 1);
        gestureDetector = new GestureDetector(this,new GestureListener());

        //Debug View
        debug = (TextView) findViewById(R.id.debug);
        debug.setVisibility(View.INVISIBLE); //Comment this line to Debug

        //Defining SpaceShip Views
        spaceship1 = (ImageView) findViewById(R.id.imageP1);
        spaceship2 = (ImageView) findViewById(R.id.imageP2);
        spaceship3 = (ImageView) findViewById(R.id.imageP3);
        spaceship4 = (ImageView) findViewById(R.id.imageP4);

        //Defining Bullets
        bullet1 = (ImageView)findViewById(R.id.bulletP1);
        bullet1.setVisibility(View.INVISIBLE);
        bullet2 = (ImageView)findViewById(R.id.bulletP2);
        bullet2.setVisibility(View.INVISIBLE);
        bullet3 = (ImageView)findViewById(R.id.bulletP3);
        bullet3.setVisibility(View.INVISIBLE);
        bullet4 = (ImageView)findViewById(R.id.bulletP4);
        bullet4.setVisibility(View.INVISIBLE);

        //Defining Shields
        shield1 = (ImageView)findViewById(R.id.shieldP1);
        shield1.setVisibility(View.INVISIBLE);
        shield2 = (ImageView)findViewById(R.id.shieldP2);
        shield2.setVisibility(View.INVISIBLE);
        shield3 = (ImageView)findViewById(R.id.shieldP3);
        shield3.setVisibility(View.INVISIBLE);
        shield4 = (ImageView)findViewById(R.id.shieldP4);
        shield4.setVisibility(View.INVISIBLE);

        //Defining SpaceShip Object
        ship1 = new SpaceShip(spaceship1, bullet1, shield1);
        ship1.setName("F21");
        ship2 = new SpaceShip(spaceship2, bullet2, shield2);
        ship2.setName("Z8");
        ship3 = new SpaceShip(spaceship3, bullet3, shield3);
        ship3.setName("A7");
        ship4 = new SpaceShip(spaceship4, bullet4, shield4);
        ship4.setName("032");

        switch(numPlayers){
            case 2:
                spaceship2.setEnabled(false);
                spaceship2.setVisibility(View.INVISIBLE);
                spaceship4.setEnabled(false);
                spaceship4.setVisibility(View.INVISIBLE);
                spaceship1.setOnTouchListener(onTouchListener());
                spaceship3.setOnTouchListener(onTouchListener());
                ship1.setActive(true);
                ship3.setActive(true);
                break;
            case 3:
                spaceship2.setEnabled(false);
                spaceship2.setVisibility(View.INVISIBLE);
                spaceship1.setOnTouchListener(onTouchListener());
                spaceship3.setOnTouchListener(onTouchListener());
                spaceship4.setOnTouchListener(onTouchListener());
                ship1.setActive(true);
                ship3.setActive(true);
                ship4.setActive(true);
                ship3.setActive(true);
                break;
            case 4:
                spaceship1.setOnTouchListener(onTouchListener());
                spaceship2.setOnTouchListener(onTouchListener());
                spaceship3.setOnTouchListener(onTouchListener());
                spaceship4.setOnTouchListener(onTouchListener());
                ship1.setActive(true);
                ship2.setActive(true);
                ship3.setActive(true);
                ship4.setActive(true);
                break;
        }
        mainLayout = (RelativeLayout) findViewById(R.id.main);
    }

    //Gesture Detector. This only detects Double Taps.
    public class GestureListener extends
            GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        //
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            SpaceShip s = getShipFromView(doubleTapView);
            s.setShield(true);
            return true;
        }
    }

    /* ---------- AUXILIARY FUNCTIONS ------------------*/
    private Set<SpaceShip> getSpaceShipSet() {
        Set<SpaceShip> spaceShipSet = new HashSet<>();
        spaceShipSet.add(ship1);
        spaceShipSet.add(ship2);
        spaceShipSet.add(ship3);
        spaceShipSet.add(ship4);
        return spaceShipSet;
    }
    private SpaceShip getShipFromView(View v){
        SpaceShip result = new SpaceShip((ImageView)v);

        int viewid = v.getId();

        if(ship1.getViewID() == viewid) result = ship1;
        if(ship2.getViewID() == viewid) result = ship2;
        if(ship3.getViewID() == viewid) result = ship3;
        if(ship4.getViewID() == viewid) result = ship4;

        return result;
    }

    private SpaceShip getClosestShip(int px, int py){
        SpaceShip result = null;

        double dp1 = getDistanceBetweenPoints(px,py, ship1.getX(),ship1.getY());
        double dp2 = getDistanceBetweenPoints(px,py, ship2.getX(),ship2.getY());
        double dp3 = getDistanceBetweenPoints(px,py, ship3.getX(),ship3.getY());
        double dp4 = getDistanceBetweenPoints(px,py, ship4.getX(),ship4.getY());

        double min = Math.min(dp1,dp2);
        min = Math.min(min,dp3);
        min = Math.min(min, dp4);

        if(min == dp1 && ship1.isActive()) result = ship1;
        if(min == dp2 && ship2.isActive()) result = ship2;
        if(min == dp3 && ship3.isActive()) result = ship3;
        if(min == dp4 && ship4.isActive()) result = ship4;
        if(result == null) result = ship1;

        return result;
    }

    private double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

}