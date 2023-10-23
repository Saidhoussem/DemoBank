package com.demo.bank.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demo.bank.database.BankDatabase
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.BankEntity
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BankDaoTest {

    private lateinit var bankDao: BankDao
    private lateinit var accountDao: AccountDao
    private lateinit var bankDb: BankDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        bankDb = Room.inMemoryDatabaseBuilder(
            context,
            BankDatabase::class.java,
        ).build()
        bankDao = bankDb.bankDao()
        accountDao = bankDb.accountDao()
    }


    @After
    fun closeDatabase() {
        bankDb.close()
    }
    @Test
    fun bankDao_insert_and_get_bank() = runTest {
        val bank = testBank(
                name =  "Bank_1",
                type = 1
            )

       val insertedItem =  bankDao.insertBank(bank)
       assertEquals(insertedItem,1)

        val bankEntity = bankDao.getBankByName("Bank_1")
        assertEquals(bankEntity?.isCA , 1)

    }


    @Test
    fun bankDao_insert_bank_with_account() = runTest {
        val bank = testBank(
            name =  "Bank_1",
            type = 1
        )
        val insertedItem =  bankDao.insertBank(bank)
        assertEquals(insertedItem,1)

        val accountEntities = listOf(
            testAccount(
                id = "1234",
                bankId = insertedItem,
            ),
            testAccount(
                id = "12345",
                bankId = insertedItem,
            ),
            testAccount(
                id = "123456",
                bankId = insertedItem,
            ),
            testAccount(
                id = "1234567",
                bankId = insertedItem,
            ),
            )

        accountDao.insertAccounts(accountEntities)

        val banks = bankDao.getBanksWithAccounts()

      banks.map { flowBankWithAccount ->

            flowBankWithAccount.map { bankWithAccount ->
                assertEquals(bankWithAccount.bankEntity.id, insertedItem)

                assertEquals(bankWithAccount.accountEntityList?.size, 4)
            }
        }
    }






    private fun testBank(
        name:String,
        type : Int = 0
    ) = BankEntity(
        name = name,
        isCA = type,
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