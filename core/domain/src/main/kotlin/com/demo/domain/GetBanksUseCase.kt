package com.demo.domain

import com.demo.banck.data.repository.BankRepository
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.BankEntity
import com.demobank.model.Account
import com.demobank.model.Bank
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


open class GetBanksUseCase @Inject constructor(
    private val bankRepository: BankRepository
) {
    open operator fun invoke(): Flow<List<Bank>> {

        return bankRepository.loadData().map { bankWithAccountsList ->


            bankWithAccountsList.sortedBy {it.bankEntity.isCA}.map { bankWithAccount ->
                var balance = 0.0
                //RG03: Les comptes sont affichés par ordre alphabétique.
                val listAccount = bankWithAccount.accountEntityList?.sortedBy { it.label }?.map {
                    balance+=it.balance
                    it.toAccount()
                }

                bankWithAccount.bankEntity.toBank().copy(accounts = listAccount, balance = balance)
            }
        }
    }
}



fun AccountEntity.toAccount() = Account(
    id = id,
    bankId = bankId,
    order = order,
    holder = holder,
    role = role,
    contractNumber = contractNumber,
    label = label,
    productCode = productCode,
    balance = balance,
    operations = emptyList()
)


fun BankEntity.toBank() = Bank(
    id = id,
    name = name,
    isCA = isCA,
    accounts = emptyList(),
    balance = 0.0
)