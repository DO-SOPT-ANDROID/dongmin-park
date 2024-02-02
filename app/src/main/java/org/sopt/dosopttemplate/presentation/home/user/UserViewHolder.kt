package org.sopt.dosopttemplate.presentation.home.user

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemUsersBinding
import org.sopt.dosopttemplate.domain.entity.OtherUser

class UserViewHolder(private val binding: ItemUsersBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(userData: OtherUser) =
        with(binding) {
            ivUserItemAvatar.load(userData.avatar) {
                error(R.drawable.img_error)
                placeholder(R.drawable.img_placeholder)
            }
            tvUserItemFirstName.text = userData.first_name
            tvUserItemLastName.text = userData.last_name
            tvUserItemEmail.text = userData.email
        }
}
