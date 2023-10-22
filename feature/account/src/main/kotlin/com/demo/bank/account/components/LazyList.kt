package com.demo.bank.account.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.bank.account.R
import com.demobank.model.Operations

const val CLASS_NAME: String = "LazyListForOperation"

@Composable
fun LazyListForOperation(
    sections: List<Operations>,
    modifier: Modifier
) {

    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(top = 30.dp)) {
        sections.forEachIndexed { i, dataItem ->
            Log.d(CLASS_NAME, "item = ${dataItem.title}")
            item(key = "operation_$i") {

                OperationCard(dataItem)
                Divider()

            }
        }
    }
}


@Composable
fun OperationCard(item : Operations){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Column(
            modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                item.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 5.dp)
            )

            Text(
                item.date,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                item.amount+ stringResource(id = R.string.euro),
                color = Color.LightGray,
                modifier = Modifier
                    .padding(end = 30.dp)
            )
        }
    }

}

val operation = Operations("123", "12345","Opration title", "12.2", "cat_1", "22/10/2023")

@Preview
@Composable
fun OperationCardPreview(){
    OperationCard(item = operation)
}
