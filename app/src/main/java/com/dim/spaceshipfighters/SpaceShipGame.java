package com.dim.spaceshipfighters;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;

public class SpaceShipGame extends AppCompatActivity {

    private int numPlayers; //Stores num of players in-game
    ImageView spaceship1, spaceship2, spaceship3, spaceship4;
    private ViewGroup mainLayout;


    private int xDelta;
    private int yDelta;


    private OnTouchListener onTouchListener() {
        return new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_ship_game);
        numPlayers = getIntent().getIntExtra("numPlayers", 1);

        spaceship1 = (ImageView) findViewById(R.id.imageP1);
        spaceship2 = (ImageView) findViewById(R.id.imageP2);
        spaceship3 = (ImageView) findViewById(R.id.imageP3);
        spaceship4 = (ImageView) findViewById(R.id.imageP4);


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


        spaceship1.setOnTouchListener(onTouchListener());
        spaceship2.setOnTouchListener(onTouchListener());
        spaceship3.setOnTouchListener(onTouchListener());
        spaceship4.setOnTouchListener(onTouchListener());

        //INICIALIZAR DISTINTAS NAVES SEGUN NUM JUGADORES
        /*
        TextView players = (TextView)findViewById(R.id.textView2);
        players.setText(Integer.toString(numPlayers));
         */

    }
}