package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemBirthdayFriendBinding
import org.sopt.dosopttemplate.model.HumanModel

class FriendBirthdayViewHolder(private val binding: ItemBirthdayFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HumanModel.FriendBirthdayModel) =
        with(binding) {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvSelfDecription.text = friendData.aboutMe
        }
}
