package com.example.a6monthlesson1.domain.model

import java.io.Serializable

class Note(
    val id: Int = DEFAULT_ID,
    var title: String? = null,
    var desc: String? = null,
):Serializable {
    companion object {
        const val DEFAULT_ID = 0
    }
}