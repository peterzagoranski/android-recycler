package com.github.peterzagoranski.recycler.example;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.peterzagoranski.recycler.Recycler;
import com.github.peterzagoranski.recycler.example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Recycler.with(null).grid(2,4).reverse().into(binding.recycler);
    }
}
