package com.dept.deptepicchallenge.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dept.deptepicchallenge.presentation.MainViewModel

@Composable
fun DatesScreen(navController: NavController, viewModel: MainViewModel) {

    val dates = viewModel.dateList.observeAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        dates.value?.let {
            items(it) { date ->
                DateListItem(navController, viewModel, date = date.date)
            }
        }
    }
}
