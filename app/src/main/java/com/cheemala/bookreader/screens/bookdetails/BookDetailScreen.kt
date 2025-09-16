package com.cheemala.bookreader.screens.bookdetails

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.cheemala.bookreader.R
import com.cheemala.bookreader.components.BookReaderAppBar
import com.cheemala.bookreader.model.data.DataOrException
import com.cheemala.bookreader.model.data.Item
import androidx.compose.ui.platform.LocalResources
import com.cheemala.bookreader.components.RoundedCornerButton
import com.cheemala.bookreader.model.data.Book

@Composable
fun BookDetailScreen(
    navController: NavController,
    detailScreenViewModel: BookDetailScreenViewModel = hiltViewModel(),
    bookId: String
) {

    Surface(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .fillMaxSize()
    ) {

        Scaffold(
            topBar = {
                BookReaderAppBar(
                    title = "Book Details", showProfile = true, isHomeScreen = false, navController
                )
            }) {

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                // Call Book Info
                val bookDetails = produceState<DataOrException<Item, Boolean, Exception>>(
                    initialValue = DataOrException(
                        data = null, loading = true, exception = null
                    )
                ) {
                    value = detailScreenViewModel.getBookInfo(bookId = bookId)
                }.value

                if (bookDetails.loading == true) {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    ShowBookDetails(bookDetails.data, detailScreenViewModel, navController)

                }
            }

        }

    }

}

@Composable
fun ShowBookDetails(
    bookDetailObj: Item?,
    detailScreenViewModel: BookDetailScreenViewModel,
    navController: NavController
) {

    val bookSavedSts by detailScreenViewModel.bookSavedStatus

    if(bookSavedSts)
        navController.popBackStack()

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(35.dp)
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier.padding(5.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {

                AsyncImage(
                    modifier = Modifier
                        .padding(1.dp)
                        .size(90.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(bookDetailObj?.volumeInfo?.imageLinks?.thumbnail ?: "").build(),
                    placeholder = painterResource(R.drawable.rich_dad_poor_dad_poster),
                    contentDescription = "Book Poster Image",

                    onSuccess = { success ->
                        Log.d("Image_load_successfully_", success.result.dataSource.toString())
                    },
                    onLoading = {
                        Log.d("Image_load_", "Loading...")
                    },
                    onError = { error ->
                        Log.d("Image_load_error_", error.result.throwable.message.toString())
                    })
            }

        }

        Text(
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp),
            text = bookDetailObj?.volumeInfo?.title ?: "",
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp),
            text = bookDetailObj?.volumeInfo?.authors.toString(),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp),
            text = "Page Count : ${bookDetailObj?.volumeInfo?.pageCount}",
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp),
            text = "Categories : ${bookDetailObj?.volumeInfo?.categories.toString()}",
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal
        )
        Text(
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp),
            text = "Published On : ${bookDetailObj?.volumeInfo?.publishedDate}",
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal
        )

        Spacer(
            modifier = Modifier
                .padding(2.dp)
                .height(5.dp)
        )

        val dims = LocalResources.current.displayMetrics
        Surface(
            modifier = Modifier
                .padding(2.dp)
                .height(dims.heightPixels.dp.times(0.09f)),
            shape = RectangleShape,
            border = BorderStroke(
                width = 1.dp, Color.DarkGray
            )
        ) {

            LazyColumn {
                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp),
                        text = "Description: ${bookDetailObj?.volumeInfo?.description}",
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

        }

        Row(modifier = Modifier.padding(1.dp), horizontalArrangement = Arrangement.SpaceAround) {

            RoundedCornerButton(
                label = LocalResources.current.getString(R.string.save_name),
                radius = 30,
                color = Color(
                    0xFF03A9F4
                )
            ) {
                Log.d("btn_clicked_", "save")
                detailScreenViewModel.saveBook(
                    Book(
                        bookDetailObj?.id ?: "",
                        bookTitle = bookDetailObj?.volumeInfo?.title,
                        bookAuthors = bookDetailObj?.volumeInfo?.authors.toString(),
                        bookDesc = bookDetailObj?.volumeInfo?.description.toString(),
                        rating = bookDetailObj?.volumeInfo?.ratingsCount?.toFloat()!!
                    )
                )
            }

            RoundedCornerButton(
                label = LocalResources.current.getString(R.string.cancel_name),
                radius = 30,
                color = Color.Red
            ) {
                navController.popBackStack()
            }

        }

    }
}