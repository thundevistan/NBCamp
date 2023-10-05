package bootcamp.sparta.disneym.domain.repository

import android.content.Context
import bootcamp.sparta.disneym.ui.mypage.UserModel
/*
 * 추민수
 * Facade 패턴을 사용한 Repository (건물의 앞부분)
 */
interface MyPageRepository {
    fun updateUserId(id: String): UserModel

    fun updateUserPw(pw: String): UserModel

    fun updateUserProfile(profile: Int): UserModel

    fun loadUserData(context: Context): UserModel

    fun saveUserData(context: Context, userModel: UserModel)
}