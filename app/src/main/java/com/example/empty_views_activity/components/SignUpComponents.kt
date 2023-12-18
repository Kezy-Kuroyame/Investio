package com.example.empty_views_activity.components

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.empty_views_activity.buttons.LoginInClick
import com.example.empty_views_activity.buttons.signUpClick
import com.example.empty_views_activity.navigation.Route
import com.example.empty_views_activity.queries.postUser
import com.example.empty_views_activity.ui.theme.colorPurple
import com.example.empty_views_activity.ui.theme.colorTintPink
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun SignUp_SignUpButton(value: String,
                        route: String,
                        navController: NavController,
                        rememberValues: MutableList<MutableState<String>>,
){

    val isNavigate = remember{
        mutableStateOf(false)
    }


    val isErrorEmail = remember{
        mutableStateOf(false)
    }
    val isErrorPasswords = remember{
        mutableStateOf(false)
    }
    val isPasswordEmpty = remember{
        mutableStateOf(false)
    }
    val isEmailEmpty = remember{
        mutableStateOf(false)
    }

    if (isNavigate.value){
        SignUpSuccessDialog(isNavigate, navController)
    }

    if (isErrorEmail.value){
        SignUpErrorAlertDialog(isShow = isErrorEmail, header = "Ошибка", content = "Эта почта уже зарегистрирована")
    }
    if (isErrorPasswords.value){
        SignUpErrorAlertDialog(isShow = isErrorPasswords, header = "Ошибка", content = "Введённые пароли не совпадают")
    }
    if (isPasswordEmpty.value){
        SignUpErrorAlertDialog(isShow = isPasswordEmpty, header = "Ошибка", content = "Поле для пароля не может быть пустым")
    }
    if (isEmailEmpty.value){
        SignUpErrorAlertDialog(isShow = isEmailEmpty, header = "Ошибка", content = "Поле Email не может быть пустым")
    }

    Button(
        onClick = {
            // TODO Выглядит оч массивно, можно попробовать как раз засунуть это всё в папку buttons и там обрабатывать
            GlobalScope.launch(Dispatchers.IO) {
                isNavigate.value = signUpClick(
                    args = rememberValues,
                    err = mutableListOf(isErrorEmail, isErrorPasswords, isPasswordEmpty, isEmailEmpty)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(colorPurple, colorTintPink)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun SignUpErrorAlertDialog(isShow: MutableState<Boolean>, header: String, content: String){
    if (isShow.value){
        AlertDialog(
            tonalElevation = 10.dp,
            onDismissRequest = {isShow.value = false},
            confirmButton = {},
            title = {
                Text(
                    text = header,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 25.sp)
            },
            text = {
                Text(
                    text = content,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp
                )
            }
        )
    }
}


@Composable
fun SignUpSuccessDialog(isShow: MutableState<Boolean>, navController: NavController){
    val timer = object: CountDownTimer(1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {isShow.value = false
            navController.navigate(route = Route.LoginIn.route)}
    }
    timer.start()
    if (isShow.value){
        AlertDialog(
            tonalElevation = 10.dp,
            onDismissRequest = {
                isShow.value = false
                navController.navigate(route = Route.LoginIn.route)},
            confirmButton = {},
            title = {
                Text(
                    text = "Успешно",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    fontSize = 32.sp)
            },
            text = {}
        )
    }
}