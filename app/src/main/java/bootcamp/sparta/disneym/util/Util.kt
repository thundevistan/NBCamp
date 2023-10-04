package bootcamp.sparta.disneym.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import bootcamp.sparta.disneym.model.BookmarkModel
import bootcamp.sparta.disneym.ui.bookmark.BookmarkViewType
import com.google.gson.Gson

object Util {
    private const val SHARED_PREFS_KEY = "shared_prefs_key"

    fun saveBookmarkItemForSharedPrefs(context: Context, item: BookmarkModel) {
        val pref = context.getSharedPreferences(SHARED_PREFS_KEY, Activity.MODE_PRIVATE)
        val edit = pref.edit()
        val jsonString = Gson().toJson(
            item.copy(
                viewType = BookmarkViewType.Normal.INT
        ))
        edit.putString(item.imgUrl.toString(), jsonString)
        edit.apply()
    }

    fun loadBookmarkItemForSharedPrefs(context: Context): ArrayList<BookmarkModel> {
        val pref = context.getSharedPreferences(SHARED_PREFS_KEY, Activity.MODE_PRIVATE)
        val list = ArrayList<BookmarkModel>()
        val getAllData: Map<String, *> = pref.all
        val gson = Gson()
        getAllData.forEach() { (_, value) ->
            val item = gson.fromJson(value as String, BookmarkModel::class.java)
            list.add(item)
        }
        return list
    }

    fun removeBookmarkItemForSharedPrefs(context: Context, item: BookmarkModel) {
        val pref = context.getSharedPreferences(SHARED_PREFS_KEY, Activity.MODE_PRIVATE)
        val edit = pref.edit()
        edit.remove(item.imgUrl.toString())
        edit.apply()

    }

    fun shareUrl(context: Context, url : String){
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            // (Optional) Here we're setting the title of the content
            putExtra(Intent.EXTRA_TITLE, "YOUTUBE URL")
            // (Optional) Here we're passing a content URI to an image to be displayed
            data = Uri.parse(url)
            type = "text/plain"
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }, null)
        context.startActivity(share)
    }

}