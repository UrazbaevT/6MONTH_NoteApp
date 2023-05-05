package com.example.a6monthlesson1.domain.usecase

import com.example.a6monthlesson1.domain.model.Note
import com.example.a6monthlesson1.domain.repositories.NoteRepository

class CreateNoteUseCase(
    private val noteRepository: NoteRepository
) {

    fun createNote(note: Note) = noteRepository.createNote(note)

}