package com.cheemala.bookreader.screens.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.cheemala.bookreader.R
import com.cheemala.bookreader.components.BookReaderAppBar
import com.cheemala.bookreader.components.InputField
import com.cheemala.bookreader.navigation.BookReaderScreens

@Composable
fun SearchScreen(
    navController: NavController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {

    Surface(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .fillMaxSize()
    ) {
        SetupSearchScreen(navController, searchScreenViewModel)
    }

}


@Composable
fun SetupSearchScreen(navController: NavController, searchScreenViewModel: SearchScreenViewModel) {
    Scaffold(
        topBar = {
            BookReaderAppBar(
                title = "Search Books",
                showProfile = true,
                isHomeScreen = false,
                navController
            )
        }) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            ShowSearchBox(navController, searchScreenViewModel)
        }

    }

}

@Composable
fun ShowSearchBox(navController: NavController, searchScreenViewModel: SearchScreenViewModel) {

    val searchedBookTxt = rememberSaveable {
        mutableStateOf("")
    }
    val validInputTxt = remember(searchedBookTxt.value) {
        searchedBookTxt.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    keyboardController?.let { keyboardControllerObj ->
        SearchForm(
            navController = navController,
            searchedBookTxt,
            "Search Books",
            keyboardControllerObj
        ) { searchedString ->
            Log.d("searched_string_", searchedString)
            if (!validInputTxt)
                return@SearchForm
            searchScreenViewModel.searchBooks(searchedString)
        }
    }

}

@Composable
fun SearchForm(
    navController: NavController,
    searchedBookTxt: MutableState<String>,
    lable: String,
    keyboardController: SoftwareKeyboardController,
    onSearch: (String) -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {

        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = searchedBookTxt,
            label = lable,
            singleLine = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
            onAction = KeyboardActions {
                if (searchedBookTxt.value.trim().isEmpty())
                    return@KeyboardActions
                onSearch(searchedBookTxt.value)
                keyboardController.hide()
            })

        Spacer(modifier = Modifier.height(10.dp))

        DisplaySearchedBooks(navController = navController)

    }
}

@Composable
fun DisplaySearchedBooks(
    navController: NavController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {
    // Observe the MutableState directly
    val searchedBookInfo by searchScreenViewModel.searchedBookInfoState

    Log.d("display_book_data_", searchedBookInfo.data.toString())

    if (searchedBookInfo.loading == true) {
        Box(
            modifier = Modifier.fillMaxSize(), // Makes the Box take up the entire screen
            contentAlignment = Alignment.Center // Centers its content (the CircularProgressIndicator)
        ) {
            CircularProgressIndicator() // The progress indicator itself
        }

    } else {
        searchedBookInfo.data?.let { bookInfoList ->
            Log.d("bookInfoList_",bookInfoList.toString())
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LazyColumn {

                    items(bookInfoList) { item ->
                        Log.d("book_item_", item.toString())
                        BookItem(
                            navController = navController,
                            bookId = item.id,
                            bookTitle = item.volumeInfo.title.toString(),
                            bookSubTitle = item.volumeInfo.subtitle.orEmpty(),
                            authors = item.volumeInfo.authors,
                            smallImgThumbnail = item.volumeInfo.imageLinks.smallThumbnail ?:""
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun BookItem(
    navController: NavController,
    bookId: String,
    bookTitle: String,
    bookSubTitle: String,
    authors: List<String>?,
    smallImgThumbnail: String = ""
) {

    Log.d("book_img_", smallImgThumbnail)

    Card(
        onClick = { navController.navigate(route = BookReaderScreens.BookDetailScreen.name+"/$bookId") }, modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                modifier = Modifier
                    .width(120.dp)
                    .height(150.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(smallImgThumbnail)
                    .build(),
                placeholder = painterResource(R.drawable.rich_dad_poor_dad_poster),
                contentDescription = "Book Poster Image",
                contentScale = ContentScale.FillBounds,
                onSuccess = { success ->
                    Log.d("Image_load_successfully_", success.result.dataSource.toString())
                },
                onLoading = {
                    Log.d("Image_load_", "Loading...")
                },
                onError = { error ->
                    Log.d("Image_load_error_", error.result.throwable.message.toString())
                })
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier.padding(2.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = bookTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = bookSubTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = authors.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )
            }

        }

    }

}

