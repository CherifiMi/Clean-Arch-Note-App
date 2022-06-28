package com.example.cleanarchnoteapp.feature_note.presentasion.note_add_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchnoteapp.feature_note.domain.model.Note
import com.example.cleanarchnoteapp.feature_note.domain.use_case.NoteUseCases
import com.example.cleanarchnoteapp.feature_note.domain.util.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter title..."
    ))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteDec = mutableStateOf(NoteTextFieldState(
        hint = "Enter words..."
    ))
    val noteDec: State<NoteTextFieldState> = _noteDec

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId : Int? = null


    init {
        savedStateHandle.get<Int>("noteId")?.let {id->
            if(id != -1){
                viewModelScope.launch {
                    noteUseCases.getNote(id)?.also {
                        currentNoteId = it.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = it.title,
                            isHintVisible = false
                        )
                        _noteDec.value = noteDec.value.copy(
                            text = it.content,
                            isHintVisible = false
                        )
                        _noteColor.value = it.color
                    }
                }
            }else{
                currentNoteId = null
            }
        }
    }


    fun onEvent(event: AddEditNoteEvents){
        when(event){
            is AddEditNoteEvents.EnteredTitle -> {
                 _noteTitle.value = noteTitle.value.copy(
                     text = event.value
                 )
            }
            is AddEditNoteEvents.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvents.EnteredDec -> {
                _noteDec.value = noteDec.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvents.ChangeDecFocus -> {
                _noteDec.value = noteDec.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            noteDec.value.text.isBlank()
                )
            }
            is AddEditNoteEvents.ChangeColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvents.SaveNote -> {
                if (
                    noteTitle.value.text.isNotEmpty()
                    &&noteDec.value.text.isNotEmpty()
                ){
                    viewModelScope.launch {
                        try {
                            noteUseCases.insertNote(
                                Note(
                                    title = noteTitle.value.text,
                                    content = noteDec.value.text,
                                    timestamp = System.currentTimeMillis(),
                                    color = noteColor.value,
                                    id = currentNoteId
                                )
                            )
                            _eventFlow.emit(UiEvent.SaveNote)
                        }catch (e: Exception){
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = e.message ?: "couldn't do it boss man"
                                )
                            )
                        }
                    }
                }else{
                    UiEvent.ShowSnackbar(
                        message = "Fill the note"
                    )
                }
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }


}