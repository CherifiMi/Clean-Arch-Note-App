package com.example.cleanarchnoteapp.feature_note.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchnoteapp.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabas: RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object{
        const val DATABSE_NAME = "note_db"
    }

}