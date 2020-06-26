package com.finalandroid.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.finalandroid.R
import com.finalandroid.Activities.DetailActivity
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(),
    View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null && view != null) {
            val argument =
                requireArguments().getString(EXTRA_PARAM)
            textView_fragment.text = argument
        }
    }

    override fun onClick(v: View) {
        val activity = activity
        if (activity is DetailActivity) {
            (activity as DetailActivity?)?.afficheMemo(v)
        }
    }

    companion object {
        const val EXTRA_PARAM = "detail"
    }
}