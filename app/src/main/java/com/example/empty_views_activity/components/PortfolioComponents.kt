package com.example.empty_views_activity.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.empty_views_activity.R
import com.example.empty_views_activity.modules.Portfolio
import com.example.empty_views_activity.modules.Stock
import com.example.empty_views_activity.moexAPI.getSecuritiesModel
import com.example.empty_views_activity.queries.getPortfoliosNames
import com.example.empty_views_activity.queries.getStockByPortfolioId
import com.example.empty_views_activity.queries.postPortfolio
import com.example.empty_views_activity.queries.postStock
import com.example.empty_views_activity.ui.theme.colorNotStonks
import com.example.empty_views_activity.ui.theme.colorPrimary
import com.example.empty_views_activity.ui.theme.colorPurple
import com.example.empty_views_activity.ui.theme.colorSecondary
import com.example.empty_views_activity.ui.theme.colorStonks
import com.example.empty_views_activity.ui.theme.colorThird
import com.example.empty_views_activity.ui.theme.colorTintPink
import com.example.empty_views_activity.ui.theme.colorWhite
import com.example.empty_views_activity.ui.theme.textColorWhite
import com.example.empty_views_activity.ui.theme.textHintColor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun PortfolioInfo(){

}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun StockBlock(stock: Stock){
    val isShowDialogInfo = remember {
        mutableStateOf(false)
    }

    if (isShowDialogInfo.value){

    }

    val color = remember {
        mutableStateOf(colorWhite)
    }
    val delta = remember {
        mutableStateOf("")
    }

    GlobalScope.launch(Dispatchers.IO) {
        val currentDate = Date()
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        val newDate = calendar.time
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateText: String = dateFormat.format(newDate)

        val getSecure = getSecuritiesModel(tradeData = dateText, stock.name)
        GlobalScope.launch ( Dispatchers.IO ) {
            delta.value =
                String.format("%.2f", (getSecure.value / getSecure.volume - stock.purchase_price.toFloat()) * stock.amount)
            if (delta.value.toFloat() > 0.001) {
                delta.value = "+${String.format("%.2f", delta.value.toFloat())}"
                color.value = colorStonks
            } else if (delta.value.toFloat() < (-0.01)) {
                delta.value = String.format("%.2f", delta.value.toFloat())
                color.value = colorNotStonks
            }
        }
    }
    
    Button(onClick = { isShowDialogInfo.value = true },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .padding(0.dp, 0.dp, 0.dp, 15.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .heightIn(60.dp)
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            colorTintPink,
                            colorPurple,
                            colorTintPink
                        )
                    ),
                    shape = RoundedCornerShape(5.dp)
                ),
        ){
            Spacer(modifier = Modifier.width(50.dp))
            Text(text = stock.name,
                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold,
                color = textColorWhite,
                modifier = Modifier
                    .padding(15.dp, 0.dp, 0.dp, 5.dp)
                    .align(Alignment.CenterStart)
            )
            Text(
                text = delta.value,
                color = color.value,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(0.dp, 0.dp, 15.dp, 0.dp),
                fontSize = 28.sp
            )
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun HeaderPortfolio(portfolio: Portfolio, stocks: MutableState<List<Stock>>){
    val showPortfolioStatistic = remember {
        mutableStateOf(false)
    }

    if (showPortfolioStatistic.value){
        DialogPortfolioStatistic(portfolio, showPortfolioStatistic, stocks)
    }
    TextButton(
        onClick = {

            showPortfolioStatistic.value = true
        }
    ){
        Text(
            color = textColorWhite,
            text = portfolio.name,
            fontSize = 35.sp,

        )
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun DialogPortfolioStatistic(portfolio: Portfolio, isShow: MutableState<Boolean>, stocks: MutableState<List<Stock>>){
    val sumOld = remember {
        mutableDoubleStateOf(0.0)
    }
    val sumNow = remember {
        mutableDoubleStateOf(0.0)
    }
    val profit = remember {
        mutableDoubleStateOf(0.0)
    }


    LaunchedEffect(key1 = true) {
        val currentDate = Date()
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        val newDate = calendar.time
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateText: String = dateFormat.format(newDate)

        GlobalScope.launch(Dispatchers.IO) {
            for (stock: Stock in stocks.value) {
                val getSecure = getSecuritiesModel(tradeData = dateText, stock.name)
                sumNow.doubleValue += (getSecure.value / getSecure.volume) * stock.amount

                sumOld.doubleValue += stock.purchase_price * stock.amount
            }
            profit.doubleValue = sumNow.doubleValue.div(sumOld.doubleValue) * 100 - 100
        }
    }

    if (isShow.value){
        Dialog(onDismissRequest = { isShow.value = false }) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .width(150.dp)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorSecondary,
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = "Статистика Портфеля",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = textColorWhite
                    )
                    Text(
                        text = "Цена покупки акций: ${String.format("%.2f", sumOld.doubleValue)}",
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp),
                        color = textColorWhite

                    )
                    Text(
                        text = "Нынешняя цена акций: ${String.format("%.2f", sumNow.doubleValue)}",
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp),
                        color = textColorWhite

                    )
                    Text(
                        text = "Профит:${String.format("%.2f", profit.doubleValue)}%",
                        color = textColorWhite
                    )

                }
            }
        }
    }
}

