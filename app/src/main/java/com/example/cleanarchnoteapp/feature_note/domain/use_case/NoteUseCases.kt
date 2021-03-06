package com.example.cleanarchnoteapp.feature_note.domain.use_case

data class NoteUseCases(
    val getNotes: GetNotes,
    val getNote: GetNote,
    val deleteNote: DeleteNote,
    val insertNote: InsertNote
)
