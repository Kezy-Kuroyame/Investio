package com.example.empty_views_activity.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.empty_views_activity.ui.theme.colorSecondary

@Composable
fun PorfoliosScreen(){
    Surface(color = colorSecondary,
        modifier = Modifier
            .fillMaxSize()
            .background(colorSecondary)
    ){
        Column() {

        }
    }
}
