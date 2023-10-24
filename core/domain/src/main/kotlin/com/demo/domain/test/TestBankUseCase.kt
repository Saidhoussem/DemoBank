package com.demo.domain.test

import com.demo.banck.data.repository.BankRepository
import com.demo.bank.database.entities.BankWithAccounts
import com.demo.domain.GetBanksUseCase
import com.demo.domain.toAccount
import com.demo.domain.toBank
import com.demobank.model.Account
import com.demobank.model.Bank
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class TestBankUseCase(bankRepo: BankRepository) : GetBanksUseCase(bankRepo) {


    override operator fun invoke(): Flow<List<Bank>> {
        return flow {
            // Emit the list of banks
            emit(banks)
        }


    }
    fun sendBank(bank: List<Bank>) {
        banks = bank
    }

}



private var banks =  listOf<Bank>()
