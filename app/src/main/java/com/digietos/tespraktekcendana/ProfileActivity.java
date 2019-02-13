package com.digietos.tespraktekcendana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvNama,tvUsername,tvEmail;
    private String name,username,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvNama = findViewById(R.id.profileNamaTv);
        tvUsername = findViewById(R.id.profileUsernameTv);
        tvEmail = findViewById(R.id.profileEmailTv);

        name = getIntent().getStringExtra("name");
        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");

        tvNama.setText(name);
        tvUsername.setText(username);
        tvEmail.setText(email);
    }
}
