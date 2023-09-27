package bootcamp.sparta.disneym.util

import android.app.Activity
import android.content.Context
import bootcamp.sparta.disneym.model.BookmarkModel
import com.google.gson.Gson

object Util {
    private const val SHARED_PREFS_KEY = "shared_prefs_key"

    fun saveBookmarkItemForSharedPrefs(context: Context, item: BookmarkModel) {
        val pref = context.getSharedPreferences(SHARED_PREFS_KEY, Activity.MODE_PRIVATE)
        val edit = pref.edit()
        val jsonString = Gson().toJson(item)
        edit.putString(item.imgUrl, jsonString)
        edit.apply()
    }

    fun loadBookmarkItemForSharedPrefs(context: Context): ArrayList<BookmarkModel> {
        val pref = context.getSharedPreferences(SHARED_PREFS_KEY, Activity.MODE_PRIVATE)
        val list = ArrayList<BookmarkModel>()
        val getAllData: Map<String, *> = pref.all
        val gson = Gson()
        getAllData.forEach() { (key, value) ->
            val item = gson.fromJson(value as String, BookmarkModel::class.java)
            list.add(item)
        }
        return list
    }

    fun removeBookmarkItemForSharedPrefs(context: Context, item: BookmarkModel) {
        val pref = context.getSharedPreferences(SHARED_PREFS_KEY, Activity.MODE_PRIVATE)
        val edit = pref.edit()
        edit.remove(item.imgUrl)
        edit.apply()

    }
}