package com.dim.spaceshipfighters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SpaceShipGame extends AppCompatActivity {

    private int numPlayers; //Stores num of players in-game

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_ship_game);
        numPlayers = getIntent().getIntExtra("numPlayers", 1);

        //INICIALIZAR DISTINTAS NAVES SEGUN NUM JUGADORES
        TextView players = (TextView)findViewById(R.id.textView2);
        players.setText(Integer.toString(numPlayers));

    }
}