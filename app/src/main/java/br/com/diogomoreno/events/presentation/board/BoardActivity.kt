package br.com.diogomoreno.events.presentation.board

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.diogomoreno.events.presentation.board.ui.BoardFragment
import br.com.flasco.leanworks.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BoardFragment.Companion.newInstance())
                    .commitNow()
        }
    }
}