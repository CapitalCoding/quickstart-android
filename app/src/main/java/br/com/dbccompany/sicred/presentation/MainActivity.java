package br.com.dbccompany.sicred.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.dbccompany.sicred.R;
import dagger.hilt.android.AndroidEntryPoint;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}