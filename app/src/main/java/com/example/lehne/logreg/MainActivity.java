package com.example.lehne.logreg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Edit_Text_nev, Edit_Text_passw;
    private Button Button_belep, Button_reg;
    private adatbazis db;
    boolean beenged =false;
    String adat;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    init();

        Button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent regist = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(regist);
                finish();
            }
        });



        Button_belep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adatLekeres();

                final SharedPreferences sh = getSharedPreferences("adatok", Context.MODE_PRIVATE);
                String adat = user.toString();
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("adat", adat);
                editor.apply();

                if(beenged) {
                    Intent belepes = new Intent(MainActivity.this, Main3Activity.class);
                    startActivity(belepes);
                    finish();
                }
            }
        });

    }

    public void init()
    {
        Edit_Text_nev = (EditText) findViewById(R.id.Edit_Text_nev);
        Edit_Text_passw = (EditText) findViewById(R.id.Edit_Text_passw);
        Button_reg = (Button) findViewById(R.id.Button_reg);
        Button_belep = (Button) findViewById(R.id.Button_belep);
        db = new adatbazis(this);
    }


    public void adatLekeres()
    {
        Cursor eredmeny = db.adatLekerdezes();

        user = Edit_Text_nev.getText().toString();
        String passw = Edit_Text_passw.getText().toString();
        beenged=false;
        if (eredmeny!=null && eredmeny.getCount()>0) {
            while (eredmeny.moveToNext()) {
                String er1 = eredmeny.getString(1);
                String er2 = eredmeny.getString(2);
                if (er1.equals(user) && er2.equals(passw)) {
                    beenged = true;
                }

            }
        }
        if(!beenged) {Toast.makeText(this, "Hibás felhasználónév vagy jelszó!", Toast.LENGTH_SHORT).show();}
        }

    }












