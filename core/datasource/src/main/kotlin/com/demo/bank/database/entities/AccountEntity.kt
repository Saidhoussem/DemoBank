package com.demo.bank.database.entities

import androidx.room.ColumnInfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = AccountEntity.TableInfo.TABLE_NAME,
)
data class AccountEntity(

    @PrimaryKey
    @ColumnInfo(name = TableInfo.ACCOUNT_ID)
    var id: String,
    @ColumnInfo(name = TableInfo.BANK_ID)
    var bankId: Long,
    @ColumnInfo(name = TableInfo.ORDER)
    var order: Int,
    @ColumnInfo(name = TableInfo.HOLDER)
    var holder: String,
    @ColumnInfo(name = TableInfo.ROLE)
    var role: Int,
    @ColumnInfo(name = TableInfo.CONTACT_NUMBER)
    var contractNumber: String?,
    @ColumnInfo(name = TableInfo.LABEL)
    var label: String,
    @ColumnInfo(name = TableInfo.PRODUCT_CODE)
    var productCode: String?,
    @ColumnInfo(name = TableInfo.BALANCE)
    var balance: Double

) {

    interface TableInfo {
        companion object {
            const val TABLE_NAME = "account"

            const val ACCOUNT_ID = "account_id"
            const val BANK_ID = "banck_id"
            const val ORDER = "order"
            const val HOLDER = "holder"
            const val ROLE = "role"
            const val CONTACT_NUMBER = "contract_number"
            const val LABEL = "label"
            const val PRODUCT_CODE = "product_code"
            const val BALANCE = "balance"
        }
    }

}

