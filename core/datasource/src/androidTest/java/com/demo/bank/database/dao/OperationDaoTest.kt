package com.demo.bank.database.dao

import android.content.Context
import android.text.format.DateUtils
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demo.bank.database.BankDatabase
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.OperationEntity
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OperationDaoTest {

    private lateinit var operationDao: OperationDao
    private lateinit var bankDb: BankDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        bankDb = Room.inMemoryDatabaseBuilder(
            context,
            BankDatabase::class.java,
        ).build()
        operationDao = bankDb.operationDao()
    }

    @Test
    fun operationDao_insert_items() = runTest {
        val operationEntities = listOf(
            testOperation(
                timesTemp = "1644179569",
                id = 1234,
                accountId = "1234",
            ),testOperation(
                timesTemp = "1588690878",
                id = 12345,
                accountId = "1234",
            ),testOperation(
                timesTemp = "1644784369",
                id = 123456,
                accountId = "1234",
            ),testOperation(
                timesTemp = "1644611558",
                id = 123467,
                accountId = "12345",
            ),

        )
       val insertedItems =  operationDao.insertOperations(operationEntities).size

        assertEquals(insertedItems,4)

        val operation = operationDao.getOperation("1234")
        assertEquals(operation.size,3)

        assertEquals(
            listOf("1644179569","1588690878","1644784369"),
            operation.map { it.timesTemp},
        )

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

}