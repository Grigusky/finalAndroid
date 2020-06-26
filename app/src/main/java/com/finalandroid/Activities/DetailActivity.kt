package com.finalandroid.Activities

import android.os.Build
import com.finalandroid.R
import com.finalandroid.Fragment.DetailFragment
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_detail.view.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.title = "Read"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nameMemo = intent.getStringExtra("detail") // get extra to collecct memo detail
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putString(DetailFragment.EXTRA_PARAM, nameMemo) // put memo on bundle
        fragment.arguments = bundle // collect arg with frag

        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.detail, fragment, "frag_replace")
        fragmentTransaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun afficheMemo(v: View) {
        Toast.makeText(this, v.textView_fragment.text, Toast.LENGTH_SHORT).show()
    }
}