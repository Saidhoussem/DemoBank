package com.demo.banck.data.repository

import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.BankEntity
import com.demo.bank.database.entities.BankWithAccounts
import com.demo.bank.database.entities.OperationEntity
import kotlinx.coroutines.flow.Flow

interface BankRepository {

    fun loadData() : Flow<List<BankWithAccounts>>
    suspend fun getBanksUpdates()
    suspend fun insertAccounts(accounts : List<AccountEntity>)
    suspend fun insertOperations(operation : List<OperationEntity>) : List<Long>

}