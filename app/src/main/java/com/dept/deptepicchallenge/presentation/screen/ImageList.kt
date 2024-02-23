package com.dept.deptepicchallenge.presentation.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dept.deptepicchallenge.R
import com.dept.deptepicchallenge.presentation.MainViewModel
import com.dept.deptepicchallenge.presentation.theme.Black_bg
import com.dept.deptepicchallenge.presentation.theme.Blue
import com.dept.deptepicchallenge.presentation.theme.Grey
import com.dept.deptepicchallenge.presentation.theme.Grey_2
import com.dept.deptepicchallenge.util.Util

@Composable
fun ImageList(viewModel: MainViewModel, date: String) {

    viewModel.fetchImageList(date)

    val images = viewModel.imageList.observeAsState()

    var imageCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Grey),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        images.value?.let {
            if (it.isEmpty() || (!it.isEmpty() && it.size > imageCount)) {
                composeButton(false)
            } else
                composeButton(true)
        } ?: composeButton(false)

        val state = rememberLazyGridState()

        LazyVerticalGrid(
            state = state,
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
        ) {
            images.value?.let {
                items(it) { image ->
                    Log.d("dash", Util.getFormatDate(date))
                    ImageItem(
                        item = image,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .aspectRatio(1.5f),
                        listener = {
                            imageCount++
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun composeButton(finished: Boolean) {
    val context = LocalContext.current
    Button(
        onClick = {
            //TODO: not finished yet
            if (finished) {
                Toast.makeText(context, "Not finished!!", Toast.LENGTH_LONG).show()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(15),
        colors = if (finished) {
            ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = Blue,
                disabledContainerColor = Blue,
                disabledContentColor = Blue
            )
        } else {
            ButtonDefaults.buttonColors(
                containerColor = Black_bg,
                contentColor = Black_bg,
                disabledContainerColor = Black_bg,
                disabledContentColor = Black_bg
            )
        }
    ) {
        if (finished) {
            Text(stringResource(id = R.string.play_images), color = Grey_2)

        } else
            Text(stringResource(id = R.string.downloading_images), color = Grey_2)
    }
}

fun interface ImageListener {
    fun finished()
}
