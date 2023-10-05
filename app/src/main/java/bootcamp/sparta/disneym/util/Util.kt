package bootcamp.sparta.disneym.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.model.BookmarkModel
import bootcamp.sparta.disneym.ui.bookmark.BookmarkViewType
import bootcamp.sparta.disneym.ui.mypage.UserModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Util {
    private const val SHARED_PREFS_KEY = "shared_prefs_key"
    private const val USER_SHARED_PREFS_KEY ="user_shared_prefs_key"
    private const val USER_SHARED_PREFS_NAME ="user_shared_prefs_name"


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

    fun saveUserDataForSharedPrefs(context: Context, values: UserModel) {
        val gson = Gson()
        val json = gson.toJson(values)
        val prefs = context.getSharedPreferences(USER_SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs?.edit()
        editor?.putString(USER_SHARED_PREFS_KEY, json)
        editor?.apply()
    }

    fun loadUserDataForSharedPrefs(context: Context): UserModel {
        val prefs = context.getSharedPreferences(USER_SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs?.getString(USER_SHARED_PREFS_KEY, null)
        return if (json != null) {
            val gson = Gson()
            val storedData: UserModel =
                gson.fromJson(json, object : TypeToken<UserModel>() {}.type)
            storedData
        } else {
            return UserModel(
                R.drawable.profile2,
                "disneym@gmail.com",
                "123456789"
            )
        }
    }

    fun shareUrl(context: Context, videoId : String){

        val url = idToUrl(videoId)

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

    private const val URL = "https://www.youtube.com/watch?v=%s"
    private fun idToUrl(id : String): String = "https://www.youtube.com/watch?v=$id"

}