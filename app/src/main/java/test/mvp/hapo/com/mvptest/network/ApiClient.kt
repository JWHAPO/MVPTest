package test.mvp.hapo.com.mvptest.network

import android.content.Context
import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import test.mvp.hapo.com.mvptest.app.BASE_URL
import test.mvp.hapo.com.mvptest.app.REQUEST_TIMEOUT
import test.mvp.hapo.com.mvptest.utils.PrefUtils
import java.util.concurrent.TimeUnit

/**
 * MVPTest
 * Class: ApiClient
 * Created by JEONGWOOKIM on 2019-01-24.
 * Description: API Client
 */
object ApiClient{
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    fun getClient(context: Context) : Retrofit{
        initOkHttp(context)
        retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        return retrofit
    }


    private fun  initOkHttp(context: Context){
        var httpClient :OkHttpClient.Builder =
                OkHttpClient().newBuilder()
                        .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)

        httpClient.addInterceptor(Interceptor { chain ->
            var request = chain
                    .request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build()
//            if(!TextUtils.isEmpty(PrefUtils().getApiKey(context))){
//                request?.addHeader("Authorization", PrefUtils().getApiKey(context))
//            }
            chain.proceed(request)
        })

        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient = httpClient.build()
    }
}