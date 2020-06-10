package com.topnews.ui.intro

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.topnews.R
import com.topnews.data.news.LimitHandler
import com.topnews.data.user.CountryType
import com.topnews.databinding.FragmentPrivacyBinding
import com.topnews.ui.home.HomeActivity
import com.topnews.ui.settings.SettingsFragment.Companion.URL_PRIVACY_POLICY
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class PrivacyFragment : DaggerFragment(), MovingToNext {

    private lateinit var binding: FragmentPrivacyBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var limitDelegate: LimitHandler

    private val viewModel: IntroViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrivacyBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = activity
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener {
            CoroutineScope(IO).launch { viewModel.insertUser() }.invokeOnCompletion {
                saveSP()
                moveToNext()
            }
        }
        binding.textPrivacyLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL_PRIVACY_POLICY)
            startActivity(intent)
        }

    }

    private fun saveSP() {
        limitDelegate.reset()
        sharedPreferences.edit()
            .putBoolean(SP_INTRO_NAME, true)
            .apply()
    }


    override fun moveToNext() {
        val intent = Intent(requireContext(), HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    companion object {
        const val SP_INTRO_NAME = "Intro"
    }

}
