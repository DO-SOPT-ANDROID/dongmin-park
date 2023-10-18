package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.Model.Friend
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "FragmentHome 바인딩 객체가 생성되지 않았습니다" }

    // 임시 데이터
    private val mockFriendList = listOf<Friend>(
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

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val friendAdapter = FriendAdapter(requireContext())
        binding.rvFriends.adapter = friendAdapter
        friendAdapter.setFriendList(viewModel.mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}