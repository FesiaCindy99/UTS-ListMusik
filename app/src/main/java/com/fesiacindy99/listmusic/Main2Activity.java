package com.fesiacindy99.listmusic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fesiacindy99.listmusic.Model.Database;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    public List<User> user;
    public EditText tfName,tfPass;
    public Button btnLogin;
    Database database;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        session = new Session(this);

        if (session.getSPSudahLogin()){
            startActivity(new Intent(Main2Activity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        addUser();

        tfName = (EditText) findViewById(R.id.tfNama);
        tfPass = (EditText) findViewById(R.id.tfPassword);
        btnLogin = (Button) findViewById(R.id.btnMasuk);

        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            User tmpUser = null;
            for(User u:user){
                if(u.getNama().equals(tfName.getText().toString()) && u.getPassword().equals(tfPass.getText().toString())){
                    tmpUser = u;
                }
            }

            if(tmpUser != null){
                SharedPreferences spPengguna = Main2Activity.this.getSharedPreferences("Userlogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = spPengguna.edit();

                edit.putString("sedangLogin",tmpUser.getNama());
                edit.apply();

                session.saveSPBoolean(Session.SP_SUDAH_LOGIN, true);

                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(Main2Activity.this, "Username atau Password Salah", Toast.LENGTH_LONG).show();
            }
        }
    });

        database = new Database(this);
    }

    public void addUser() {
        user = new ArrayList<>();
        user.add(new User("cindy", "123"));
    }
}
