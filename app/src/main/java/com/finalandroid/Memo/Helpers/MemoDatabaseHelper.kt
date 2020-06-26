package com.finalandroid.Memo.Helpers

import android.content.Context
import androidx.room.Room
import com.finalandroid.Memo.DAO.MemoDatabase

class MemoDatabaseHelper(context: Context?) {
    companion object {
        private var dbHelper: MemoDatabaseHelper? = null
        private lateinit var db: MemoDatabase

        @Synchronized
        fun getDatabase(c: Context): MemoDatabase {
            if (dbHelper == null)
                dbHelper =
                    MemoDatabaseHelper(
                        c.applicationContext
                    )

            return db
        }
    }

    init {
        db = context?.let {
            Room
                .databaseBuilder(it, MemoDatabase::class.java, "memo.db")
                .allowMainThreadQueries()
                .build()
        }!!
    }
}