@Composable
fun ButtonBackPortfolio(userID : String, navController: NavController){
    Image(painter = painterResource(
        id = R.drawable.back),
        contentDescription = "back",
        modifier = Modifier
            .size(40.dp)
            .clickable { navController.navigate(route = "portfolios/${userID}") },
        colorFilter = ColorFilter.tint(textHintColor)
    )
}


@Composable
fun ButtonAddStock(portfolioId: String, stocks: MutableState<List<Stock>>){
    val isShowDialog = remember {
        mutableStateOf(false)
    }

    if (isShowDialog.value){
        DialogAddStock(isShowDialog, portfolioId, stocks)
    }

    IconButton(
        onClick = {
            isShowDialog.value = true
        },
        modifier = Modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "add",
            tint = textColorWhite,
            modifier = Modifier.fillMaxSize()
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun DialogAddStock(
    isShow: MutableState<Boolean>,
    portfolioId: String,
    stocks: MutableState<List<Stock>>
) {
    val stockName = remember {
        mutableStateOf("")
    }
    val stockAmount = remember {
        mutableStateOf("")
    }

    if (isShow.value) {
        Dialog(onDismissRequest = { isShow.value = false }) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .width(170.dp)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorThird,
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = "Добавить акции",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = textColorWhite
                    )

                    OutlinedTextField(
                        label = {
                            Text(text = "Name")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(90.dp, 0.dp),
                        value = stockName.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedLabelColor = textHintColor,
                            unfocusedBorderColor = colorPrimary,
                            focusedBorderColor = textHintColor,
                            focusedLabelColor = colorPurple,
                            cursorColor = textHintColor,
                            containerColor = colorPrimary,
                            textColor = colorWhite
                        ),
                        onValueChange = {stockName.value = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )
                    OutlinedTextField(
                        label = {
                                Text(text = "amount")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(90.dp, 0.dp),
                        value = stockAmount.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedLabelColor = textHintColor,
                            unfocusedBorderColor = colorPrimary,
                            focusedBorderColor = textHintColor,
                            focusedLabelColor = colorPurple,
                            cursorColor = textHintColor,
                            containerColor = colorPrimary,
                            textColor = colorWhite
                        ),
                        onValueChange = {stockAmount.value = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(onClick = {isShow.value = false}) {
                            Text(text = "Отмена", color = textColorWhite)
                        }
                        TextButton(onClick = {
                            GlobalScope.launch(Dispatchers.IO) {
                                postStock(stockName.value, stockAmount.value.toInt(), portfolioId)
                                stocks.value = getStockByPortfolioId(portfolioId)
                                isShow.value = false
                            }
                        }
                        ) {
                            Text(text = "Создать", color = textColorWhite)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun DialogStockInfo(
    isShow: MutableState<Boolean>,
    stock: Stock
) {
    if (isShow.value) {
        Dialog(onDismissRequest = { isShow.value = false }) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .width(150.dp)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorSecondary,
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = "Статистика",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = textColorWhite
                    )
                    Text(
                        text = "Количество: ${stock.amount}"
                    )
                    Text(
                        text = "Цена закупки: ${stock.purchase_price}"
                    )
                    Text(
                        text = "Текущая цена: ${stock.current_price}"
                    )

                }
            }
        }
    }
}




@Preview
@Composable
fun PreviewPortfolioComponents(){
    val isShow = remember {
        mutableStateOf(true)
    }
//    DialogAddStock(isShow, "19", )
    StockBlock(stock = Stock("0", "0", "0", 2, "YNDX", 550.0, 20.0))
}
