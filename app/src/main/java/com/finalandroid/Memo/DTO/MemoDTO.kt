package com.finalandroid.Memo.DTO

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
class MemoDTO (
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,
    var name: String = "") {

    constructor(name: String) : this() {
        this.name = name
    }
}