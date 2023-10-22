package com.demo.bank.account

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import com.demo.bank.account.components.CollapsableLazyColumn
import com.demo.bank.account.components.LoadingWheel

const val CLASS_NAME: String = "AccountScreen"

@Composable
fun AccountScreen(
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart,

            ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 10.dp),
                fontSize = 30.sp,
                text = stringResource(R.string.account_screen_title),
                fontWeight = FontWeight.Bold,
            )


        }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Log.d(CLASS_NAME, "loading finished")

            when (uiState) {
                AccountUiState.Loading ->

                    LoadingWheel()

                is AccountUiState.Success ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopStart
                    ) {
                        if((uiState as AccountUiState.Success).bank.isEmpty())
                            AccountEmptyScreen()
                        else
                           CollapsableLazyColumn(sections = (uiState as AccountUiState.Success).bank, navController)
                    }

                is AccountUiState.Empty -> AccountEmptyScreen()

            }
        }

    }
}


@Composable
fun AccountEmptyScreen(
) {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = stringResource(id = R.string.empty_account_screen))

    }
}