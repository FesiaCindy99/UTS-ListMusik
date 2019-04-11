package com.fesiacindy99.listmusic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fesiacindy99.listmusic.Model.Database;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MusicAdapter adapter;
    ArrayList<Music> music;
    Database database;
    Cursor cursor;
    TextView tvMusic;
    ImageButton btnAdd, btnDelete;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        database = new Database(this);

        SharedPreferences spUser = ThirdActivity.this.getSharedPreferences("Userlogin", Context.MODE_PRIVATE);
        String namaSedangLogin = spUser.getString("sedangLogin", "Defaultnya Manusia");

        TextView text = (TextView) findViewById(R.id.tfUserLogin);
        TextView rvMusic = (TextView) findViewById(R.id.rvMusic);

        text.setText("You : " + namaSedangLogin);

        recyclerView = (RecyclerView) findViewById(R.id.rvMusic);
        tvMusic = (TextView) findViewById(R.id.tvMusic);
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnDelete= (ImageButton) findViewById(R.id.btnDelete);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, Add_Music.class);
                startActivity(intent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbsql = database.getReadableDatabase();
                String sql = "delete from music";
                dbsql.execSQL(sql);
                addDataMusic();
            }
        });

        addDataMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDataMusic();
    }

    private void addDataMusic() {
        music = new ArrayList<>();
        SQLiteDatabase dbsql = database.getReadableDatabase();
        cursor = dbsql.rawQuery("SELECT * FROM music",null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do {

                music.add(new Music(cursor.getString(cursor.getColumnIndex("judul")),cursor.getString(cursor.getColumnIndex("penyanyi")),cursor.getString(cursor.getColumnIndex("rilis")),cursor.getString(cursor.getColumnIndex("pencipta"))));

            } while (cursor.moveToNext());
        }

        adapter = new MusicAdapter(music);

        layoutManager = new LinearLayoutManager(ThirdActivity.this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(layoutManager);

        if(music.size() > 0){
            this.tvMusic.setText("Jumlah Musik : " + music.size());
        }else{
            adapter.notifyDataSetChanged();
            this.tvMusic.setText("Tidak ada Musik untuk ditampilkan. : ");
        }
    }
}
