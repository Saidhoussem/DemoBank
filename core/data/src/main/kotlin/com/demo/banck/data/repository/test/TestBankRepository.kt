package com.demo.banck.data.repository.test

import com.demo.banck.data.repository.BankRepository
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.BankWithAccounts
import com.demo.bank.database.entities.OperationEntity
import com.demobank.model.Bank
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestBankRepository : BankRepository {

    private val bankFlow: MutableSharedFlow<List<BankWithAccounts>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    override fun loadData(): Flow<List<BankWithAccounts>>  = bankFlow

    override suspend fun getBanksUpdates() {
        TODO("Not yet implemented")
    }

    override suspend fun insertAccounts(accounts: List<AccountEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertOperations(operation: List<OperationEntity>): List<Long> {
        TODO("Not yet implemented")
    }



    fun sendBankWithAccount(bankWithAccounts: List<BankWithAccounts>) {
        bankFlow.tryEmit(bankWithAccounts)
    }

}