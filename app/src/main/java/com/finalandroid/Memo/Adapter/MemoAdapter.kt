package com.finalandroid.Memo.Adapter

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.finalandroid.R
import com.finalandroid.Activities.DetailActivity
import com.finalandroid.Fragment.DetailFragment
import com.finalandroid.Memo.DTO.MemoDTO
import com.finalandroid.Memo.Helpers.MemoDatabaseHelper
import kotlinx.android.synthetic.main.item_memo.view.*

class MemoAdapter(listeMemo: MutableList<MemoDTO>, main: AppCompatActivity) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder?>() {
    private var listeMemo: MutableList<MemoDTO> = ArrayList()
    private val main: AppCompatActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val viewMemo: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
        return MemoViewHolder(viewMemo)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(listeMemo[position])
    }

    override fun getItemCount(): Int {
        return listeMemo.size
    }

    fun onItemDismiss(view: RecyclerView.ViewHolder) {
        if (view.adapterPosition > -1) {
            MemoDatabaseHelper.getDatabase(view.itemView.context).memoDAO().delete(listeMemo[view.adapterPosition])
            listeMemo.removeAt(view.adapterPosition)
            notifyItemRemoved(view.adapterPosition)
        }
    }

    inner class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { view ->
                val memo: MemoDTO = listeMemo[adapterPosition]
                val preferences: SharedPreferences =
                    androidx.preference.PreferenceManager.getDefaultSharedPreferences(view.context)
                val editor = preferences.edit()
                editor.putInt("last", adapterPosition)
                editor.apply()

                if (main.findViewById<View?>(R.id.detail) == null) {
                    val intent = Intent(view.context, DetailActivity::class.java)
                    intent.putExtra("detail", memo.name)
                    view.context.startActivity(intent)
                } else {
                    // fragment :
                    val fragment = DetailFragment()
                    val bundle = Bundle()
                    bundle.putString(DetailFragment.EXTRA_PARAM, memo.name)
                    fragment.arguments = bundle

                    // fragment manager :
                    val fragmentManager =
                        main.supportFragmentManager
                    // transaction :
                    val fragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.detail, fragment, "frag_replace")
                    fragmentTransaction.commit()
                }
            }
        }

        fun bind(memo:MemoDTO) = with(itemView){
            titlememo.text = memo.name
        }
    }

    init {
        this.listeMemo = listeMemo
        this.main = main
    }
}