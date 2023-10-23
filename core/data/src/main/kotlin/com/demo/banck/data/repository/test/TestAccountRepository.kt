package com.demo.banck.data.repository.test

import com.demo.banck.data.repository.AccountRepository
import com.demo.bank.database.entities.AccountWithOperations
import com.demo.bank.database.entities.BankWithAccounts
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestAccountRepository : AccountRepository{

    private val accountFlow: MutableSharedFlow<AccountWithOperations> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    override fun getAccountWithOperationsByAccountId(accountId: Long) = accountFlow



    fun sendAccountWithOperations(accountWithOperations: AccountWithOperations) {
        accountFlow.tryEmit(accountWithOperations)
    }

}