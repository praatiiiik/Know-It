package com.example.knowit.di

import android.content.Context
import com.example.knowit.data.remote.NetworkService
import com.example.knowit.data.remote.Networking
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideNetworkService(@ApplicationContext context: Context): NetworkService =
        Networking.createRetrofitInstance(context)
}