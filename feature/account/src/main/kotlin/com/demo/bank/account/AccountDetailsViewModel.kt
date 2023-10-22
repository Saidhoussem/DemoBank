package com.demo.bank.account

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.bank.account.navigation.DetailsArgs
import com.demo.domain.GetAccountDetailsUseCase
import com.demobank.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AccountDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getOperationsUseCase: GetAccountDetailsUseCase
) : ViewModel() {

    private val accountArgs: DetailsArgs = DetailsArgs(savedStateHandle)

    private val accountId = accountArgs.accountId

    val uiState : StateFlow<AccountDetailsUiState> =
        getOperationsUseCase.invoke(accountId.toLong()).map(
            AccountDetailsUiState::Success
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AccountDetailsUiState.Loading,
        )


    init {
        Log.d("AccountDetailsViewModel", "accountId = $accountId")
    }



}



sealed interface AccountDetailsUiState {

    data object Loading : AccountDetailsUiState

    data class Success(
        val account: Account,
    ) : AccountDetailsUiState

    data object Empty : AccountDetailsUiState
}



