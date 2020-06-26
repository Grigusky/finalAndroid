package com.finalandroid.Memo.DAO

import androidx.room.Database
import androidx.room.RoomDatabase
import com.finalandroid.Memo.DTO.MemoDTO

@Database(entities = [MemoDTO::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDAO(): MemoDAO
}