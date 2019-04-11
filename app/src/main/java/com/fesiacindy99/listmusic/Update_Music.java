package com.fesiacindy99.listmusic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fesiacindy99.listmusic.Model.Database;

public class Update_Music extends AppCompatActivity {
    EditText tfJudul,tfPenyanyi,tfRilis, tfPencipta;
    Button btnSave;
    Database database;
    Cursor cursor;
    String judul, Penyanyi, Rilis, Pencipta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__music);

        Intent intent = getIntent();
        judul = intent.getStringExtra("judul");
        Penyanyi = intent.getStringExtra("penyanyi");
        Rilis = intent.getStringExtra("rilis");
        Pencipta = intent.getStringExtra("pencipta");

        tfJudul = (EditText) findViewById(R.id.tfJudulNew);
        tfPenyanyi = (EditText) findViewById(R.id.tfPenyanyiNew);
        tfRilis= (EditText) findViewById(R.id.tfRilisNew);
        tfPencipta= (EditText) findViewById(R.id.tfPenciptaNew);

        btnSave = (Button) findViewById(R.id.btnSave);

        tfJudul.setText(judul);
        tfPenyanyi.setText(Penyanyi);
        tfRilis.setText(Rilis);
        tfPencipta.setText(Pencipta);

        database = new Database(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbnya = database.getWritableDatabase();
                dbnya.execSQL("update music set judul='"+ tfJudul.getText() +"', penyanyi='"+ tfPenyanyi.getText() +"', rilis='"+ tfRilis.getText() +"', pencipta='"+ tfPencipta.getText() +"' where judul='"+ judul +"'");
                finish();
                Toast.makeText(Update_Music.this,"OK",Toast.LENGTH_LONG).show();
            }
        });
    }
}
