package com.andef.myfinance.old_presentation.ui.income
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.andef.myfinance.domain.income.entities.Income
//import com.andef.myfinance.domain.income.entities.IncomeCategory
//import com.andef.myfinance.presentation.formatter.AmountFormatter
//import com.andef.myfinance.ui.theme.MyFinanceTheme
//import java.util.Date
//
//@Composable
//fun IncomeCard(income: com.andef.myfinance.domain.income.entities.Income, onIncomeCardClickListener: (com.andef.myfinance.domain.income.entities.Income) -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        colors = CardDefaults.cardColors(
//            contentColor = MaterialTheme.colorScheme.onBackground,
//            containerColor = MaterialTheme.colorScheme.background
//        ),
//        shape = RoundedCornerShape(10.dp),
//        elevation = CardDefaults.cardElevation(8.dp),
//        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onBackground),
//        onClick = {
//            onIncomeCardClickListener(income)
//        }
//    ) {
//        Row(
//            modifier = Modifier.padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Image(
//                painter = painterResource(income.category.iconResId),
//                contentDescription = stringResource(income.category.nameResId)
//            )
//            Spacer(modifier = Modifier.padding(6.dp))
//            Text(text = stringResource(income.category.nameResId))
//            Spacer(modifier = Modifier.weight(1f))
//            Text(
//                text = AmountFormatter.format(income.amount),
//                modifier = Modifier.padding(start = 6.dp)
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//private fun DarkIncomeCardTest() {
//    MyFinanceTheme(darkTheme = true) {
//        IncomeCard(
//            com.andef.myfinance.domain.income.entities.Income(
//                id = 1,
//                amount = 10000.0,
//                category = com.andef.myfinance.domain.income.entities.IncomeCategory.SALARY,
//                comment = "",
//                date = Date()
//            ),
//            {}
//        )
//    }
//}
//
//@Preview
//@Composable
//private fun LightIncomeCardTest() {
//    MyFinanceTheme(darkTheme = false) {
//        IncomeCard(
//            com.andef.myfinance.domain.income.entities.Income(
//                id = 1,
//                amount = 10000.0,
//                category = com.andef.myfinance.domain.income.entities.IncomeCategory.SALARY,
//                comment = "",
//                date = Date()
//            ),
//            {}
//        )
//    }
//}