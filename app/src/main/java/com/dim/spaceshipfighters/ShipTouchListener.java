package com.dim.spaceshipfighters;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class ShipTouchListener implements View.OnTouchListener {



    public ShipTouchListener() {

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

            final float x = event.getX();
            final float y =  event.getY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:
                    break;

                case MotionEvent.ACTION_MOVE:
                   view.setX(x);
                   view.setY(y);
                    break;
            }
          //  mainLayout.invalidate();
            return true;
        }
    }

