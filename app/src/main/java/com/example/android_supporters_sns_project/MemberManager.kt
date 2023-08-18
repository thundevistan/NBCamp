package com.example.android_supporters_sns_project

import android.util.Log
import com.example.android_supporters_sns_project.dataclass.Member

object MemberManager {
    private val memberList = mutableListOf<Member>()
    // 멤버 추가
    fun addMember(member: Member) {
        memberList.add(member)
    }
    // memberList에 저장된 데이터 확인을 위한 Logcat 출력
    fun printMembers() {
        for ((index, member) in memberList.withIndex()) {
            Log.d(
                "MemberInfo",
                "Index: $index, Email: ${member.email}, Password: ${member.password}, Name: ${member.name}, Nickname: ${member.nickname}, Picture: ${member.profile}"
            )
        }
    }
    fun getMemberByEmail(email: String): Member? {
        return memberList.find { it.email == email }
    }
    fun getMemberList(): List<Member> {
        return memberList.toList()
    }
    fun updateMember(email: String?, newPassword: String? = null, newNickname: String? = null) {
        val memberToUpdate = memberList.find { it.email == email }

        memberToUpdate?.apply {
            if (newPassword != null) {
                this.password = newPassword
            }
            if (newNickname != null) {
                this.nickname = newNickname
            }
        }
    }

}