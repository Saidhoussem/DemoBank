package com.demo.bank.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BankWithAccounts (
    @Embedded
    val bankEntity: BankEntity,
    @Relation(
        parentColumn = BankEntity.TableInfo.BANK_ID,
        entityColumn = AccountEntity.TableInfo.BANK_ID
    )
    val accountEntityList: List<AccountEntity>?
)