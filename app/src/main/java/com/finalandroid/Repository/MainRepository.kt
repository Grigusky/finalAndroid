package com.finalandroid.Repository

import com.finalandroid.DI.DIApplication
import com.finalandroid.Memo.DAO.MemoDAO
import com.finalandroid.Memo.DTO.MemoDTO
import javax.inject.Inject

class MainRepository {
    @Inject lateinit var memoDAO: MemoDAO

    fun getLiveDataMemo(): List<MemoDTO> {
        return memoDAO.getlisteMemo()
    }

    fun insertMemo(memo: MemoDTO) {
        memoDAO.insert(memo)
    }

    fun deleteNote(memo: MemoDTO) {
        memoDAO.delete(memo)
    }

    init {
        DIApplication.getAppComponent()?.inject(this)
    }
}