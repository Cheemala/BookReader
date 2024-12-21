package com.cheemala.bookreader.screens.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cheemala.bookreader.components.AppHeaderText
import com.cheemala.bookreader.components.EmailInputField
import com.cheemala.bookreader.components.PasswordInputField
import com.cheemala.bookreader.R
import com.cheemala.bookreader.components.SubmitBtn
import com.cheemala.bookreader.components.TopBar
import com.cheemala.bookreader.model.data.User
import com.cheemala.bookreader.navigation.BookReaderScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun SignUpOrLoginScreen(
    navController: NavController,
    signUpOrLoginScreenViewModel: SignUpOrLoginScreenViewModel = hiltViewModel()
) {

    val isItLoginStateValue = rememberSaveable {
        mutableStateOf(true)
    }

    val loadingStateValue = rememberSaveable {
        mutableStateOf(false)
    }

    val submitBtnOrTitleText = if (isItLoginStateValue.value && !loadingStateValue.value) {
        stringResource(id = R.string.login_name)
    } else if (!isItLoginStateValue.value && !loadingStateValue.value) stringResource(
        id = R.string.sign_up_name
    ) else ""

    Scaffold(topBar = { TopBar(title = submitBtnOrTitleText) }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            ImpLoginOrSignUpScreen(
                isItLoginStateValue,
                submitBtnOrTitleText,
                loadingStateValue,
                signUpOrLoginScreenViewModel,
                navController
            )
        }
    }
}

@Composable
fun ImpLoginOrSignUpScreen(
    isItLoginStateValue: MutableState<Boolean>,
    submitBtnOrTitleText: String,
    loadingStateValue: MutableState<Boolean>,
    signUpOrLoginScreenViewModel: SignUpOrLoginScreenViewModel,
    navController: NavController
) {

    val emailStateValue = rememberSaveable {
        mutableStateOf("")
    }

    val passwordStateValue = rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        AppHeaderText(title = submitBtnOrTitleText)
        Spacer(modifier = Modifier.height(25.dp))
        EmailInputField(
            value = emailStateValue,
            label = "Email",
            singleLine = true,
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(5.dp))
        PasswordInputField(
            passwordValue = passwordStateValue,
            label = "Password",
            singleLine = true,
            keyboardType = KeyboardType.Password
        )
        Spacer(modifier = Modifier.height(5.dp))
        SubmitBtn(
            btnLabel = submitBtnOrTitleText,
            emailState = emailStateValue,
            pswrdState = passwordStateValue,
            loading = loadingStateValue
        ) {

            if (isItLoginStateValue.value) {
                if (emailStateValue.value.isNotEmpty() && passwordStateValue.value.isNotEmpty()) {
                    CoroutineScope(Dispatchers.Main).launch {
                        val userLoginStatus = signUpOrLoginScreenViewModel.checkUserExisted(
                            emailStateValue.value,
                            passwordStateValue.value
                        )
                        if(userLoginStatus){
                            navController.popBackStack(BookReaderScreens.SignUpOrLoginScreen.name, true)
                            navController.navigate(BookReaderScreens.HomeScreen.name)
                        }

                        Log.d("user_login_status_", "$userLoginStatus")
                    }
                }
            } else {
                if (emailStateValue.value.isNotEmpty() && passwordStateValue.value.isNotEmpty()) {
                    CoroutineScope(Dispatchers.Main).launch {
                        val userSignUpStatus = signUpOrLoginScreenViewModel.signUpUser(
                            User(
                                userEmail = emailStateValue.value,
                                userPassword = passwordStateValue.value,
                                userLoginStatus = false
                            )
                        )
                        if(userSignUpStatus){
                            isItLoginStateValue.value = !isItLoginStateValue.value
                            emailStateValue.value = ""
                            passwordStateValue.value = ""
                            Log.d("user", "$userSignUpStatus")
                        }

                    }

                }
            }
        }

        if (isItLoginStateValue.value) {
            Spacer(modifier = Modifier.height(10.dp))
            TextButton(onClick = {
                isItLoginStateValue.value = !isItLoginStateValue.value
                Log.d("Signup_", "clicked!")
            }) {
                Text(
                    text = stringResource(id = R.string.no_account_name),
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 6.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = colorResource(
                        id = R.color.red
                    )
                )
            }
        } else {
            Box {}
        }
    }

}
