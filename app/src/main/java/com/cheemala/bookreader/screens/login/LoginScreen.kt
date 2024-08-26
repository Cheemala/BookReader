package com.cheemala.bookreader.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cheemala.bookreader.AppHeaderText
import com.cheemala.bookreader.EmailInputField
import com.cheemala.bookreader.PasswordInputField
import com.cheemala.bookreader.R
import com.cheemala.bookreader.SubmitBtn
import com.cheemala.bookreader.TopBar

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(topBar = { TopBar(title = stringResource(id = R.string.login_name)) }) {
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            ImpLoginScreen(navController)
        }
    }
}

@Composable
fun ImpLoginScreen(navController: NavController) {

    val emailStateValue = rememberSaveable {
        mutableStateOf("")
    }

    val passwordStateValue = rememberSaveable {
        mutableStateOf("")
    }

    val loadingStateValue = rememberSaveable {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(5.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))
        AppHeaderText(title = R.string.app_name)
        Spacer(modifier = Modifier.height(25.dp))
        EmailInputField(value = emailStateValue, label = "Email", singleLine = true, keyboardType = KeyboardType.Text)
        Spacer(modifier = Modifier.height(5.dp))
        PasswordInputField(passwordValue = passwordStateValue, label = "Password", singleLine = true, keyboardType = KeyboardType.Password)
        Spacer(modifier = Modifier.height(5.dp))
        SubmitBtn(btnLabel = R.string.email_name, loading = loadingStateValue){
            loadingStateValue.value = !loadingStateValue.value
        }
    }

}
