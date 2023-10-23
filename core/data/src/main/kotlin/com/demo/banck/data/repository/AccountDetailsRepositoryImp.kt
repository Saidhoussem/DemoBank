package com.demo.banck.data.repository

import com.demo.bank.database.dao.AccountDao
import com.demo.bank.database.entities.AccountWithOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountDetailsRepositoryImp @Inject constructor(
    private val accountDao: AccountDao)
    : AccountRepository{
    override fun getAccountWithOperationsByAccountId(accountId: String): Flow<AccountWithOperations> {
       return accountDao.getAccountWithOperationsByAccountId(accountId)
    }

}