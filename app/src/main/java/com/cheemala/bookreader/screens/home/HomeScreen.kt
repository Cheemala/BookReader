package com.cheemala.bookreader.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cheemala.bookreader.R
import com.cheemala.bookreader.components.BookReaderAppBar
import com.cheemala.bookreader.components.ReadingList
import com.cheemala.bookreader.components.ReadingListHeader
import com.cheemala.bookreader.components.ReadingListRightNow
import com.cheemala.bookreader.components.ReadingListRightNowHeader
import com.cheemala.bookreader.model.data.Book
import com.cheemala.bookreader.navigation.BookReaderScreens

@Composable
fun HomeScreen(navController: NavController) {

    Surface(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .fillMaxSize()
    ) {

        SetupHomeScreen(navController)

    }
}

@Composable
fun SetupHomeScreen(navController: NavController) {

    Scaffold(
        topBar = { BookReaderAppBar(title = "Home", showProfile = true, isHomeScreen = true, navController) },
        floatingActionButton = {
            FABContent {
                navController.navigate(BookReaderScreens.SearchScreen.name)
            }
        }) {

        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {


            ReadingListRightNowHeader()
            HorizontalDivider(modifier = Modifier.height(1.dp), color = Color.LightGray)

            ReadingListRightNow(bookList = arrayListOf(Book(bookId = "1", bookTitle = "Rich-Poor Dad", bookAuthors = "Kioski, Kavasiki", bookDesc = "A fantastic book on financial education that is widely accepted by many people across world wide and changed their lifes", rating = 4.5f)))

            ReadingListHeader()
            HorizontalDivider(modifier = Modifier.height(1.dp), color = Color.LightGray)

            val readingBookList: List<Book> = arrayListOf(
                Book(bookId = "1", bookTitle = "Rich-Poor Dad", bookAuthors = "Kioski, Kavasiki", bookDesc = "A fantastic book on financial education that is widely accepted by many people across world wide and changed their lifes", rating = 4.5f),
                Book(bookId = "2", bookTitle = "Psychology Of Money", bookAuthors = "Krish D, Kavasiki", bookDesc = "A fantastic book on financial education that is widely accepted by many people across world wide and changed their lifes", rating = 3.5f),
                Book(bookId = "3", bookTitle = "Man Made Everything", bookAuthors = "Loren, David Chris, Christy", bookDesc = "A fantastic book on man made education that is widely accepted by many people across world wide and changed their lifes", rating = 4.0f),
                Book(bookId = "4", bookTitle = "Who will Cry when You Die", bookAuthors = "Maximus, Monk", bookDesc = "A fantastic book on financial education that is widely accepted by many people across world wide and changed their lifes", rating = 3.0f),
                Book(bookId = "5", bookTitle = "Mind Your Business", bookAuthors = "Lorsen, Peter Park", bookDesc = "A fantastic book on financial education that is widely accepted by many people across world wide and changed their lifes", rating = 3.5f),
                Book(bookId = "6", bookTitle = "Rewire Your Brain", bookAuthors = "Jerrymia, Branda S", bookDesc = "A fantastic book on psychological education that is widely accepted by many people across world wide and changed their lifes", rating = 4.0f)
            )
            ReadingList(readingBookList)
        }

    }

}
@Composable
fun FABContent(onFabTap: (String) -> Unit) {

    FloatingActionButton(
        onClick = { onFabTap("") },
        shape = RoundedCornerShape(50.dp),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 2.dp,
            focusedElevation = 5.dp,
            hoveredElevation = 3.dp,
            pressedElevation = 8.dp
        ),
        containerColor = Color.DarkGray
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.add_icon),
            contentDescription = "Add Book",
            tint = Color.White
        )
    }

}
