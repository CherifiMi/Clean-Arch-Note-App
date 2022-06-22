package com.example.cleanarchnoteapp.feature_note.domain.use_case

import com.example.cleanarchnoteapp.feature_note.domain.model.Note
import com.example.cleanarchnoteapp.feature_note.domain.repository.NoteRepository

class InsertNote (private val repo: NoteRepository){
    suspend operator fun invoke(note: Note){
        repo.insertNote(note)
    }
}