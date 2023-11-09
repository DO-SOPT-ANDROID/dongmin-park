package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.databinding.ItemBirthdayFriendBinding
import org.sopt.dosopttemplate.model.HumanModel

class FriendBirthdayViewHolder(private val binding: ItemBirthdayFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HumanModel.FriendBirthdayModel) =
        with(binding) {
            ivBirthdayFriendItemProfile.load(friendData.profileImage) {
                error(R.drawable.img_error)
                placeholder(R.drawable.img_placeholder)
            }
            tvName.text = friendData.name
            tvBirthdayFriendItemSelfDecription.text = friendData.aboutMe
        }
}
