package com.demo.bank.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.demo.bank.database.entities.BankEntity
import com.demo.bank.database.entities.BankWithAccounts
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDao {

    @Transaction
    @Query("SELECT  * FROM ${BankEntity.TableInfo.TABLE_NAME} WHERE ${BankEntity.TableInfo.NAME} = :name ")
    fun getBankByName(name : String): BankEntity?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBank(bank: BankEntity): Long

    @Transaction
    @Query("SELECT * FROM bank ")
    fun getBanksWithAccounts(): Flow<List<BankWithAccounts>>


}