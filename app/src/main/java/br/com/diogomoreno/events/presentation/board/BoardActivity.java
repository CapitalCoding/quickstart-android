package br.com.diogomoreno.events.presentation.board;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.flasco.leanworks.R;
import br.com.diogomoreno.events.presentation.board.ui.BoardFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BoardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, BoardFragment.newInstance())
                    .commitNow();
        }
    }
}