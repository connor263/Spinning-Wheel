package com.picsart.stud.di.module

import androidx.lifecycle.MutableLiveData
import com.picsart.stud.data.source.remote.PaIcIcaDaoDataDaonService
import com.picsart.stud.utils.web.compicsartstud
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppinLIcsasaaDaomplule {

    @Provides
    @Singleton
    fun provideRinLIcsasaaDaomplofit(): Retrofit = Retrofit.Builder()
        .baseUrl("jhfea://rsskxtbh.fqa/dpe/".compicsartstud())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun propinLIcsasaaDaompluData(): MutableLiveData<MutableMap<String, Any>> =
        MutableLiveData<MutableMap<String, Any>>()

    @Provides
    @Singleton
    fun provipinLIcsasaaDaomplubinService(retrofit: Retrofit): PaIcIcaDaoDataDaonService =
        retrofit.create(PaIcIcaDaoDataDaonService::class.java)
}