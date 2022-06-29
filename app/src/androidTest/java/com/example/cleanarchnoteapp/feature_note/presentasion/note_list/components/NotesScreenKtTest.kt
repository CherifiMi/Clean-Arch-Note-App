package com.example.cleanarchnoteapp.feature_note.presentasion.note_list.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanarchnoteapp.di.AppModule
import com.example.cleanarchnoteapp.feature_note.presentasion.MainActivity
import com.example.cleanarchnoteapp.feature_note.presentasion.util.Screen
import com.example.cleanarchnoteapp.ui.theme.CleanArchNoteAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesScreenKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.setContent {
            val navController = rememberNavController()
            CleanArchNoteAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.NotesScreen.route
                ) {
                    composable(Screen.NotesScreen.route){
                        NotesScreen(navController = navController)
                    }
                }
            }
        }



    }

    @Test
    fun clickToggleOrderThing_isVisible(){

    }

}