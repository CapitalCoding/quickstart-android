package br.com.dbccompany.sicred.presentation.event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;

import br.com.dbccompany.sicred.R;
import br.com.dbccompany.sicred.presentation.event.ui.EventDetailFragment;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_activity);
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EventDetailFragment.newInstance(bundle != null ? bundle.getString("event_id") : ""))
                    .commitNow();
        }
    }

}