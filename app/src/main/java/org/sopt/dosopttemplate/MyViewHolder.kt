package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.model.HumanModel
import org.sopt.dosopttemplate.databinding.ItemMyBinding

class MyViewHolder (private val binding: ItemMyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: HumanModel.MyModel) {
        binding.ivProfile.setImageResource(friendData.profileImage)
        binding.tvName.text = friendData.name
        binding.tvSelfDecription.text = friendData.aboutMe
    }
}