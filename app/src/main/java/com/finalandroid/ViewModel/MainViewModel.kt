package com.finalandroid.ViewModel

import androidx.lifecycle.ViewModel
import com.finalandroid.Memo.DTO.MemoDTO
import com.finalandroid.Repository.MainRepository

class MainViewModel : ViewModel() {
    private lateinit var mainRepository: MainRepository


    fun init(mainRepository: MainRepository) {
        this.mainRepository = mainRepository
    }

    fun getLiveDataMemo(): List<MemoDTO>? {
        return mainRepository.getLiveDataMemo()
    }

    fun insertMemo(memo: MemoDTO) {
        mainRepository.insertMemo(memo)
    }
}