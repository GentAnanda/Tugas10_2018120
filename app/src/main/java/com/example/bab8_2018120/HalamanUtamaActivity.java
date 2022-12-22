package com.example.bab8_2018120;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.example.bab8_2018120.databinding.ActivityHalamanUtamaBinding;

public class HalamanUtamaActivity extends AppCompatActivity {

    private ActivityHalamanUtamaBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup view binding
        binding =
                ActivityHalamanUtamaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = getSharedPreferences("AndroidHiveLogin",
                0);
        editor = preferences.edit();
        binding.buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}