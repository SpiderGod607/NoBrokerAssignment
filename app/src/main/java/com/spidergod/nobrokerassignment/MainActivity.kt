package com.spidergod.nobrokerassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spidergod.nobrokerassignment.ui.presentation.list_screen.ListScreen
import com.spidergod.nobrokerassignment.ui.theme.NoBrokerAssignmentTheme
import com.spidergod.nobrokerassignment.util.Constants.LIST_SCREEN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoBrokerAssignmentTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = LIST_SCREEN) {
                    composable(LIST_SCREEN) {
                        ListScreen()
                    }
                }
            }
        }
    }
}
