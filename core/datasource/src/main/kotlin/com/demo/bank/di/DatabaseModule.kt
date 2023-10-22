package com.demo.bank.di

import android.content.Context
import androidx.room.Room
import com.demo.bank.database.BankDatabase
import com.demo.bank.database.dao.AccountDao
import com.demo.bank.database.dao.BankDao
import com.demo.bank.database.dao.OperationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {



    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context,
    ): BankDatabase {
        return Room.databaseBuilder(
            context,
            BankDatabase::class.java,
            "Bank.db",
        )
            .build()

    }


    @Provides
    fun providesBankDao(
        database: BankDatabase,
    ): BankDao = database.bankDao()


    @Provides
    fun providesAccountDao(
        database: BankDatabase,
    ): AccountDao = database.accountDao()


    @Provides
    fun providesOperationDao(
        database: BankDatabase,
    ): OperationDao = database.operationDao()


}