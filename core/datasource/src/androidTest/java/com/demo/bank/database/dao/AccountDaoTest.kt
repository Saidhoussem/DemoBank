package com.demo.bank.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demo.bank.database.BankDatabase
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.OperationEntity
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AccountDaoTest {

    private lateinit var accountDao: AccountDao
    private lateinit var operationDao: OperationDao
    private lateinit var bankDb: BankDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        bankDb = Room.inMemoryDatabaseBuilder(
            context,
            BankDatabase::class.java,
        ).build()
        accountDao = bankDb.accountDao()
        operationDao = bankDb.operationDao()
    }

    @Test
    fun accountDao_insert_items() = runTest {
        val accountEntities = listOf(
            testAccount(
                id = "1234",
                bankId = 0,
            ),
            testAccount(
                id = "12345",
                bankId = 0,
            ),
            testAccount(
                id = "123456",
                bankId = 1,
            ),
            testAccount(
                id = "1234567",
                bankId = 1,
            ),
        )
       val insertedItems =  accountDao.insertAccounts(accountEntities).size

        assertEquals(insertedItems,4)

        val accounts = accountDao.getAccountsByBankId(1)
        assertEquals(accounts.size,2)

        assertEquals(
            listOf("123456","1234567"),
            accounts.map { it.id},
        )

    }




    @Test
    fun accountDao_insert_account_with_operation() = runTest {
       val accounts = listOf( testAccount(
            id = "1234567",
            bankId = 1,
        )
       )
        val insertedItem =  accountDao.insertAccounts(accounts).size

        assertEquals(insertedItem,1)

        val operationEntities = listOf(
            testOperation(
                timesTemp = "1644179569",
                id = 1234,
                accountId = "1234567",
            ),testOperation(
                timesTemp = "1588690878",
                id = 12345,
                accountId = "1234567",
            ),testOperation(
                timesTemp = "1644784369",
                id = 123456,
                accountId = "1234567",
            ),testOperation(
                timesTemp = "1644611558",
                id = 123467,
                accountId = "123456745",
            ),

            )

        operationDao.insertOperations(operations = operationEntities)

        val account = accountDao.getAccountWithOperationsByAccountId(1234567)

        account.map { accountWithOperation ->

                assertEquals(accountWithOperation.accountEntity.id, "1234567")

                assertEquals(accountWithOperation.operations?.size, 3)

        }
    }

    private fun testOperation(
        timesTemp: String,
        id : Long,
        accountId : String
    ) = OperationEntity(
        timesTemp = timesTemp,
        date = timesTemp,
        id = id.toString(),
        accountId = accountId,
        title = timesTemp,
        amount = accountId,
        category = "0"
    )

    private fun testAccount(
        id: String,
        bankId : Long
    ) = AccountEntity(
        id = id,
        bankId = bankId,
        order = 0,
        holder = "holder$id",
        role = bankId.toInt(),
        contractNumber = "contractNumber$bankId",
        label = "label$id",
        productCode = "productCode",
        balance = 123.123,

    )

}