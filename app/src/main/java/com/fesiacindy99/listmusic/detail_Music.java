package com.fesiacindy99.listmusic;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fesiacindy99.listmusic.Model.Database;

public class detail_Music extends AppCompatActivity {
    Database database;
    String judul, penyanyi, rilis, pencipta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__music);

        Intent intent = getIntent();
        judul = intent.getStringExtra("judul");
        penyanyi = intent.getStringExtra("penyanyi");
        rilis = intent.getStringExtra("rilis");
        pencipta = intent.getStringExtra("pencipta");

        TextView tfJudul = (TextView) findViewById(R.id.tvJudul);
        TextView tfPenyanyi = (TextView) findViewById(R.id.tvPenyanyi);
        TextView tfRilis = (TextView) findViewById(R.id.tvRilis);
        TextView tfPencipta = (TextView) findViewById(R.id.tvPencipta);

        Button btnChange = (Button) findViewById(R.id.btnChange);
        Button btnDelOne = (Button) findViewById(R.id.btnDelOne);

        tfJudul.setText("Judul : " + judul);
        tfPenyanyi.setText("Penyanyi : " + penyanyi);
        tfRilis.setText("Rilis Tahun :" +  rilis);
        tfPencipta.setText("Pencipta : " + pencipta);

        database = new Database(this);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = detail_Music.this;
                Intent i = new Intent(context, Update_Music.class);
                i.putExtra("judul", judul);
                i.putExtra("penyanyi", penyanyi);
                i.putExtra("rilis", rilis);
                i.putExtra("pencipta", pencipta);
                context.startActivity(i);
                finish();
//                Toast.makeText(detailNote.this, "Id nya : " + id, Toast.LENGTH_SHORT).show();
            }
        });

        btnDelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbsql = database.getReadableDatabase();
                dbsql.delete("music","judul = '" + judul + "'",null);
                Toast.makeText(detail_Music.this,"Berhasil dihapus!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
