package com.heroslender.takeawat.di

import android.content.Context
import androidx.room.Room
import com.heroslender.takeawat.repository.database.AppDatabase
import com.heroslender.takeawat.repository.database.MenuDao
import com.heroslender.takeawat.retrofit.RetrofitClient
import com.heroslender.takeawat.retrofit.RetrofitServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DependenciesModule {

    @Provides
    fun provideRetrofitClient(
        @ApplicationContext ctx: Context
    ): RetrofitClient {
        return RetrofitServiceGenerator.getClient(ctx)
    }

    @Provides
    fun provideRoomDatabase(
        @ApplicationContext ctx: Context
    ): AppDatabase {
        return Room.databaseBuilder(ctx, AppDatabase::class.java, "db").fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMenuDao(
        database: AppDatabase
    ): MenuDao {
        return database.menuDao()
    }
}