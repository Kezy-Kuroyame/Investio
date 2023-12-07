package com.example.empty_views_activity.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.empty_views_activity.buttons.LoginInClick
import com.example.empty_views_activity.ui.theme.colorPrimary
import com.example.empty_views_activity.ui.theme.colorPurple
import com.example.empty_views_activity.ui.theme.colorTextDark
import com.example.empty_views_activity.ui.theme.colorTintPink
import com.example.empty_views_activity.ui.theme.colorWhite
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun MailDialog(isShow: MutableState<Boolean>){
    if (isShow.value){
        AlertDialog(
            tonalElevation = 10.dp,
            onDismissRequest = {isShow.value = false},
            confirmButton = {},
            title = {
                Text(
                text = "Ошибка",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp)
            },
            text = {
                Text(
                text = "Введённая почта неверная",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )
            }
        )
    }
}

@Composable
fun LogIn_SignUpButton(value: String,
                       route: String,
                       navController: NavController,
                 ){
    Button(onClick = {navController.navigate(route = route); },
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

// Кнопка входа
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun LogIn_LoginInButton(value: String,
                        route: String,
                        navController: NavController,
                        rememberValue: MutableState<String>
){
    val isNavigate = remember {
        mutableStateOf(false)
    }
    val isError = remember {
        mutableStateOf(false)
    }
    val id = remember {
        mutableStateOf("")
    }

    if (isError.value){
        MailDialog(isError)
    }
    if (isNavigate.value){
        navController.navigate(route = "login_in_password_screen/${id.value}")
    }

    Button(
        onClick = {
            GlobalScope.launch(Dispatchers.IO) {
                id.value = LoginInClick(rememberValue)

                if (id.value != "-1") {
                    Log.i("Cool", "Ты крутой ")
                    isNavigate.value = true
                } else {
                    isError.value = true
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        border = BorderStroke(2.dp, colorPrimary),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = colorWhite,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorTextDark
            )
        }
    }
}

@Preview
@Composable
fun LoginInPreview(){
    val isShow = remember {
        mutableStateOf(true)
    }
    com.example.empty_views_activity.components.MailDialog(isShow)
}