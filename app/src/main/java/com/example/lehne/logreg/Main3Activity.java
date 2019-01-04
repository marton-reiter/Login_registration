package com.example.lehne.logreg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private Button Button_kilep;
    private TextView udv;
    String adat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        init();
        final SharedPreferences sh = getSharedPreferences("adatok", Context.MODE_PRIVATE);
        adat = sh.getString("adat", null);
        udv.setText("Üdvözöllek "+adat);

        Button_kilep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kilep = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(kilep);
                finish();
            }
        });


    }

    public void init() {
        udv = (TextView) findViewById(R.id.udv);
        Button_kilep = (Button) findViewById(R.id.Button_kilep);


    }





}