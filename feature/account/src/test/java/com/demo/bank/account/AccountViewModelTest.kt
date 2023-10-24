package com.demo.bank.account

import androidx.compose.runtime.collectAsState
import com.demo.banck.data.repository.BankRepository
import com.demo.banck.data.repository.test.TestBankRepository
import com.demo.bank.account.components.CollapsableSection
import com.demo.bank.account.components.ViewType
import com.demo.domain.GetBanksUseCase
import com.demo.domain.test.MainDispatcherRule
import com.demo.domain.test.TestBankUseCase
import com.demobank.model.Account
import com.demobank.model.Bank
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs
import kotlin.test.assertIsNot

class AccountViewModelTest{
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val bankRepository = TestBankRepository()

    private val bankUseCase = TestBankUseCase(bankRepository)
    private lateinit var viewModel : AccountViewModel

    @Before
    fun setUp(){
        viewModel = AccountViewModel(bankUseCase)
    }


    @Test
    fun initialUiState_mustBeLoading() = runTest{
        assertEquals(AccountUiState.Loading, viewModel.uiState.value)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testBank_banKList_addTowHeader() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        bankUseCase.sendBank(banks)
        val item = viewModel.uiState.value
        assertIs<AccountUiState.Success>(item)
        assertEquals(item.bank.size,4)

        collectJob.cancel()

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testBank_bankBalance() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        bankUseCase.sendBank(banks)

        val item = viewModel.uiState.value
        assertIs<AccountUiState.Success>(item)
        collectJob.cancel()

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testBank_emptyBanks() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        bankUseCase.sendBank(listOf())

        val item = viewModel.uiState.value
        assertIs<AccountUiState.Success>(item)
        assertEquals(item.bank.size, 0)
        collectJob.cancel()

    }

    private var banks =  listOf( Bank(
    id = 123,
    name = "Bank_1",
    isCA = 0, accounts = listOf(
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
            balance = 10.0,
            operations = emptyList()
        ), Account(
            id = "123456",
            bankId = 123,
            order = 1,
            holder = "holder",
            role = 1,
            contractNumber = "contractNumber",
            label = "a_label",
            productCode = "productCode",
            balance = 10.0,
            operations = emptyList()
        )
    ), balance = 100.0
),
    Bank(
        id = 1234,
        name = "Bank_2",
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
            ), Account(
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
    ))



}