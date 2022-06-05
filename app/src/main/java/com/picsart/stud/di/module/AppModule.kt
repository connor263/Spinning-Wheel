package com.picsart.stud.di.module

import androidx.lifecycle.MutableLiveData
import com.picsart.stud.data.source.remote.PasteBinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://pastebin.com/raw/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideLiveData(): MutableLiveData<MutableMap<String, Any>> =
        MutableLiveData<MutableMap<String, Any>>()

    @Provides
    @Singleton
    fun providePastebinService(retrofit: Retrofit): PasteBinService =
        retrofit.create(PasteBinService::class.java)
}