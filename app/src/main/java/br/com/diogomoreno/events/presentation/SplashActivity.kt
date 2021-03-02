package br.com.diogomoreno.events.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.diogomoreno.events.presentation.board.BoardActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, BoardActivity::class.java))
        finish()
    }
}