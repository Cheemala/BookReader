package com.cheemala.bookreader.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cheemala.bookreader.R
import com.cheemala.bookreader.model.data.Book


@Composable
fun AppHeaderText(title: String) {
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
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookReaderAppBar(showProfile: Boolean, navController: NavController) {

    TopAppBar(
        modifier = Modifier
            .padding(5.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                /*if(showProfile){
                    Surface(modifier = Modifier.size(30.dp), shape = RoundedCornerShape(50.dp), contentColor = Color.DarkGray) {

                    }
                }*/
                Text(
                    text = "BookReader",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Red,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 25.sp
                    )
                )
            }
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.logout_icon),
                contentDescription = "Logout",
                modifier = Modifier.size(30.dp),
                tint = Color.Red
            )
        }
        /*modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp), spotColor = Color.DarkGray)*/
    )

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
    btnLabel: String,
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
            Text(text = btnLabel, textAlign = TextAlign.Center)
    }

}

@Composable
fun ReadingListRightNowHeader() {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleText(title = "Reading List Right Now", textAlign = TextAlign.Start, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .size(30.dp)
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.profile_icon), contentDescription = "Profile", modifier = Modifier.size(50.dp), tint = Color.Red)
            }
            TitleText(title = "Hi, Chai", textAlign = TextAlign.End, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

    }
}


@Composable
fun ReadingListRightNow(bookList: List<Book> = emptyList()) {

    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BookItem(bookList[0])

    }

}


@Composable
fun ReadingListHeader() {
    TitleText(
        title = "Reading List",
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}

@Composable
fun ReadingList(bookList: List<Book> = emptyList()) {

    val scrollState = rememberScrollState()

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(260.dp)
        .horizontalScroll(state = scrollState)) {

        for(book in bookList){
            BookItem(bookItem = book)
        }

    }

}

@Composable
fun BookItem(bookItem: Book) {

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(15.dp),
                spotColor = Color.Black
            ),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.DarkGray,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.LightGray
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(1.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(2.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.rich_dad_poor_dad_poster),
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .width(120.dp)
                        .height(150.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart_icon),
                        contentDescription = "Heart",
                        modifier = Modifier.size(30.dp)
                    )

                    Card(
                        modifier = Modifier
                            .width(50.dp)
                            .height(80.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(50.dp),
                                spotColor = Color.Black
                            ),
                        colors = CardColors(
                            containerColor = Color.White,
                            contentColor = Color.DarkGray,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = Color.LightGray
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(5.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.star_icon),
                                contentDescription = "Heart",
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "${bookItem.rating}",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }
            }
            Text(
                text = bookItem.bookTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = bookItem.bookAuthors,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                RoundedButton(title = "Reading", radius = 70)
            }
        }

    }

}

@Composable
fun RoundedButton(title: String = "Demo", radius: Int = 30) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .clip(RoundedCornerShape(topStartPercent = radius, bottomEndPercent = radius)),
        color = Color.Red
    ) {
        Column(
            modifier = Modifier
                .width(90.dp)
                .height(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}


@Composable
fun TitleText(title: String, textAlign: TextAlign, fontWeight: FontWeight, fontSize:TextUnit, maxLines: Int = 1, overFlow: TextOverflow = TextOverflow.Ellipsis){
    Text(text = title, textAlign = textAlign, fontWeight = fontWeight, fontSize = fontSize)
}