package com.topnews.ui.intro

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.topnews.R
import com.topnews.data.user.NewsCategory
import com.topnews.databinding.FragmentCategoriesBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

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

    inner class CategoryClickListener(private val category: NewsCategory) : View.OnClickListener {
        override fun onClick(v: View?) {
            v ?: return
            val categories = viewModel.chosenCategories.value ?: return
            if (categories.contains(category)) {
                removeCategory(category)
                v.setBackgroundColor(disabledColor)
            } else if (categories.size < 2) {
                v.setBackgroundColor(enabledColor)
                addCategory(category)
            }
        }
    }

    private fun setCategoriesListeners() {
        binding.buttonTechnology.setOnClickListener(CategoryClickListener(NewsCategory.TECHNOLOGY))
        binding.buttonBusiness.setOnClickListener(CategoryClickListener(NewsCategory.BUSINESS))
        binding.buttonEntertaiment.setOnClickListener(CategoryClickListener(NewsCategory.ENTERTAINMENT))
        binding.buttonGeneral.setOnClickListener(CategoryClickListener(NewsCategory.GENERAL))
        binding.buttonHealth.setOnClickListener(CategoryClickListener(NewsCategory.HEALTH))
        binding.buttonScience.setOnClickListener(CategoryClickListener(NewsCategory.SCIENCE))
        binding.buttonSports.setOnClickListener(CategoryClickListener(NewsCategory.SPORTS))
    }


    private val enabledColor = Color.parseColor("#fff8e1")
    private val disabledColor = Color.parseColor("#484848")

}
