package com.finalandroid.Activities

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalandroid.Memo.Adapter.MemoAdapter
import com.finalandroid.Memo.DTO.MemoDTO
import com.finalandroid.R
import com.finalandroid.Memo.Helpers.ItemTouchHelperCallback
import com.finalandroid.Repository.MainRepository
import com.finalandroid.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var memoAdapter: MemoAdapter
    private var listMemo: MutableList<MemoDTO>? = ArrayList()
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.init(MainRepository())
        listMemo = mainViewModel.getLiveDataMemo()?.toMutableList()

        memoAdapter = MemoAdapter(listMemo!!, this)

        myrecyclerview.setHasFixedSize(true)
        myrecyclerview.layoutManager = LinearLayoutManager(this)
        itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallback(memoAdapter)
        )
        itemTouchHelper.attachToRecyclerView(myrecyclerview)
        myrecyclerview.adapter = memoAdapter
    }

    fun submitMemo(v: View) {
        if (memo_wrt.text.toString().isNotEmpty()) {
            val n = MemoDTO(memo_wrt!!.text.toString())
            listMemo?.add(n)
            mainViewModel.insertMemo(n)
            myrecyclerview.adapter?.notifyDataSetChanged()
            memo_wrt.text.clear()
        }
    }
}