package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.Model.HumanModel
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HumanModel.FriendModel) {
        binding.ivProfile.setImageResource(friendData.profileImage)
        binding.tvName.text = friendData.name
        binding.tvSelfDecription.text = friendData.aboutMe
    }
}
