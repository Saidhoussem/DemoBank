package com.demo.bank.account.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.demo.bank.account.R
import com.demo.bank.account.navigation.navigateToDetails
import com.demobank.model.Account

@Composable
fun CollapsableLazyColumn(
    sections: List<CollapsableSection>,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }
    LazyColumn(modifier) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            item(key = "header_$i") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            collapsedState[i] = !collapsed
                        }
                ) {

                    when (dataItem.type) {
                        ViewType.HEADER_CA -> {
                            HeaderBankTitle(ViewType.HEADER_CA)
                        }

                        ViewType.HEADER_NOT_CA -> {
                            HeaderBankTitle(ViewType.HEADER_NOT_CA)
                        }

                        ViewType.BANK -> {

                            BankCard(dataItem, collapsed)
                        }

                    }

                }
                Divider()
            }
            if (!collapsed) {
                item(key = "account_$i") {
                    dataItem.rows?.forEachIndexed { _, account ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = account.label,
                                modifier = Modifier
                                    .padding(vertical = 10.dp, horizontal = 20.dp)
                                    .clickable {
                                        navController.navigateToDetails(account.id)
                                    }
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    text = account.balance.toString() + stringResource(id = R.string.euro),
                                    modifier = Modifier
                                        .padding(vertical = 10.dp, horizontal = 20.dp)
                                        .clickable {
                                            navController.navigateToDetails(account.id)
                                        }
                                )
                            }

                        }

                        Divider(modifier = Modifier.padding(horizontal = 20.dp))
                    }
                }
            }
        }
    }

}


@Composable
fun HeaderBankTitle(bankType: ViewType) {
    val title = if (bankType == ViewType.HEADER_CA)
        stringResource(id = R.string.ca_bank)
    else
        stringResource(id = R.string.other_bank)
    Text(
        title,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)

    )
}

@Preview
@Composable
fun HeaderBankTitlePreview() {
    HeaderBankTitle(ViewType.HEADER_NOT_CA)
}


val collapsableSection = CollapsableSection(
    ViewType.BANK,
    "Titre", balance = 123.0
)

@Composable
fun BankCard(
    dataItem: CollapsableSection,
    collapsed: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    )
    {
        Text(
            dataItem.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        )
        {
            Text(
                dataItem.balance.toString() + stringResource(id = R.string.euro),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 10.dp)
            )
            Icon(
                Icons.Default.run {
                    if (collapsed)
                        KeyboardArrowDown
                    else
                        KeyboardArrowUp
                },
                contentDescription = "",
                tint = Color.LightGray
            )
        }
    }
}

@Preview
@Composable
fun BankCardPreview() {
    BankCard(dataItem = collapsableSection, collapsed = true)
}


enum class ViewType {
    HEADER_CA,
    HEADER_NOT_CA,
    BANK
}

data class CollapsableSection(
    val type: ViewType,
    val title: String,
    val rows: List<Account>? = null,
    val balance: Double = 0.0
)

