package br.com.diogomoreno.events.utils

import android.content.Intent

object PresentationUtils {
    fun shareIntent(content: String?): Intent {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, content)
        sendIntent.type = "text/plain"
        return Intent.createChooser(sendIntent, null)
    }
}