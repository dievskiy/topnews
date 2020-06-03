package com.topnews.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.topnews.R

class TopFragment : Fragment() {

    private lateinit var topViewModel: TopViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        topViewModel =
                ViewModelProviders.of(this).get(TopViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_top, container, false)
        return root
    }
}