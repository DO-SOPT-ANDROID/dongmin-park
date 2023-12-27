package org.sopt.dosopttemplate.presentation.home.user

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.dosopttemplate.databinding.ItemUsersBinding
import org.sopt.dosopttemplate.domain.entity.OtherUserList
import org.sopt.dosopttemplate.util.ItemDiffCallback

class UserAdapter(context: Context) : ListAdapter<OtherUserList.OtherUser, UserViewHolder>(
    UserDiffCallback,
) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUsersBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    companion object {
        private val UserDiffCallback =
            ItemDiffCallback<OtherUserList.OtherUser>(
                onItemsTheSame = { old, new -> old.email == new.email },
                onContentsTheSame = { old, new -> old == new },
            )
    }
}
