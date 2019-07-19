package door.cyron.house.housedoor

import android.app.Application
import android.content.Context
import door.cyron.house.housedoor.retrofit.RetrofitClient


class HomeApplication : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        val context: Context = HomeApplication.applicationContext()
        RetrofitClient.create(cacheDir)
    }

    companion object {
        private var instance: HomeApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }


}