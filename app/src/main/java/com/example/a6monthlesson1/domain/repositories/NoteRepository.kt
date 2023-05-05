package com.example.a6monthlesson1.domain.repositories

import com.example.a6monthlesson1.domain.model.Note

interface NoteRepository {

    fun createNote(noteEntity: Note)

    fun getAllNotes(): List<Note>

    fun editNote(noteEntity: Note)

    fun remoteNote(noteEntity: Note)

}