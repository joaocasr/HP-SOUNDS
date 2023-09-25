package com.harrypotter.potteraudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button hogwarts,grimmauldplace,diagonalley;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(this,HogwartsExpressActivity.class);
        //startActivity(intent);

        hogwarts = findViewById(R.id.hogwartsbutton);
        grimmauldplace = findViewById(R.id.grimmauldplacebutton);
        diagonalley = findViewById(R.id.diagonalleybutton);

        hogwarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,HogwartsExpressActivity.class);
                startActivity(intent1);
                overridePendingTransition(0,0);
            }
        });
        grimmauldplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,GrimmauldPlaceEntranceActivity.class);
                startActivity(intent2);
                overridePendingTransition(0,0);
            }
        });
        diagonalley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this,TomsBarActivity.class);
                startActivity(intent3);
                overridePendingTransition(0,0);
            }
        });
    }
}