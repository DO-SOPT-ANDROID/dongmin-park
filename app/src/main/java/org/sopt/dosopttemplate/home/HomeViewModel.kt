package org.sopt.dosopttemplate.home

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.Model.HumanModel
import org.sopt.dosopttemplate.R

class HomeViewModel : ViewModel() {
    val mockFriendList = mutableListOf<HumanModel>(
        HumanModel.MyModel(
            profileImage = R.drawable.iv_profile,
            name = "박동민",
            aboutMe = "나는 동민쓰. 24살인뎀",
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "전성기 시절 파트장",
            aboutMe = "꼼짝마 손들어",
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "손흥민",
            aboutMe = "나보다 잘생긴 사람 있으면 나와",
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "파트장",
            aboutMe = "표정 풀자",
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "전성기 시절 파트장",
            aboutMe = "꼼짝마 손들어",
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "손흥민",
            aboutMe = "나보다 잘생긴 사람 있으면 나와",
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "파트장",
            aboutMe = "표정 풀자",
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "전성기 시절 파트장",
            aboutMe = "꼼짝마 손들어",
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "손흥민",
            aboutMe = "나보다 잘생긴 사람 있으면 나와",
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "파트장",
            aboutMe = "표정 풀자",
        ),
    )
}