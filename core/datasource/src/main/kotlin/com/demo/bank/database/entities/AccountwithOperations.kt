package com.demo.bank.database.entities

import androidx.room.Embedded
import androidx.room.Relation

class AccountWithOperations (
    @Embedded
    val accountEntity: AccountEntity,
    @Relation(
        parentColumn = AccountEntity.TableInfo.ACCOUNT_ID,
        entityColumn = OperationEntity.TableInfo.ACCOUNT_ID
    )
    val operations: List<OperationEntity>?
)
