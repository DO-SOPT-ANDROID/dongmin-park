package org.sopt.dosopttemplate

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.model.HumanModel

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HumanModel.FriendModel) =
        with(binding) {
            ivFriendItemProfile.load(friendData.profileImage) {
                error(R.drawable.img_error)
                placeholder(R.drawable.img_placeholder)
            }
            tvFriendItemName.text = friendData.name
            tvFriendItemSelfDecription.text = friendData.aboutMe
            ivFriendItemUseMelon.isVisible = friendData.useMelon
        }
}
