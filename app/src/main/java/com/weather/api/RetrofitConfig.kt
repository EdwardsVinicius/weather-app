package com.weather.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object{
//        val cacheSize = (5 x 1024 x 1024).toLong()
//        val myCache = Cache(context.cacheDir, cacheSize)
//
//        val okHttpClient = OkHttpClient.Builder()
//            .cache(myCache)
//            .addInterceptor { chain ->
//                var request = chain.request()
//                request = if (RetrofitConfig.hasNetwork(context)!!)
//                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
//                else
//                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
//                chain.proceed(request)
//            }
//            .build()

        private const val baseUrl = "http://api.weatherapi.com/v1/"

        fun getRetrofitInstance(path: String = baseUrl): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

//        fun hasNetwork(context: Context): Boolean? {
//            var isConnected: Boolean? = false // Initial Value
//            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//            if (activeNetwork != null && activeNetwork.isConnected)
//                isConnected = true
//            return isConnected
//        }
    }

}