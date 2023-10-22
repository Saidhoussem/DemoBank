package com.demo.bank.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.AccountWithOperations
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccounts(accountEntity: List<AccountEntity>): List<Long>

    @Transaction
    @Query("SELECT  * FROM ${AccountEntity.TableInfo.TABLE_NAME} WHERE  ${AccountEntity.TableInfo.BANK_ID} = :bankId")
    fun getAccountsByBankId(bankId : Long):List<AccountEntity>


    @Transaction
    @Query("SELECT  * FROM ${AccountEntity.TableInfo.TABLE_NAME} WHERE  ${AccountEntity.TableInfo.ACCOUNT_ID} = :accountId")
    fun getAccountWithOperationsByAccountId(accountId : Long): Flow<AccountWithOperations>

}