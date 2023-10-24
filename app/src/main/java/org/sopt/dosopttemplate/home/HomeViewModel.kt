package org.sopt.dosopttemplate.home

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.model.HumanModel
import org.sopt.dosopttemplate.R

class HomeViewModel : ViewModel() {
    val mockFriendList = mutableListOf<HumanModel>(
        HumanModel.MyModel(
            profileImage = R.drawable.iv_profile,
            name = "박동민",
            aboutMe = "나는 동민쓰. 24살인뎀",
        ),
        HumanModel.FriendBirthdayModel(
            profileImage = R.drawable.iv_profile,
            name = "박강희",
            aboutMe = "곧 생일이다. 좋게 말할때 선물내놔",
            useMelon = true
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "이삭",
            aboutMe = "에그드랍 미만 잡",
            useMelon = true
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "경지현",
            aboutMe = "큐티 프리티 (본인입으로 함)",
            useMelon = true
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "조세연",
            aboutMe = "솔직히 숙대에서 내가 제일 코딩 잘한다 ㅋㅋ",
            useMelon = false
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "이태희",
            aboutMe = "저 사실 아이폰으로 바꿨습니다.",
            useMelon = true
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "강유리",
            aboutMe = "하나 둘 셋 첫차유리!",
            useMelon = false
        ),
        HumanModel.FriendBirthdayModel(
            profileImage = R.drawable.iv_profile,
            name = "조관희",
            aboutMe = "생일선물은 프로틴으로 받겠습니다",
            useMelon = false
        ),
        HumanModel.FriendModel(
            profileImage = R.drawable.iv_profile,
            name = "몽실이",
            aboutMe = "꿈결티미단 화이팅",
            useMelon = false
        ),
    )
}