package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemBirthdayFriendBinding
import org.sopt.dosopttemplate.model.HumanModel

class FriendBirthdayViewHolder(private val binding: ItemBirthdayFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HumanModel.FriendBirthdayModel) {
        binding.ivProfile.setImageResource(friendData.profileImage)
        binding.tvName.text = friendData.name
        binding.tvSelfDecription.text = friendData.aboutMe
    }
}
