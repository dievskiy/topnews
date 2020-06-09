package com.topnews.ui.about

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.topnews.R
import com.topnews.databinding.ActivityAboutBinding
import com.topnews.databinding.FragmentSettingsBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_web.*
import java.lang.IllegalArgumentException

class AboutActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // set about views
        val names = resources.getStringArray(R.array.about_names)
        val links = resources.getStringArray(R.array.about_links)
        binding.listView.apply {
            adapter = ArrayAdapter(this@AboutActivity, R.layout.about_item, names)
            onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    try {
                        val link = links[position]
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(link)
                        startActivity(intent)
                    } catch (e: IllegalArgumentException) {
                        e.printStackTrace()
                    }
                }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish(); return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}