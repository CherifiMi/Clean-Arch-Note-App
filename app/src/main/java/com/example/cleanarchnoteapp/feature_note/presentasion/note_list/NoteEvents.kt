package com.example.cleanarchnoteapp.feature_note.presentasion.note_list

import com.example.cleanarchnoteapp.feature_note.domain.model.Note
import com.example.cleanarchnoteapp.feature_note.domain.util.NoteOrder

sealed class NoteEvents{
    data class Order(val noteOrder: NoteOrder): NoteEvents()
    data class DeleteNote(val note: Note): NoteEvents()
    object RestoreNote: NoteEvents()
    object ToggleOrderSection: NoteEvents()
}
