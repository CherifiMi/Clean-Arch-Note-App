package com.example.cleanarchnoteapp.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchnoteapp.feature_note.data.local.NoteDatabas
import com.example.cleanarchnoteapp.feature_note.data.repository.NoteRepositoryImp
import com.example.cleanarchnoteapp.feature_note.domain.repository.NoteRepository
import com.example.cleanarchnoteapp.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabas{
        return Room.inMemoryDatabaseBuilder(
            app,
            NoteDatabas::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabas): NoteRepository{
        return NoteRepositoryImp(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            insertNote = InsertNote(repository),
            getNote = GetNote(repository)
        )
    }

}