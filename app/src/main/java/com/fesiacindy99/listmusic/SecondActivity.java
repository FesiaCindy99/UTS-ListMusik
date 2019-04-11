package com.fesiacindy99.listmusic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void openGithub(View view){
        Uri web = Uri.parse("https://github.com/FesiaCindy99");
        Intent intent = new Intent(Intent.ACTION_VIEW, web);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntens", "Can't handle this intent!");
        }
    }
}
