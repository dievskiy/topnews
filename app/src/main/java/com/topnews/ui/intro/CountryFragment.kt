package com.topnews.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.topnews.R
import com.topnews.data.user.CountryType
import com.topnews.databinding.FragmentCountryBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CountryFragment : DaggerFragment(), MovingToNext {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: IntroViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = activity
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = viewModel

        setCountryDropdown()
        binding.next.setOnClickListener { moveToNext() }

    }

    private fun setCountryDropdown() {
        // display values
        val countries = resources.getStringArray(R.array.countries)
        // actual codes
        val tags = CountryType.values()
        val adapter =
            ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, countries)
        binding.dropdownCountry.setAdapter(adapter)
        binding.dropdownCountry.setOnItemClickListener { _, _, position, _ ->
            viewModel.setCountry(tags[position])
        }

    }

    override fun moveToNext() {
        findNavController().navigate(R.id.navigation_categories)
    }


}
