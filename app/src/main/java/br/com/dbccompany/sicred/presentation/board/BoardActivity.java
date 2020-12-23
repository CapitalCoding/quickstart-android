package br.com.dbccompany.sicred.presentation.board;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.dbccompany.sicred.R;
import br.com.dbccompany.sicred.presentation.board.ui.BoardFragment;
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