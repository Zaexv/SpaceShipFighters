package com.dim.spaceshipfighters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


public class MainActivity extends AppCompatActivity {
    static final int TWOPLAYERS = 2;
    static final int THREEPLAYERS = 3;
    static final int FOURPLAYERS = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View v){
        final int id = v.getId();
        Intent intent = new Intent(MainActivity.this,SpaceShipGame.class);
        switch(id){
            case R.id.button2p:
                intent.putExtra("numPlayers", TWOPLAYERS);
                break;
            case R.id.button3p:
                intent.putExtra("numPlayers", THREEPLAYERS);
                break;
            case R.id.button4p:
                intent.putExtra("numPlayers", FOURPLAYERS);
                break;
        }
        startActivity(intent);
    }
}
