package com.demo.banck.data.repository

import android.util.Log
import com.demo.banck.data.AppDateUtil
import com.demo.bank.database.dao.AccountDao
import com.demo.bank.database.dao.BankDao
import com.demo.bank.database.dao.OperationDao
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.BankEntity
import com.demo.bank.database.entities.BankWithAccounts
import com.demo.bank.database.entities.OperationEntity
import com.demo.bank.remote.AccountResponse
import com.demo.bank.remote.BankResponse
import com.demo.bank.remote.NetworkDataSource
import com.demo.bank.remote.OperationsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
const val CLASS_NAME: String = "BankRepositoryImplement"
class BankRepositoryImplement @Inject constructor(
    private val network : NetworkDataSource,
    private val bankDao: BankDao,
    private val accountDao: AccountDao,
    private val operationDao : OperationDao
) : BankRepository {

    override fun loadData() : Flow<List<BankWithAccounts>> {
        return bankDao.getBanksWithAccounts()
    }

    override suspend fun getBanksUpdates() {

        processData(network.getBanks(), bankDao, accountDao, operationDao)
    }


    override suspend fun insertAccounts(accounts: List<AccountEntity>) {
        accountDao.insertAccounts(accounts)
    }

    override suspend fun insertOperations(operation: List<OperationEntity>) : List<Long> {
        return operationDao.insertOperations(operation)
    }



    private suspend fun processData(banks : List<BankResponse>, bankDao: BankDao,
                                    accountDao: AccountDao,
                                    operationDao: OperationDao
    ){
        Log.d(CLASS_NAME, "result = $banks")
        val accountEntityList = mutableListOf<AccountEntity>()
        val operationEntityList = mutableListOf<OperationEntity>()
        for (bank in banks) {

            val insertedBankId = addOrUpdateBank(bankDao,bank.toBankEntity())
            bank.accounts?.let {accounts ->
                for (account in accounts) {
                    accountEntityList.add(account.toAccountEntity().apply {
                        bankId = insertedBankId
                    })
                    for (operation in account.operations) {
                        operationEntityList.add(operation.toOperationEntity().apply {
                            accountId = account.id
                        })
                    }
                }
            }
        }

        val insertedAccount = accountDao.insertAccounts(accountEntityList).size
        val insertedOp = operationDao.insertOperations(operationEntityList).size

        Log.d(CLASS_NAME, "inserted Account = $insertedAccount" +
                " -- inserted Op = $insertedOp")
    }

    private fun addOrUpdateBank(bankDao: BankDao, bankEntity : BankEntity) : Long{
       return bankDao.getBankByName(bankEntity.name)?.id ?: bankDao.insertBank(bankEntity)
    }

    private fun BankResponse.toBankEntity() = BankEntity(

        name = name,
        isCA = isCA
    )

    private fun AccountResponse.toAccountEntity() = AccountEntity(
        id = id,
        bankId = bankId,
        order = order,
        holder = holder,
        role = role,
        contractNumber = contractNumber,
        label = label,
        productCode = productCode,
        balance = balance
    )


    private fun OperationsResponse.toOperationEntity() = OperationEntity(
        timesTemp = date,
        id = id,
        accountId = accountId,
        title = title,
        amount = amount,
        category = category,
        date = AppDateUtil.getDateTime(date)
    )



}