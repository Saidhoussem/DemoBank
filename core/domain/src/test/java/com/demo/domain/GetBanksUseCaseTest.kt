package com.demo.domain

import com.demo.banck.data.repository.test.TestBankRepository
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.BankEntity
import com.demo.bank.database.entities.BankWithAccounts
import com.demo.domain.test.MainDispatcherRule
import com.demobank.model.Account
import com.demobank.model.Bank
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class GetBanksUseCaseTest{

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val bankRepository = TestBankRepository()


    val useCase = GetBanksUseCase(
        bankRepository
    )


@Test
fun bankBalance_mustBeEqualToSumOfAccountBalances() = runTest {
    val bankWithAccountUseCase = useCase()

    bankRepository.sendBankWithAccount(bankWithAccounts)

    assertEquals(
        listOf(bank.balance),
        bankWithAccountUseCase.first().map {bank ->
            bank.accounts?.sumOf{ it.balance}
        }
    )
}

    @Test
    fun accounts_mustBeSortedByLabel() = runTest {
        val bankWithAccountUseCase = useCase()

        bankRepository.sendBankWithAccount(bankWithAccounts)


        assertEquals(
            listOf(3),
            bankWithAccountUseCase.first().map {bank ->
                bank.accounts?.size
            }
        )

        //Test the account id, but it test also the sort
        assertEquals(
            listOf(listOf("123456", "1234", "12345")),
            bankWithAccountUseCase.first().map {bank ->
                bank.accounts?.map { it.id }
            }
        )

        assertEquals(
            listOf(listOf("a_label","b_label","c_label")),
            bankWithAccountUseCase.first().map {bank ->
                bank.accounts?.map { it.label }
            }
        )

    }



    @Test
    fun accountEntity_MapToAccount() = runTest{

        val account = accountEntity.toAccount()

        assertEquals(accountEntity.bankId, account.bankId)
        assertEquals(accountEntity.id, account.id)
        assertEquals(accountEntity.order, account.order)
        assertEquals(accountEntity.holder, account.holder)
        assertEquals(accountEntity.role, account.role)
        assertEquals(accountEntity.contractNumber, account.contractNumber)
        assertEquals(accountEntity.label, account.label)
        assertEquals("", accountEntity.balance, account.balance, 0.0)

    }


    @Test
    fun bankEntity_MapToBank() = runTest{

        val bank = bankEntity.toBank()

        assertEquals(bankEntity.name, bank.name)
        assertEquals(bankEntity.id, bank.id)
        assertEquals(bankEntity.isCA, bank.isCA)


    }

    private val bank = Bank(
    id = 123,
    name = "Bank_1",
    isCA = 1, accounts = listOf(
        Account(
            id = "1234",
            bankId = 123,
            order = 1,
            holder = "holder",
            role = 1,
            contractNumber = "contractNumber",
            label = "b_label",
            productCode = "productCode",
            balance = 10.0,
            operations = emptyList()
        ),
        Account(
            id = "12345",
            bankId = 123,
            order = 1,
            holder = "holder",
            role = 1,
            contractNumber = "contractNumber",
            label = "c_label",
            productCode = "productCode",
            balance = 70.0,
            operations = emptyList()
        ),Account(
            id = "123456",
            bankId = 123,
            order = 1,
            holder = "holder",
            role = 1,
            contractNumber = "contractNumber",
            label = "a_label",
            productCode = "productCode",
            balance = 20.0,
            operations = emptyList()
        )
    ), balance = 100.0
)


    private val bankEntity = BankEntity(
        id = 123,
        name = "Bank_1",
        isCA = 1
    )

    private val accountEntity = AccountEntity(
        id = "1234",
        bankId = 123,
        order = 1,
        holder = "holder",
        role = 1,
        contractNumber = "contractNumber",
        label = "b_label",
        productCode = "productCode",
        balance = 10.0
    )
    private val accountEntities = listOf(
        accountEntity
        , AccountEntity(
            id = "12345",
            bankId = 123,
            order = 1,
            holder = "holder",
            role = 1,
            contractNumber = "contractNumber",
            label = "c_label",
            productCode = "productCode",
            balance = 70.0
        ), AccountEntity(
            id = "123456",
            bankId = 123,
            order = 1,
            holder = "holder",
            role = 1,
            contractNumber = "contractNumber",
            label = "a_label",
            productCode = "productCode",
            balance = 20.0
        )
    )


    private val bankWithAccounts = listOf<BankWithAccounts>(
        BankWithAccounts(bankEntity, accountEntities)
    )



}
