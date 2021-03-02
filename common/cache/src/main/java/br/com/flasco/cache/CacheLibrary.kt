package br.com.flasco.cache

import android.content.Context
import com.pacoworks.rxpaper2.RxPaperBook

object CacheLibrary {
    @JvmStatic fun init(context: Context) = RxPaperBook.init(context)
}
