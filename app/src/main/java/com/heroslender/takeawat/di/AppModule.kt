package com.heroslender.takeawat.di

import com.heroslender.takeawat.repository.MenuRepository
import com.heroslender.takeawat.repository.MenuRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindMenuRepository(
        repo: MenuRepositoryImpl
    ): MenuRepository
}