package bootcamp.sparta.disneym.data.repository

import android.content.Context
import android.util.Log
import bootcamp.sparta.disneym.R
import bootcamp.sparta.disneym.domain.repository.HomeRepository
import bootcamp.sparta.disneym.ui.mypage.UserModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/*
 * 추민수
 * 실질적인 데이터를 관리하는 Repository 구현부
 * 데이터에 대한 처리를 다룸
 */
class MyPageRepositoryImpl : HomeRepository {

    private val USER_SHARED_PREFS_KEY = "user_shared_prefs_key"
    private val USER_SHARED_PREFS_NAME = "user_shared_prefs_name"
    private lateinit var userItem: UserModel

    override fun updateUserId(id: String): UserModel {
        userItem = userItem.copy(
            id = id
        )
        return userItem
    }

    override fun updateUserPw(pw: String): UserModel {
        userItem = userItem.copy(
            password = pw
        )
        return userItem
    }

    override fun updateUserProfile(profile: Int): UserModel {
        userItem = userItem.copy(
            imageUri = profile
        )
        return userItem
    }

    override fun loadUserData(context: Context): UserModel {
        loadUserDataForSharedPrefs(context).let { loadData ->
            Log.d("sharedPreference", "$loadData")
            userItem = loadData
        }
        return userItem
    }

    override fun saveUserData(context: Context, userModel: UserModel) {
        saveUserDataForSharedPrefs(
            context,
            userModel
        )
    }

    private fun saveUserDataForSharedPrefs(context: Context, values: UserModel) {
        val gson = Gson()
        val json = gson.toJson(values)
        val prefs = context.getSharedPreferences(USER_SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs?.edit()
        editor?.putString(USER_SHARED_PREFS_KEY, json)
        editor?.apply()
    }

    private fun loadUserDataForSharedPrefs(context: Context): UserModel {
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
}

