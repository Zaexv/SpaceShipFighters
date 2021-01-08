package com.dim.spaceshipfighters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SpaceShipGame extends AppCompatActivity {

    private int numPlayers; //Stores num of players in-game
    ImageView spaceship1, spaceship2, spaceship3, spaceship4;
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

        //INICIALIZAR DISTINTAS NAVES SEGUN NUM JUGADORES
        /*
        TextView players = (TextView)findViewById(R.id.textView2);
        players.setText(Integer.toString(numPlayers));
         */

    }
}