package org.sopt.dosopttemplate

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.model.HumanModel

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HumanModel.FriendModel) =
        with(binding) {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvSelfDecription.text = friendData.aboutMe
            ivItemFriendUseMelon.isVisible = friendData.useMelon
        }
}
