package com.example.demo_app.utils.preferences

import android.content.SharedPreferences
import com.example.demo_app.emptyString
import javax.inject.Inject
import kotlin.collections.HashSet

class PrefManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun put(key: String, value: Any) {
        when (value) {
            is Int -> {
                sharedPreferences.edit().putInt(key, value).apply()
            }
            is Float -> {
                sharedPreferences.edit().putFloat(key, value).apply()
            }
            is Boolean -> {
                sharedPreferences.edit().putBoolean(key, value).apply()
            }
            is String -> {
                sharedPreferences.edit().putString(key, value).apply()
            }

        }

    }


    fun putList(key : String,list : List<String>){
        val set = HashSet<String>()
        set.addAll(list)
        sharedPreferences.edit().putStringSet(key,set).apply()
    }


    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }


    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, emptyString())!!
    }

    fun clear(){
        sharedPreferences.edit().clear().apply()
    }

    fun getList(key: String): MutableSet<String>? = sharedPreferences.getStringSet(key,null)


}

