package com.example.androidexternshipproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidexternshipproject.Components.ButtonComponent
import com.example.androidexternshipproject.Components.CheckboxComponent
import com.example.androidexternshipproject.Components.ClickableLoginTextComponent
import com.example.androidexternshipproject.Components.DividerTextComponent
import com.example.androidexternshipproject.Components.HeadingTextComponent
import com.example.androidexternshipproject.Components.MyTextFieldComponent
import com.example.androidexternshipproject.Components.NormalTextComponent
import com.example.androidexternshipproject.Components.PasswordTextFieldComponent
import com.example.androidexternshipproject.R
import com.example.androidexternshipproject.dbInteractions.CredentialsEntity
import com.example.androidexternshipproject.dbInteractions.RoomDbDao
import com.example.androidexternshipproject.navigation.PodcastAppRouter
import com.example.androidexternshipproject.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(dao: RoomDbDao) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(28.dp),
        color = Color.Black,


    ){

        val firstName = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val tnc = remember{mutableStateOf(false)}
        val scope = CoroutineScope(Dispatchers.Main)
        Column(modifier = Modifier.fillMaxSize()) {

            NormalTextComponent(value = stringResource(id = R.string.heyThere))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                textValue = firstName,
                labelValue = stringResource(id = R.string.first_name),
                painterResource = painterResource(id = R.drawable.profile)
            )
            MyTextFieldComponent(
                textValue = lastName,
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.profile)
            )
            MyTextFieldComponent(
                textValue = email,
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.message)
            )
            PasswordTextFieldComponent(
                password = password,
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.lock)
            )

            CheckboxComponent(checkedState = tnc, value = stringResource(id = R.string.terms_and_conditions),
            onTextSelected = {
                PodcastAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
            })
            
            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(id = R.string.register)){
                val item = CredentialsEntity(email = email.value, password = password.value)
                scope.launch(Dispatchers.IO) {
                    dao.insert(item)
                }
                PodcastAppRouter.navigateTo(Screen.PodcastHolderScreen)
            }

            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = true,
                onTextSelected ={
                    PodcastAppRouter.navigateTo(Screen.LoginScreen)

            } )

        }

    }
}

//@Preview
//@Composable
//fun SignUpScreenPreview(){
//    SignUpScreen()
//}