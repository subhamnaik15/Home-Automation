package door.cyron.house.housedoor

import android.app.Application
import android.content.Context
import door.cyron.house.housedoor.utility.ApiInterface
import door.cyron.house.housedoor.utility.RetrofitUitl
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeApplication : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        val context: Context = HomeApplication.applicationContext()
    }

    companion object {
        private var instance: HomeApplication? = null
        private var apiService: ApiInterface? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        fun getApiService(): ApiInterface {

            if (apiService == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = RetrofitUitl.getUnsafeOkHttpClient()
                httpClient.addInterceptor(logging)

                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
                apiService = retrofit.create(ApiInterface::class.java)
            }
            return apiService as ApiInterface
        }
    }


}