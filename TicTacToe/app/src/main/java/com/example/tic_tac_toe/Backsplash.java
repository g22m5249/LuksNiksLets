package com.example.clickonxno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Backsplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backsplash);

        Intent main = new Intent(Backsplash.this, MainMenu.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(main);
            }
        }, 4000);
    }
}
