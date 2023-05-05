package com.example.a6monthlesson1.domain.usecase

import com.example.a6monthlesson1.domain.repositories.NoteRepository

class GetAllNoteUseCase(
    private val noteRepository: NoteRepository
){
    fun getAllNotes()= noteRepository.getAllNotes()
}