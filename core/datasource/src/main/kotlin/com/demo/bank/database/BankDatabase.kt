package com.demo.bank.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.bank.database.dao.AccountDao
import com.demo.bank.database.dao.BankDao
import com.demo.bank.database.dao.OperationDao
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.BankEntity
import com.demo.bank.database.entities.OperationEntity

@Database(
    entities = [
        AccountEntity::class,
        BankEntity::class,
        OperationEntity::class
    ],
    version = 1,
    exportSchema = true,
)

abstract class BankDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun bankDao(): BankDao
    abstract fun operationDao(): OperationDao
}


