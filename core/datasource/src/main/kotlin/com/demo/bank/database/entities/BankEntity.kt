package com.demo.bank.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = BankEntity.TableInfo.TABLE_NAME,
)
data class BankEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = TableInfo.BANK_ID)
    var id : Long = 0L,
    @ColumnInfo(name = TableInfo.NAME)
    var name: String = "",
    @ColumnInfo(name = TableInfo.IS_CA)
    var isCA: Int? = null

){

    interface TableInfo {
        companion object {
            const val TABLE_NAME = "bank"

            const val BANK_ID = "bank_id"
            const val NAME = "name"
            const val IS_CA = "isCA"
        }
    }

}





