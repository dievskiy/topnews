package com.topnews.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.topnews.R
import com.topnews.databinding.FragmentSettingsBinding
import com.topnews.utils.toast
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SettingsViewModel by navGraphViewModels(R.id.mobile_navigation) {
        viewModelFactory
    }

    private lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel

        binding.textPrivacyPolicy.setOnClickListener(privacyListener)
        binding.textClearCache.setOnClickListener(cacheListener)

    }

    private val privacyListener = View.OnClickListener {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = (Uri.parse(URL_PRIVACY_POLICY))
        startActivity(intent)

    }
    private val cacheListener = View.OnClickListener {
        CoroutineScope(Dispatchers.Default).launch {
            Glide.get(requireContext()).clearDiskCache()
            withContext(Dispatchers.Main) {
                requireContext().toast(getString(R.string.cleared))
            }
        }
    }

    companion object {
        const val URL_PRIVACY_POLICY = "https://google.com"
    }

}