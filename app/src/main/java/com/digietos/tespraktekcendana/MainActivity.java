package com.digietos.tespraktekcendana;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.digietos.tespraktekcendana.api.ApiInterface;
import com.digietos.tespraktekcendana.api.ApiService;
import com.digietos.tespraktekcendana.model.User;
import com.digietos.tespraktekcendana.model.Users;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    private DatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private ListUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycerView);

        apiInterface = ApiService.getService().create(ApiInterface.class);
        dbHelper = new DatabaseHelper(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        List<User> users = dbHelper.getUsers();
        Log.d("Users",users.toString());
        adapter = new ListUserAdapter(this,users);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(){
        retrofit2.Call<List<User>> usersCall = apiInterface.getUsers();
        usersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(retrofit2.Call<List<User>> call, Response<List<User>> response) {
                List<User> list = response.body();
                Log.d("Nama",list.get(0).getName());
                dbHelper.addDataUser(list);
            }

            @Override
            public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {
                Log.d("Fail",t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
