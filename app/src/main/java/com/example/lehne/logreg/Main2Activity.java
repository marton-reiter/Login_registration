package com.example.lehne.logreg;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText Edit_Text_nev2, Edit_Text_passw2 , Edit_Text_passw_ujra, Edit_Text_teljes_nev, Edit_Text_telo;
    private Button Button_reg2;
    private adatbazis db;
    boolean ok=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        init();

        Button_reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    addUser();
                    if(ok) {
                        Intent vissza = new Intent(Main2Activity.this, MainActivity.class);
                        startActivity(vissza);
                        finish();
                    }

            }
        });

    }
    public void init()
    {
        Edit_Text_nev2 = (EditText) findViewById(R.id.Edit_Text_nev2);
        Edit_Text_passw2 = (EditText) findViewById(R.id.Edit_Text_passw2);
        Edit_Text_teljes_nev = (EditText) findViewById(R.id.Edit_Text_teljes_nev);
        Edit_Text_passw_ujra = (EditText) findViewById(R.id.Edit_Text_passw_ujra);
        Edit_Text_telo = (EditText) findViewById(R.id.Edit_Text_telo);
        Button_reg2 = (Button) findViewById(R.id.Button_reg2);
        db = new adatbazis(this);
    }

    public void addUser() {
        ok=true;
        String user = Edit_Text_nev2.getText().toString();
        adatLekeres();
        String passw = Edit_Text_passw2.getText().toString();
        String passw_ujra = Edit_Text_passw_ujra.getText().toString();

        if (!passw.equals(passw_ujra)) {
            Toast.makeText(this, "A jelszavaknak egyeznie kell", Toast.LENGTH_SHORT).show();
            ok = false;
        }
        String nev = Edit_Text_teljes_nev.getText().toString();
        String telo = Edit_Text_telo.getText().toString();

        if (user.equals("")||passw.equals("")||passw_ujra.equals("")||nev.equals("")||telo.equals("")) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
            ok = false;
        }


        if (ok) {
            boolean eredmeny = db.addUser(user, passw, nev, telo);
            if (eredmeny) {
                Toast.makeText(this, "Sikeres adatrögzítés", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void adatLekeres()
    {
        Cursor eredmeny = db.adatLekerdezes();

        String user = Edit_Text_nev2.getText().toString();
        if (eredmeny!=null && eredmeny.getCount()>0) {
            while (eredmeny.moveToNext()) {
                String er1 = eredmeny.getString(1);
                if (er1.equals(user)) {
                    ok=false;
                    Toast.makeText(this, "Ez a felhasználónév már foglalt!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}



