package com.spidergod.nobrokerassignment.di

import android.app.Application
import androidx.room.Room
import com.spidergod.nobrokerassignment.data.local.database.NoBrokerDatabase
import com.spidergod.nobrokerassignment.data.remote.api.NoBrokerApi
import com.spidergod.nobrokerassignment.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesNoBrokerDataBase(
        app: Application
    ): NoBrokerDatabase {
        return Room.databaseBuilder(app, NoBrokerDatabase::class.java, "noBrokerDataBase")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
            .build()
    }

    @Provides
    @Singleton
    fun providesNoBrokerApi(
        retrofit: Retrofit
    ): NoBrokerApi {
        return retrofit.create(NoBrokerApi::class.java)
    }

}