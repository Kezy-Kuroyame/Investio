package com.example.empty_views_activity.components

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
import androidx.compose.runtime.MutableState
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
import com.example.empty_views_activity.queries.getPortfoliosNames
import com.example.empty_views_activity.queries.postPortfolio
import com.example.empty_views_activity.ui.theme.colorNotStonks
import com.example.empty_views_activity.ui.theme.colorPrimary
import com.example.empty_views_activity.ui.theme.colorPurple
import com.example.empty_views_activity.ui.theme.colorSecondary
import com.example.empty_views_activity.ui.theme.colorStonks
import com.example.empty_views_activity.ui.theme.colorTintPink
import com.example.empty_views_activity.ui.theme.colorWhite
import com.example.empty_views_activity.ui.theme.textColorWhite
import com.example.empty_views_activity.ui.theme.textHintColor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun PortfolioInfo(){

}

@Composable
fun StockBlock(stock: Stock){
    val isShowDialogInfo = remember {
        mutableStateOf(false)
    }

    if (isShowDialogInfo.value){

    }

    var color: Color = colorWhite
    var delta = ((stock.current_price.toFloat() - stock.purchase_price.toFloat()) * stock.amount).toString()
    if (delta.toFloat() > 0){
        delta = "+$delta"
        color = colorStonks
    }
    else if (delta.toFloat() < 0){
        color = colorNotStonks
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
                text = delta,
                color = color,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(0.dp, 0.dp, 15.dp, 0.dp),
                fontSize = 28.sp
            )
        }
    }
}

@Composable
fun HeaderPortfolio(portfolio: Portfolio){
    val showPortfolioStatistic = remember {
        mutableStateOf(false)
    }

    if (showPortfolioStatistic.value){
        DialogPortfolioStatistic(portfolio, showPortfolioStatistic)
    }
    TextButton(
        onClick = {showPortfolioStatistic.value = true}
    ){
        Text(
            color = textColorWhite,
            text = portfolio.name,
            fontSize = 35.sp,

        )
    }
}

@Composable
fun DialogPortfolioStatistic(portfolio: Portfolio, isShow: MutableState<Boolean>){
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
                        text = "Цена покупки акций: ${portfolio.price}",
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp)
                    )
                    Text(
                        text = "Нынешняя цена акций: ${portfolio.total_profit}",
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp)

                    )
                    Text(
                        text = "Профит: ${portfolio.profitability}"
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
                        text = "Добавить акции",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = textColorWhite
                    )
// TODO
//                    OutlinedTextField( name
//                    )
                    OutlinedTextField(
                        label = {
                                Text(text = "amount")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(100.dp, 0.dp),
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
                            Text(text = "Отмена")
                        }
                        TextButton(onClick = {
                            GlobalScope.launch(Dispatchers.IO) {
//                                postStock(portfolioId, nameStock, amount)
//                                portfolios.value = getPortfoliosNames(userId)
                                isShow.value = false
                            }
                        }
                        ) {
                            Text(text = "Создать")
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
    StockBlock(stock = Stock("0", "0", "0", 2, "YNDX", 550.0, 20.0))
}
