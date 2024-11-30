package com.cheemala.bookreader

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AppHeaderText(title: Int) {
    Column(
        modifier = Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.book_icon),
            contentDescription = "Book Icon",
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    navIcon: Icon? = null
) {
    TopAppBar(
        title = { Text(text = title) }, modifier = Modifier
            .padding(5.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp), spotColor = Color.Black)
    )
}

@Composable
fun EmailInputField(
    modifier: Modifier = Modifier,
    value: MutableState<String>,
    label: String,
    singleLine: Boolean,
    enabled: Boolean = true,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Default,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(text = label) },
        singleLine = singleLine,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )

}

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    passwordValue: MutableState<String>,
    label: String,
    singleLine: Boolean,
    enabled: Boolean = true,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Default,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = passwordValue.value,
        onValueChange = { passwordValue.value = it },
        label = { Text(text = label) },
        singleLine = singleLine,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )

}

@Composable
fun SubmitBtn(
    modifier: Modifier = Modifier,
    btnLabel: Int,
    loading: MutableState<Boolean>,
    emailState: MutableState<String>,
    pswrdState: MutableState<String>,
    onBtnClicked: () -> Unit = {},
) {

    Button(
        onClick = {
            onBtnClicked()
        },
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        enabled = !loading.value && emailState.value.isNotEmpty() && pswrdState.value.isNotEmpty(),
        shape = CircleShape
    ) {
        if (loading.value) CircularProgressIndicator(modifier = modifier.size(50.dp))
        else
            Text(text = stringResource(id = btnLabel), textAlign = TextAlign.Center)
    }

}