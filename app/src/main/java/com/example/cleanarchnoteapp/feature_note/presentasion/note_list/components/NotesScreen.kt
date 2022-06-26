package com.example.cleanarchnoteapp.feature_note.presentasion.note_list.components

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cleanarchnoteapp.feature_note.presentasion.note_list.NoteEvents
import com.example.cleanarchnoteapp.feature_note.presentasion.note_list.NoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    /*TODO navigate to the add screen*/
                },
                backgroundColor = Color.White
            ){
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        scaffoldState = scaffoldState
    ){
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Notes",
                    style = MaterialTheme.typography.h4
                )
                IconButton(onClick = {
                        viewModel.onEvent(NoteEvents.ToggleOrderSection)
                    }
                ){
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    onOrderChange = {
                        viewModel.onEvent(NoteEvents.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.notes){note->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            },
                        onDeleteClick = {
                            viewModel.onEvent(NoteEvents.DeleteNote(note))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note Deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed){
                                    viewModel.onEvent(NoteEvents.RestoreNote)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}