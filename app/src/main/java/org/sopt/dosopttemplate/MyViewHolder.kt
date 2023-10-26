package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMyBinding
import org.sopt.dosopttemplate.model.HumanModel

class MyViewHolder(private val binding: ItemMyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HumanModel.MyModel) =
        with(binding) {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvSelfDecription.text = friendData.aboutMe
        }
}
