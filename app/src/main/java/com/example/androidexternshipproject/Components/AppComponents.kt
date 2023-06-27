@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidexternshipproject.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidexternshipproject.R
import com.example.androidexternshipproject.ui.theme.Red
import com.example.androidexternshipproject.ui.theme.TextColor
import com.example.androidexternshipproject.ui.theme.White


@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal
        ),
        color = colorResource(id = R.color.white),
        textAlign = TextAlign.Center

    )

}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontStyle = FontStyle.Normal,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),
        color = Color.Red,
        textAlign = TextAlign.Center

    )

}


@Composable
fun MyTextFieldComponent(labelValue: String, painterResource: Painter){
    val textValue= remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))  //This is erasing parts of the boundary
        ,
        label={ Text(text = labelValue)},
        value = textValue.value,
        onValueChange = {textValue.value=it},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = White,
            focusedLabelColor = White,
            cursorColor = White
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription ="" )
        }

    )
}

@Composable
fun PasswordTextFieldComponent(labelValue: String, painterResource: Painter) {
    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp)),
        label = { Text(text = labelValue) },
        value = password.value,
        onValueChange = { password.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = White,
            focusedLabelColor = White,
            cursorColor = White
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction =
            ImeAction.Done
        ),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {

            val iconImage =
                if (passwordVisible.value) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
            val description =
                if (passwordVisible.value) {
                    stringResource(id = R.string.hide_password)
                } else {
                    stringResource(id = R.string.show_password)
                }

            IconButton(onClick = { passwordVisible.value = !(passwordVisible.value) }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }

    )
}

@Composable
fun CheckboxComponent(value: String,onTextSelected: (String) -> Unit){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            ){
        val checkedState= remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value, onCheckedChange = {checkedState.value=!checkedState
            .value})
        ClickableTextComponent(value = value,onTextSelected)

    }
}

@Composable
fun ClickableTextComponent(value: String,onTextSelected:(String)->Unit){
    val initialText= "By continuing you accept our "
    val privacyPolicyText= "Privacy Policy"
    val andText= " and "
    val termsAndConditionsText= "Term of Use"

    //to make a combined string
    val annotatedString= buildAnnotatedString {
        withStyle(style = SpanStyle(color= TextColor)){
            append(initialText)
        }
        withStyle(style = SpanStyle(color= Red) ){
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }

        withStyle(style = SpanStyle(color= TextColor)){
            append(andText)
        }

        withStyle(style = SpanStyle(color= Red) ){
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }

    }
    //now to make a clickable text
    //this offset will help us in telling which part of the string is clicked
    ClickableText(text = annotatedString, onClick = {offset->//when we click on this
        // annotatedString , will get an offset

        //now we will use this offset to find out the span that we have clicked
        annotatedString.getStringAnnotations(offset/*start*/,offset/*end*/)
            .firstOrNull()/*if there is a valid span click*/?.also {
                span->
                Log.d("ClickableTextComponent","{$span}")


                if(span.item==termsAndConditionsText || span.item==privacyPolicyText) {
                    onTextSelected(span.item)

                }
            }

    })
}

@Composable
fun ButtonComponent(value: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Red, White)),
                    shape = RoundedCornerShape(50.dp)

                    ),

            contentAlignment = Alignment.Center
        ) {
            Text(
//                modifier = Modifier.padding(top = 12.dp),
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )
        }

    }
}

@Composable
fun DividerTextComponent(){
Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        color = White,
        thickness = 1.dp
    )

    Text(
        modifier = Modifier.padding(8.dp),
        text = stringResource(id = R.string.or),
        fontSize = 18.sp,
        color = TextColor
    )
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        color = White,
        thickness = 1.dp
    )
}
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin:Boolean=true,onTextSelected:(String)->Unit){
    val initialText= if(tryingToLogin){"Already have an account?"}else{"Don't have an account yet?"}
    val loginText= if(tryingToLogin){"Login"}else{"Register"}

    //to make a combined string
    val annotatedString= buildAnnotatedString {
        withStyle(style = SpanStyle(color= TextColor)){
            append(initialText)
        }


        withStyle(style = SpanStyle(color= Red) ){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }

    }
    //now to make a clickable text
    //this offset will help us in telling which part of the string is clicked
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal
        ),
        text = annotatedString, onClick = {offset->//when we click on this
        // annotatedString , will get an offset

        //now we will use this offset to find out the span that we have clicked
        annotatedString.getStringAnnotations(offset/*start*/,offset/*end*/)
            .firstOrNull()/*if there is a valid span click*/?.also {
                    span->
                Log.d("ClickableTextComponent","{$span}")


                if(span.item==loginText) {
                    onTextSelected(span.item)

                }
            }

    })
}

@Composable
fun UnderLinedTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        color = Red,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline

    )

}