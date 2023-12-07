package com.example.empty_views_activity.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.empty_views_activity.R
import com.example.empty_views_activity.ui.theme.colorPrimary
import com.example.empty_views_activity.ui.theme.colorPurple
import com.example.empty_views_activity.ui.theme.colorTintPink
import com.example.empty_views_activity.ui.theme.colorWhite
import com.example.empty_views_activity.ui.theme.textHintColor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch


@Composable
fun HintTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        , color = colorResource(id = R.color.textHintColor)
        , textAlign = TextAlign.Center
    )
}

@Composable
fun NormalTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        , color = colorResource(id = R.color.textColorWhite)
        , textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        )
        , color = colorResource(id = R.color.textColorWhite)
        , textAlign = TextAlign.Center
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailFieldComponent(labelValue: String, painterResource: Painter, rememberValue: MutableState<String>){
    
    val isError = remember{
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedLabelColor = textHintColor,
            unfocusedBorderColor = colorPrimary,
            focusedBorderColor = textHintColor,
            focusedLabelColor = colorPurple,
            cursorColor = textHintColor,
            containerColor = colorPrimary,
            textColor = colorWhite
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        value = rememberValue.value,
        shape = RoundedCornerShape(10.dp),
        onValueChange = {
            rememberValue.value = it
        },

        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "",
                modifier = Modifier.size(23.dp))
        },
        textStyle = TextStyle(fontSize = 18.sp),
        isError = isError.value,
        supportingText = {
            if (isError.value){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Почта ${rememberValue.value} не найдена",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordFieldComponent(labelValue: String, painterResource: Painter, rememberValue: MutableState<String>){

    val passwordVisible = remember{
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedLabelColor = textHintColor,
            unfocusedBorderColor = colorPrimary,
            focusedBorderColor = textHintColor,
            focusedLabelColor = colorPurple,
            cursorColor = textHintColor,
            containerColor = colorPrimary,
            textColor = colorWhite
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = rememberValue.value,
        shape = RoundedCornerShape(10.dp),
        onValueChange = {
            rememberValue.value = it
        },
        leadingIcon = {
            Icon(
                painter = painterResource, contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else
            PasswordVisualTransformation(),

        textStyle = TextStyle(fontSize = 18.sp)

    )
}


@Composable
fun ButtonBack(route: String, navController: NavController){
    Image(painter = painterResource(
        id = R.drawable.back),
        contentDescription = "back",
        modifier = Modifier
            .size(40.dp)
            .clickable { navController.navigate(route = route) },
        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(textHintColor)
        )
}

@Preview
@Composable
fun AppComponentsPreview(){
    val isShow = remember {
        mutableStateOf(true)
    }
    com.example.empty_views_activity.components.MailDialog(isShow)
}