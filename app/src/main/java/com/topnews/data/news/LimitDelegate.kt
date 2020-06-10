package com.topnews.data.news

import android.content.SharedPreferences
import javax.inject.Inject


internal class LimitDelegate @Inject constructor(private val sp: SharedPreferences) : LimitHandler {
    override fun save() {
        sp.edit().putLong(SP_NAME_TAG_UPDATE, System.currentTimeMillis()).apply()
    }

    override fun reset() {
        sp.edit().remove(SP_NAME_TAG_UPDATE).apply()
    }

    override fun get(): Long =
        sp.getLong(SP_NAME_TAG_UPDATE, 0)


    companion object {
        const val SP_NAME_TAG_UPDATE = "lastUpdate"
    }
}

interface LimitHandler {
    fun save()
    fun reset()
    fun get(): Long
}