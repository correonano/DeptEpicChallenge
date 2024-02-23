package com.dept.deptepicchallenge.presentation.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.dept.deptepicchallenge.util.Constants
import com.dept.deptepicchallenge.util.Util
import com.dept.deptepicchallenge.domain.model.EpicDetails

//TODO: layout not completed yet
@Composable
fun ImageItem(item: EpicDetails, modifier: Modifier = Modifier, listener: ImageListener) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.large
    ) {

        val url = "${Constants.BASE_IMAGE_URL}${Util.getFormatDate(item.day)}/${Constants.PNG}/${item.image}${Constants.PNG_ext}"
        SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build(),
            contentDescription = null,
            loading = {
                CircularProgressIndicator(color = Color.Black)
            },
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
            onSuccess = {
                listener.finished()
            },
            onError = {
                listener.finished()
            })
    }
}