package falabella.lakovratim.android.fastseller.di.module

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import falabella.lakovratim.android.fastseller.BuildConfig
import falabella.lakovratim.android.fastseller.data.preferences.PreferencesDataSource
import falabella.lakovratim.android.fastseller.data.remote.CounterAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        clientBuilder.connectTimeout(40, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(40, TimeUnit.SECONDS)
        clientBuilder.readTimeout(40, TimeUnit.SECONDS)

        return clientBuilder.build()
    }

    @Provides
//    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, context: Context): Retrofit {

        val sharedPrefs = PreferencesDataSource(context)

        val url = if (sharedPrefs.getIP().isNullOrEmpty()) {
            "http://192.168.0.3:9001/"
        } else {
            "http://${sharedPrefs.getIP()}:9001/"
        }

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        return retrofitBuilder.build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): CounterAPI {
        return retrofit.create(CounterAPI::class.java)
    }
}