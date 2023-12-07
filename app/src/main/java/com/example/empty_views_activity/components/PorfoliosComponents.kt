package com.example.empty_views_activity.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.R
import com.example.empty_views_activity.modules.Portfolio
import com.example.empty_views_activity.navigation.Route
import com.example.empty_views_activity.ui.theme.colorPrimary
import com.example.empty_views_activity.ui.theme.colorPurple
import com.example.empty_views_activity.ui.theme.colorSecondary
import com.example.empty_views_activity.ui.theme.colorThird
import com.example.empty_views_activity.ui.theme.colorTintPink
import com.example.empty_views_activity.ui.theme.colorWhite
import com.example.empty_views_activity.ui.theme.textColorWhite
import com.example.empty_views_activity.ui.theme.textHintColor

@Composable
fun ButtonAdd(userId: String){
    IconButton(
        onClick = { /* TODO add portfolios logic */ },
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
              route: String,
              navController: NavController,){

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
                .heightIn(60.dp)
                .background(
                    brush = Brush.linearGradient(listOf(colorSecondary, colorThird, colorSecondary)),
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
        onClick = { Route.LoginIn.route },
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

@Preview
@Composable
fun PortfoliosScreenPreview(){
    val isShow = remember {
        mutableStateOf(true)
    }
    com.example.empty_views_activity.components.Portfolio(value = "Хуй", route = Route.LoginIn.route, navController = rememberNavController())
}