package com.finalandroid.Memo.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.finalandroid.Memo.DTO.MemoDTO

@Dao
abstract class MemoDAO {
    @Query("SELECT * FROM memo")
    abstract fun getlisteMemo(): MutableList<MemoDTO>

    @Query("SELECT COUNT(*) FROM memo WHERE name = :name")
    abstract fun countMemoByName(name: String?): Long

    @Insert
    abstract fun insert(vararg notes: MemoDTO?)

    @Update
    abstract fun update(vararg notes: MemoDTO?)

    @Delete
    abstract fun delete(vararg notes: MemoDTO?)
}
