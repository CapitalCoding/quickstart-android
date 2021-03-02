package br.com.diogomoreno.events.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.diogomoreno.events.presentation.board.BoardActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, BoardActivity.class));
        finish();
    }
}
