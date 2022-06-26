package com.example.cleanarchnoteapp.feature_note.presentasion.note_add_edit

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvents{
    data class EnteredTitle(val value: String): AddEditNoteEvents()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvents()
    data class EnteredDec(val value: String): AddEditNoteEvents()
    data class ChangeDecFocus(val focusState: FocusState): AddEditNoteEvents()
    data class ChangeColor(val color: Int): AddEditNoteEvents()
    object SaveNote: AddEditNoteEvents()
}