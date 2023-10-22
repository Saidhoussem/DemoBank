package com.demo.domain

import android.util.Log
import com.demo.banck.data.repository.AccountRepository
import com.demo.bank.database.entities.OperationEntity
import com.demobank.model.Account
import com.demobank.model.Operations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAccountDetailsUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    operator fun invoke(accountId: Long): Flow<Account> {

      return accountRepository.getAccountWithOperationsByAccountId(accountId).map { account ->
          //RG05 : La liste est ordonnée par date : l’opération la plus récente est en haut de la liste.
          //RG06: Si deux opérations ont la même date, afficher par ordre alphabétique.
            val operations =
                account.operations?.sortedWith(compareBy({ it.date }, { it.title }))?.map {

                    it.toOperation()
                }
            operations?.let {
                account.accountEntity.toAccount().copy(operations = operations)
            }?:
            account.accountEntity.toAccount()
        }

    }
}

    private fun OperationEntity.toOperation() = Operations(
        id = id,
        accountId = accountId,
        title = title,
        amount = amount,
        category = category,
        date = date
    )
