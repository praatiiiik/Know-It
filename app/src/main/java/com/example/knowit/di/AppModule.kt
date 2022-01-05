package com.example.knowit.di

import android.content.Context
import androidx.room.Room
import com.example.knowit.data.local.GeneralNewsDao
import com.example.knowit.data.local.DatabaseClass
import com.example.knowit.data.remote.NetworkService
import com.example.knowit.data.repositiory.DatabaseRepo
import com.example.knowit.data.repositiory.DefaultDatabaseRepo
import com.example.knowit.data.repositiory.DefaultNetworkRepo
import com.example.knowit.data.repositiory.NetworkRepo
import com.example.knowit.ui.Activity.MainActivity
import com.example.knowit.ui.Fragment.General
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMainRepository(networkService: NetworkService,databaseRepo: DatabaseRepo): NetworkRepo = DefaultNetworkRepo(networkService,databaseRepo)

    @Singleton
    @Provides
    fun provideRoomRepository(dao : GeneralNewsDao,@ApplicationContext context: Context) : DatabaseRepo = DefaultDatabaseRepo(dao,context)

    @Singleton
    @Provides
    fun providesMainActivityContext() : Context = MainActivity()

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationComponent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DatabaseClass::class.java,
        "NewsDB"
    ).build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideYourDao(db: DatabaseClass) = db.getNewsDao()

}