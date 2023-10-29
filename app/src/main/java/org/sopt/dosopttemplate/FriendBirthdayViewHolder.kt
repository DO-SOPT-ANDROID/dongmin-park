package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.databinding.ItemBirthdayFriendBinding
import org.sopt.dosopttemplate.model.HumanModel

class FriendBirthdayViewHolder(private val binding: ItemBirthdayFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HumanModel.FriendBirthdayModel) =
        with(binding) {
            ivProfile.load(friendData.profileImage) {
                error(R.drawable.img_error)
                placeholder(R.drawable.img_placeholder)
            }
            tvName.text = friendData.name
            tvSelfDecription.text = friendData.aboutMe
        }
}
