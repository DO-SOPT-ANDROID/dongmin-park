package org.sopt.dosopttemplate

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.Model.Friend

class HomeViewModel : ViewModel() {
    val mockFriendList = listOf<Friend>(
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "전성기 시절 파트장",
            aboutMe = "꼼짝마 손들어",
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "손흥민",
            aboutMe = "나보다 잘생긴 사람 있으면 나와",
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "파트장",
            aboutMe = "표정 풀자",
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "전성기 시절 파트장",
            aboutMe = "꼼짝마 손들어",
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "손흥민",
            aboutMe = "나보다 잘생긴 사람 있으면 나와",
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "파트장",
            aboutMe = "표정 풀자",
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "전성기 시절 파트장",
            aboutMe = "꼼짝마 손들어",
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "손흥민",
            aboutMe = "나보다 잘생긴 사람 있으면 나와",
        ),
        Friend(
            profileImage = R.drawable.iv_profile,
            name = "파트장",
            aboutMe = "표정 풀자",
        ),
    )
}