package com.example.a6monthlesson1.presentation.ui.fragments.listofnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6monthlesson1.domain.Resource
import com.example.a6monthlesson1.domain.model.Note
import com.example.a6monthlesson1.domain.usecase.GetAllNotesUseCase
import com.example.a6monthlesson1.domain.usecase.RemoveNoteUseCase
import com.example.a6monthlesson1.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfNoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val removeNoteUseCase: RemoveNoteUseCase
) : ViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()
//    val getAllNotesState: StateFlow<UIState<List<Note>>> = _getAllNotesState

    fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesUseCase.getAllNotes().collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _getAllNotesState.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _getAllNotesState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null)
                        _getAllNotesState.value = UIState.Success(res.data)
                    }
                }
            }
        }
    }

}