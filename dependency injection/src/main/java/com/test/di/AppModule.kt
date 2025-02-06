package com.test.di

import com.test.data.ProfileRepoImpl
import com.test.domain.repository.ProfileRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideProfileRepo(impl: ProfileRepoImpl): ProfileRepo

}