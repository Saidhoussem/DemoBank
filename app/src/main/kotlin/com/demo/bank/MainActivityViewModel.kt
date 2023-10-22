package com.demo.bank

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.domain.GetBanksUpdatesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    getBanksUpdatesUseCase: GetBanksUpdatesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainActivityUiState>(MainActivityUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = MainActivityUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            getBanksUpdatesUseCase()
            delay(2000)
            _uiState.value = MainActivityUiState.Success

        }

    }
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data object Success : MainActivityUiState
}