package com.demo.domain

import com.demo.banck.data.repository.BankRepository
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.BankEntity
import com.demo.bank.database.entities.OperationEntity
import javax.inject.Inject


class GetBanksUpdatesUseCase @Inject constructor(
    private val bankRepository: BankRepository,
) {
    suspend operator fun invoke() {
        //Sync must be with WorkManager
         bankRepository.getBanksUpdates()

    }



}
