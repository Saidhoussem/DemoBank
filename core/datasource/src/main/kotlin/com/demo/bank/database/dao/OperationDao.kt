package com.demo.bank.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.OperationEntity


@Dao
interface OperationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOperations (operations : List<OperationEntity>): List<Long>

    @Transaction
    @Query("SELECT  * FROM ${OperationEntity.TableInfo.TABLE_NAME} WHERE ${AccountEntity.TableInfo.ACCOUNT_ID} = :accountId ")
    suspend fun getOperation (accountId : String): List<OperationEntity>

}