package com.demo.banck.data.di

import com.demo.banck.data.repository.AccountDetailsRepositoryImp
import com.demo.banck.data.repository.AccountRepository
import com.demo.banck.data.repository.BankRepository
import com.demo.banck.data.repository.BankRepositoryImplement
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsBankRepository(
       bankRepository: BankRepositoryImplement
    ): BankRepository

    @Binds
    fun bindsAccountRepository(
        accountRepository: AccountDetailsRepositoryImp
    ): AccountRepository

}