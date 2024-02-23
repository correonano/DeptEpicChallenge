package com.dept.deptepicchallenge.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dept.deptepicchallenge.presentation.MainViewModel
import com.dept.deptepicchallenge.R
import com.dept.deptepicchallenge.util.Util
import com.dept.deptepicchallenge.presentation.theme.Grey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dates") {
        composable("dates") {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Grey,
                            titleContentColor = Color.White,
                        ),
                        title = {
                            Text(
                                stringResource(id = R.string.daily_images),
                                style = MaterialTheme.typography.titleLarge
                            )
                        },
                    )
                },
            ) { innerPadding ->
                Column(
                    Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    DatesScreen(navController = navController, viewModel = viewModel)
                }
            }
        }

        composable(
            "imageList/{date}",
            arguments = listOf(navArgument("date") { type = NavType.StringType })
        ) {
            val dateString = it.arguments?.getString("date") ?: ""
            val dayName = Util.getDayName(dateString)
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Grey,
                            titleContentColor = Color.White,
                        ),
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    tint = Color.White,
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        },
                        title = {
                            Text(
                                "$dayName $dateString",
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }
                    )
                },
            ) { innerPadding ->
                Column(
                    Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ImageList(viewModel = viewModel, dateString)
                }
            }
        }

    }
}