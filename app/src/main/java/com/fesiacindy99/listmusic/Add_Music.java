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

public class Add_Music extends AppCompatActivity {

    EditText tfJudul,tfPenyanyi,tfRilis, tfPencipta;
    Button btnSimpan;
    Database database;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__music);

        database = new Database(this);

        tfJudul = (EditText) findViewById(R.id.tfJudulAdd);
        tfPenyanyi = (EditText) findViewById(R.id.tfPenyanyiAdd);
        tfRilis= (EditText) findViewById(R.id.tfRilisAdd);
        tfPencipta= (EditText) findViewById(R.id.tfPeciptaAdd);

        btnSimpan = (Button) findViewById(R.id.btnAddMusic);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbnya = database.getWritableDatabase();
                dbnya.execSQL("insert into music(judul, penyanyi, rilis, pencipta) values('" + tfJudul.getText().toString() + "','" + tfPenyanyi.getText().toString() + "','" + tfRilis.getText().toString() + "','" + tfPencipta.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil Menambah List Musik", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Add_Music.this, ThirdActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
