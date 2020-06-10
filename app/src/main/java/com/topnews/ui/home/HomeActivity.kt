package com.topnews.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.topnews.R
import com.topnews.ui.intro.IntroductionActivity
import com.topnews.ui.intro.PrivacyFragment
import com.topnews.utils.viewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeActivityViewModel

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!sharedPrefs.getBoolean(PrivacyFragment.SP_INTRO_NAME, false)) {
            val intent = Intent(this, IntroductionActivity::class.java)
            startActivity(intent)
        } else {
            viewModel = viewModelProvider(viewModelFactory)

            setContentView(R.layout.activity_home)
            val navView: BottomNavigationView = findViewById(R.id.nav_view)

            val navController = findNavController(R.id.nav_host_fragment)

            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_top,
                    R.id.navigation_bookmarks,
                    R.id.navigation_settings
                )
            )
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            toolbar.setupWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

            navView.setOnNavigationItemReselectedListener {
                // do nothing
            }
        }
    }
}



