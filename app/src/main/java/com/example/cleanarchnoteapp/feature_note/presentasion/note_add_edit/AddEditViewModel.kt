package com.example.cleanarchnoteapp.feature_note.presentasion.note_add_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.example.cleanarchnoteapp.feature_note.domain.model.Note
import com.example.cleanarchnoteapp.feature_note.domain.use_case.NoteUseCases
import com.example.cleanarchnoteapp.feature_note.domain.util.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState())
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteDec = mutableStateOf(NoteTextFieldState())
    val noteDec: State<NoteTextFieldState> = _noteTitle

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor



    // get one note


    // replace old note by the edited one

}