package falabella.lakovratim.android.fastseller.di.module

import com.google.gson.GsonBuilder
import falabella.lakovratim.android.fastseller.BuildConfig
import falabella.lakovratim.android.fastseller.data.remote.CounterAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        return retrofitBuilder.build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): CounterAPI {
        return retrofit.create(CounterAPI::class.java)
    }
}