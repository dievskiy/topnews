package com.topnews.ui.intro

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.topnews.R
import com.topnews.data.user.CountryType
import com.topnews.data.user.NewsCategory
import com.topnews.databinding.FragmentCategoriesBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_categories.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

class CategoriesFragment : DaggerFragment(), MovingToNext {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: IntroViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = activity
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = viewModel

        setCategoriesListeners()
        binding.next.setOnClickListener { moveToNext() }
        preChooseCategory()


    }

    private fun preChooseCategory() {
        binding.buttonGeneral.performClick()
    }

    override fun moveToNext() {
        findNavController().navigate(R.id.navigation_privacy)
    }

    private fun addCategory(category: NewsCategory) {
        viewModel.chosenCategories.value?.let {
            viewModel.chosenCategories.value = it.apply {
                add(category)
            }
        }
    }

    private fun removeCategory(category: NewsCategory) {
        viewModel.chosenCategories.value?.let {
            viewModel.chosenCategories.value = it.apply {
                remove(category)
            }
        }
    }

    private fun setCategoriesListeners() {
        binding.buttonTechnology.setOnClickListener {
            val categories = viewModel.chosenCategories.value ?: return@setOnClickListener
            if (categories.contains(NewsCategory.TECHNOLOGY)) {
                removeCategory(NewsCategory.TECHNOLOGY)
                it.setBackgroundColor(disabledColor)
            } else if (categories.size < 2) {
                it.setBackgroundColor(enabledColor)
                addCategory(NewsCategory.TECHNOLOGY)
            }
        }

        binding.buttonBusiness.setOnClickListener {
            val chosen = viewModel.chosenCategories.value ?: return@setOnClickListener
            if (chosen.contains(NewsCategory.BUSINESS)) {
                removeCategory(NewsCategory.BUSINESS)
                it.setBackgroundColor(disabledColor)
            } else if (chosen.size < 2) {
                it.setBackgroundColor(enabledColor)
                addCategory(NewsCategory.BUSINESS)

            }
        }

        binding.buttonEntertaiment.setOnClickListener {
            val chosen = viewModel.chosenCategories.value ?: return@setOnClickListener
            if (chosen.contains(NewsCategory.ENTERTAINMENT)) {
                removeCategory(NewsCategory.ENTERTAINMENT)

                it.setBackgroundColor(disabledColor)
            } else if (chosen.size < 2) {

                it.setBackgroundColor(enabledColor)
                addCategory(NewsCategory.ENTERTAINMENT)
            }
        }

        binding.buttonGeneral.setOnClickListener {
            val chosen = viewModel.chosenCategories.value ?: return@setOnClickListener
            if (chosen.contains(NewsCategory.GENERAL)) {
                it.setBackgroundColor(disabledColor)
                removeCategory(NewsCategory.GENERAL)

            } else if (chosen.size < 2) {

                it.setBackgroundColor(enabledColor)
                addCategory(NewsCategory.GENERAL)
            }
        }

        binding.buttonHealth.setOnClickListener {
            val chosen = viewModel.chosenCategories.value ?: return@setOnClickListener
            if (chosen.contains(NewsCategory.HEALTH)) {
                it.setBackgroundColor(disabledColor)
                removeCategory(NewsCategory.HEALTH)

            } else if (chosen.size < 2) {

                it.setBackgroundColor(enabledColor)
                addCategory(NewsCategory.HEALTH)
            }
        }

        binding.buttonScience.setOnClickListener {
            val chosen = viewModel.chosenCategories.value ?: return@setOnClickListener
            if (chosen.contains(NewsCategory.SCIENCE)) {
                it.setBackgroundColor(disabledColor)
                removeCategory(NewsCategory.SCIENCE)

            } else if (chosen.size < 2) {

                it.setBackgroundColor(enabledColor)
                addCategory(NewsCategory.SCIENCE)
            }
        }
        binding.buttonSports.setOnClickListener {
            val chosen = viewModel.chosenCategories.value ?: return@setOnClickListener
            if (chosen.contains(NewsCategory.SPORTS)) {
                removeCategory(NewsCategory.SPORTS)
                it.setBackgroundColor(disabledColor)
            } else if (chosen.size < 2) {
                it.setBackgroundColor(enabledColor)
                addCategory(NewsCategory.SPORTS)
            }
        }
    }


    private val enabledColor = Color.parseColor("#fff8e1")
    private val disabledColor = Color.parseColor("#484848")

}
