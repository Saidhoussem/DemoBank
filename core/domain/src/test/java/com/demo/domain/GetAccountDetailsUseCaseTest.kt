package com.demo.domain

import com.demo.banck.data.repository.test.TestAccountRepository
import com.demo.bank.database.entities.AccountEntity
import com.demo.bank.database.entities.AccountWithOperations
import com.demo.bank.database.entities.OperationEntity
import com.demo.domain.test.MainDispatcherRule
import com.demobank.model.Account
import com.demobank.model.Operations
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class GetAccountDetailsUseCaseTest{
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val accountRepository = TestAccountRepository()


    val useCase = GetAccountDetailsUseCase(
        accountRepository
    )

@Test
fun accountIsRetrieved_accountIdShouldBeEqualTo() = runTest{

    val accountWithOperationsUseCase = useCase(1234)

    accountRepository.sendAccountWithOperations(accountWithOperations)

    assertEquals(
        "1234",
        accountWithOperationsUseCase.first().id
    )
}



    @Test
    fun operations_mustBeSortedByDateThenTitle() = runTest{

        val accountWithOperationsUseCase = useCase(1234)

        accountRepository.sendAccountWithOperations(accountWithOperations)

        assertEquals(
            listOf("1234","123","12345"),
            accountWithOperationsUseCase.first().operations.map { it.id }
        )
    }



@Test
fun operationEntity_MapToOperation() = runTest{

    val operation = operationEntity.toOperation()

    assertEquals(operationEntity.accountId, operation.accountId)
    assertEquals(operationEntity.id, operation.id)
    assertEquals(operationEntity.title, operation.title)
    assertEquals(operationEntity.amount, operation.amount)
    assertEquals(operationEntity.category, operation.category)
    assertEquals(operationEntity.date, operation.date)

}





    private val accountEntity =  AccountEntity(
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

  private val operationEntity =  OperationEntity(
    id = "123",
    accountId = "1234",
    title = "b_title",
    amount = "10.0",
    category = "c_1",
    date = "22/10/2023",
    timesTemp = "1697997199"
    )

    private val operationsEntities = listOf(
        operationEntity,
        OperationEntity(
            id = "1234",
            accountId = "1234",
            title = "a_title",
            amount = "-120.0",
            category = "c_2",
            date = "22/10/2023",
            timesTemp = "1697997199"
        ),
        OperationEntity(
            id = "12345",
            accountId = "1234",
            title = "c_title",
            amount = "-120.0",
            category = "c_2",
            date = "21/10/2023",
            timesTemp = "1697910799"
        )
    )



    private val accountWithOperations = AccountWithOperations(accountEntity,
        operationsEntities
    )



    private val operations = listOf(
        Operations(
            id = "123",
            accountId = "1234",
            title = "b_title",
            amount = "10.0",
            category = "c_1",
            date = "23/10/2023",
        ),
        Operations(
            id = "1234",
            accountId = "1234",
            title = "a_title",
            amount = "-120.0",
            category = "c_2",
            date = "22/10/2023",
        ),
        Operations(
            id = "12345",
            accountId = "1234",
            title = "c_title",
            amount = "-120.0",
            category = "c_2",
            date = "21/10/2023",
        )
    )

    private val account =  Account(
        id = "1234",
        bankId = 123,
        order = 1,
        holder = "holder",
        role = 1,
        contractNumber = "contractNumber",
        label = "b_label",
        productCode = "productCode",
        balance = 10.0,
        operations = operations
    )

}