package br.com.diogomoreno.events.presentation.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.flasco.leanworks.R;
import br.com.diogomoreno.events.presentation.event.ui.EventDetailFragment;
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