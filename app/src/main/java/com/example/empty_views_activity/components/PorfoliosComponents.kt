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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Logout
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.R
import com.example.empty_views_activity.buttons.signUpClick
import com.example.empty_views_activity.modules.Portfolio
import com.example.empty_views_activity.navigation.Route
import com.example.empty_views_activity.queries.postPortfolio
import com.example.empty_views_activity.ui.theme.colorPrimary
import com.example.empty_views_activity.ui.theme.colorPurple
import com.example.empty_views_activity.ui.theme.colorSecondary
import com.example.empty_views_activity.ui.theme.colorThird
import com.example.empty_views_activity.ui.theme.colorTintPink
import com.example.empty_views_activity.ui.theme.colorWhite
import com.example.empty_views_activity.ui.theme.textColorWhite
import com.example.empty_views_activity.ui.theme.textHintColor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun ButtonAdd(userId: String, navController: NavController){
    val isShowDialog = remember {
        mutableStateOf(false)
    }

    if (isShowDialog.value){
        DialogWithImage(isShowDialog, userId, navController)
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

@Composable
fun Portfolio(value: String,
              navController: NavController,){

    Button(onClick = {navController.navigate(""); },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
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
                            colorSecondary,
                            colorThird,
                            colorSecondary
                        )
                    ),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ){
            Spacer(modifier = Modifier.width(50.dp))
            Text(text = value,
                fontSize = 28.sp,
//                fontWeight = FontWeight.Bold,
                color = textColorWhite,
                modifier = Modifier.padding(25.dp, 0.dp, 0.dp, 0.dp)
            )
        }
    }
}

@Composable
fun ButtonLogOut(navController: NavController){

    IconButton(
        onClick = { navController.navigate( Route.LoginIn.route) },
        modifier = Modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Logout,
            contentDescription = "logOut",
            tint = textColorWhite,
            modifier = Modifier.size(30.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun DialogWithImage(
    isShow: MutableState<Boolean>,
    userId: String,
    navController: NavController

) {
    val portfolioName = remember {
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
                        text = "Добавить Портфель",
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
                            .padding(10.dp, 0.dp),
                        value = portfolioName.value,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedLabelColor = textHintColor,
                            unfocusedBorderColor = colorPrimary,
                            focusedBorderColor = textHintColor,
                            focusedLabelColor = colorPurple,
                            cursorColor = textHintColor,
                            containerColor = colorPrimary,
                            textColor = colorWhite
                        ),
                        onValueChange = {portfolioName.value = it}
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
                                postPortfolio(userId, portfolioName.value)
                                isShow.value = false
                            }
//                            navController.navigate("portfolios/${userId}")
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


@Preview
@Composable
fun PortfoliosScreenPreview(){
    val isShow = remember {
        mutableStateOf(true)
    }
    com.example.empty_views_activity.components.DialogWithImage(isShow, "0", navController = rememberNavController())
}