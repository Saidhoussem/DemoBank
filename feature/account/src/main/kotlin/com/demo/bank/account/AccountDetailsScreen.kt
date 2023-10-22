package com.demo.bank.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.bank.account.components.DetailsToolBar
import com.demo.bank.account.components.LazyListForOperation
import com.demo.bank.account.components.LoadingWheel
import com.demobank.model.Operations

@Composable
fun AccountDetailsScreen(
    onBackClick: () -> Unit,
    viewModel: AccountDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DetailsToolBar(modifier = Modifier.fillMaxWidth()) {
            onBackClick()
        }

        when (uiState) {
            is AccountDetailsUiState.Loading -> {
                LoadingWheel()
            }
           is AccountDetailsUiState.Success -> {
               Text(
                   fontSize = 30.sp,
                   text = (uiState as AccountDetailsUiState.Success).account.balance.toString() + stringResource(
                       id = R.string.euro
                   ),
                   fontWeight = FontWeight.Bold,
               )

               Spacer(modifier = Modifier.height(30.dp))

               Text(
                   fontSize = 30.sp,
                   text = (uiState as AccountDetailsUiState.Success).account.label,
                   fontWeight = FontWeight.Bold,
               )

               LazyListForOperation( (uiState as AccountDetailsUiState.Success).account.operations, modifier = Modifier.fillMaxSize())
            }
            is AccountDetailsUiState.Empty -> {
                AccountDetailsEmptyScreen()
            }
        }
    }

}

@Composable
fun AccountDetailsEmptyScreen(
) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = stringResource(id = R.string.account_details_issue))
    }
}

