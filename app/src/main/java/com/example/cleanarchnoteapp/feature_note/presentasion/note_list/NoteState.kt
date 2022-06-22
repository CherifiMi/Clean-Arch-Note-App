package com.example.cleanarchnoteapp.feature_note.presentasion.note_list

import com.example.cleanarchnoteapp.feature_note.domain.model.Note
import com.example.cleanarchnoteapp.feature_note.domain.util.NoteOrder
import com.example.cleanarchnoteapp.feature_note.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Des),
    val isOrderSectionVisible: Boolean = false
)
