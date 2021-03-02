package br.com.diogomoreno.events.presentation.event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.diogomoreno.events.presentation.event.ui.EventDetailFragment
import br.com.flasco.leanworks.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_detail_activity)
        if (savedInstanceState == null) {
            val bundle = intent.extras
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, EventDetailFragment.Companion.newInstance(if (bundle != null) bundle.getString("event_id") else ""))
                    .commitNow()
        }
    }
}