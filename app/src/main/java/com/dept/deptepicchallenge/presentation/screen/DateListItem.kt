package com.dept.deptepicchallenge.presentation.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.disk.DiskCache
import coil.request.ImageRequest
import com.dept.deptepicchallenge.R
import com.dept.deptepicchallenge.presentation.MainViewModel
import com.dept.deptepicchallenge.presentation.theme.Grey
import com.dept.deptepicchallenge.presentation.theme.Grey_2
import com.dept.deptepicchallenge.util.Constants
import com.dept.deptepicchallenge.util.Util

@Composable
fun DateListItem(
    navController: NavController,
    viewModel: MainViewModel,
    date: String
) {
    var imageCount by remember { mutableStateOf(0) }
    var isDownloading by remember { mutableStateOf(false) }
    val images = viewModel.imageList.observeAsState()

    images.value?.let {
        //TODO: not finished
        if (it.isNotEmpty()) {
            for (item in it) {
                val url =
                    "${Constants.BASE_IMAGE_URL}${Util.getFormatDate(item.day)}/${Constants.PNG}/${item.image}${Constants.PNG_ext}"
                getImageLoader(LocalContext.current).enqueue(
                    ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .target(onSuccess = {
                            imageCount++
                        }, onError = {
                            imageCount++
                        })
                        .build(),
                )
            }

        }
    }

    LazyRow(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .background(color = Grey, shape = RoundedCornerShape(size = 8.dp))
            .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            .clickable {
                navController.navigate("imageList/$date")
            }
    ) {
        item {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = Util.getDayName(date),
                    style = TextStyle(
                        fontSize = 21.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(700),
                        color = Color.White
                    )
                )
                Text(
                    text = date, //
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFB2B7BD),
                    )
                )
            }
        }

        item {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.Black, shape = RoundedCornerShape(size = 4.dp))
                    .padding(end = 2.dp)
                    .clickable {
                        if (!isDownloading) {
                            viewModel.fetchImageList(date)
                            isDownloading = true
                        }
                    },

                ) {
                if (!isDownloading) {
                    Text(
                        text = stringResource(id = R.string.start_downloading),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFB2B7BD),
                        ),
                        modifier = Modifier.padding(
                            start = 4.dp,
                            end = 4.dp,
                            top = 2.dp,
                            bottom = 2.dp
                        )
                    )
                } else {
                    Icon(
                        Icons.Filled.Refresh,
                        contentDescription = null,
                        tint = Color(0xFFFFBD3D),
                        modifier = Modifier
                            .padding(end = 2.dp)
                            .width(20.dp)
                            .height(20.dp)
                    )
                    //TODO: not finished, behavior incompleted
                    composeCountText(imageCount, images.value?.size ?: 0)

                }

                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color(Grey_2.value),
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                )
            }
        }
    }
}

//TODO: not finished
@Composable
fun composeCountText(imageCount: Int, imagesSize: Int) {
    var text = "0/00"
    if (imagesSize != 0 && imageCount < imagesSize)
        text = "" + imageCount + "/" + imagesSize
    Text(
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFFFBD3D),
        )
    )
}

//TODO: not finished
//could be injected
fun getImageLoader(context: Context) = ImageLoader.Builder(context)
    .diskCache {
        DiskCache.Builder()
            .directory(context.cacheDir.resolve("image_cache"))
            .maxSizePercent(0.02)
            .build()
    }.build()
