package com.example.cleanarchnoteapp.feature_note.domain.use_case

import com.example.cleanarchnoteapp.feature_note.data.repository.FakeNoteRepository
import com.example.cleanarchnoteapp.feature_note.data.repository.NoteRepositoryImp
import com.example.cleanarchnoteapp.feature_note.domain.model.Note
import com.example.cleanarchnoteapp.feature_note.domain.util.NoteOrder
import com.example.cleanarchnoteapp.feature_note.domain.util.OrderType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesTest{

    private lateinit var getNotes: GetNotes
    private lateinit var fakerepo: FakeNoteRepository

    @Before
    fun setUp(){
        fakerepo = FakeNoteRepository()
        getNotes = GetNotes(fakerepo)

        val notesToAdd = mutableListOf<Note>()
        ('a'..'z').forEachIndexed{index, c ->

            notesToAdd.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )

        }
        notesToAdd.shuffle()
        runBlocking {
            notesToAdd.forEach{
                fakerepo.insertNote(it)
            }
        }
    }

    @Test
    fun `notes by title asc, correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Asc)).first()

        for(i in 0..notes.size-2){
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }
    }
    @Test
    fun `notes by date asc, correct order`()= runBlocking{
            val notes = getNotes(NoteOrder.Date(OrderType.Asc)).first()

            for(i in 0..notes.size-2){
                assertThat(notes[i].timestamp).isLessThan(notes[i+1].timestamp)
            }
        }
    @Test
    fun `notes by color asc, correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Color(OrderType.Asc)).first()

        for(i in 0..notes.size-2){
            assertThat(notes[i].color).isLessThan(notes[i+1].color)
        }
    }


    @Test
    fun `notes by title des, correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Title(OrderType.Des)).first()

        for(i in 0..notes.size-2){
            assertThat(notes[i].title).isGreaterThan(notes[i+1].title)
        }
    }
    @Test
    fun `notes by date des, correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Date(OrderType.Des)).first()

        for(i in 0..notes.size-2){
            assertThat(notes[i].timestamp).isGreaterThan(notes[i+1].timestamp)
        }
    }
    @Test
    fun `notes by color des, correct order`()= runBlocking{
        val notes = getNotes(NoteOrder.Color(OrderType.Des)).first()

        for(i in 0..notes.size-2){
            assertThat(notes[i].color).isGreaterThan(notes[i+1].color)
        }
    }
}