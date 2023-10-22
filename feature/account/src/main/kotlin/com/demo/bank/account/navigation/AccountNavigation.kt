package com.demo.bank.account.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.demo.bank.account.AccountDetailsScreen
import java.net.URLDecoder

@VisibleForTesting
internal const val accountIdArg = "accountId"
private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

internal class DetailsArgs(val accountId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[accountIdArg]), URL_CHARACTER_ENCODING))
}

fun NavController.navigateToDetails(accountId: String) {
    this.navigate("account_details/$accountId") {
        launchSingleTop = true
    }
}




fun NavGraphBuilder.detailsScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "account_details/{$accountIdArg}",
        arguments = listOf(
            navArgument(accountIdArg) { type = NavType.StringType },
        ),
    ) {_ ->
        AccountDetailsScreen(onBackClick = onBackClick)
    }
}
