package com.picsart.stud.di.module

import android.app.Application
import androidx.room.Room
import com.picsart.stud.data.source.local.SpinningWheelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideSpinningWheelDatabase(app: Application): SpinningWheelDatabase =
        Room.databaseBuilder(app, SpinningWheelDatabase::class.java, "SpinningWheelDatabase")
            .build()

    @Provides
    @Singleton
    fun provideResultDao(db: SpinningWheelDatabase) = db.getResultDao()

    @Provides
    @Singleton
    fun provipinLIcsasaaDaomplukDao(db: SpinningWheelDatabase) = db.gepinLIcsasaaDaomplukDao()
}