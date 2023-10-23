package com.demo.banck.data.repository

import com.demo.bank.database.entities.AccountWithOperations
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getAccountWithOperationsByAccountId(accountId : String) : Flow<AccountWithOperations>

}