package com.demo.bank.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.bank.database.entities.OperationEntity.TableInfo.Companion.AMOUNT
import com.demo.bank.database.entities.OperationEntity.TableInfo.Companion.CATEGORY
import com.demo.bank.database.entities.OperationEntity.TableInfo.Companion.OPERATION_ID
import com.demo.bank.database.entities.OperationEntity.TableInfo.Companion.TIMES_TEMP


@Entity(
    tableName = OperationEntity.TableInfo.TABLE_NAME, primaryKeys = [TIMES_TEMP, OPERATION_ID, AMOUNT, CATEGORY]
)
data class OperationEntity(

    @ColumnInfo(name = TIMES_TEMP)
    var timesTemp: String,
    @ColumnInfo(name = TableInfo.DATE)
    var date: String,
    @ColumnInfo(name = OPERATION_ID)
    var id: String,
    @ColumnInfo(name = TableInfo.ACCOUNT_ID)
    var accountId : String?,
    @ColumnInfo(name = TableInfo.TITLE)
    var title: String,
    @ColumnInfo(name = AMOUNT)
    var amount: String,
    @ColumnInfo(name = CATEGORY)
    var category: String


){

    interface TableInfo {
        companion object {
            const val TABLE_NAME = "operation"
            const val TIMES_TEMP = "times_temp"
            const val OPERATION_ID = "operation_id"
            const val ACCOUNT_ID = "account_id"
            const val TITLE = "title"
            const val AMOUNT = "amount"
            const val CATEGORY = "category"
            const val DATE = "date"
        }
    }
}
