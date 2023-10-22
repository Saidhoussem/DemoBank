package com.demo.bank.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.bank.account.components.CollapsableSection
import com.demo.bank.account.components.ViewType
import com.demo.domain.GetBanksUseCase
import com.demobank.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class AccountViewModel@Inject constructor(
     bankUseCase: GetBanksUseCase,
): ViewModel()  {

    val uiState: StateFlow<AccountUiState> =

        bankUseCase().map { it ->
            if(it.isEmpty())
                arrayListOf()
            else {
                val collapsableSection: ArrayList<CollapsableSection> = arrayListOf()
                collapsableSection.add(CollapsableSection(ViewType.HEADER_CA, "ca"))

                collapsableSection.addAll(it.filter { it.isCA == 1 }.map {
                    CollapsableSection(ViewType.BANK, title = it.name, rows = it.accounts, balance = it.balance)
                })

                collapsableSection.add(CollapsableSection(ViewType.HEADER_NOT_CA, "ab"))
                collapsableSection.addAll(it.filter { it.isCA == 0 }.map {
                    CollapsableSection(ViewType.BANK, title = it.name, rows = it.accounts, balance = it.balance)
                })

                collapsableSection
            }
        }.map(
            AccountUiState::Success
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AccountUiState.Loading,
        )
}



sealed interface AccountUiState {
    data object Loading : AccountUiState

    data class Success(
        val bank: List<CollapsableSection>,
    ) : AccountUiState

    data object Empty : AccountUiState
}


