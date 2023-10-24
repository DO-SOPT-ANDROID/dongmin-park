package org.sopt.dosopttemplate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.sopt.dosopttemplate.databinding.ItemBirthdayFriendBinding
import org.sopt.dosopttemplate.model.HumanModel
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMyBinding

class HumanAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    private lateinit var humanList: MutableList<HumanModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            R.layout.item_my -> {
                val binding = ItemMyBinding.inflate(inflater, parent, false)
                MyViewHolder(binding)
            }

            R.layout.item_friend -> {
                val binding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(binding)
            }

            else -> {
                val binding = ItemBirthdayFriendBinding.inflate(inflater, parent, false)
                FriendBirthdayViewHolder(binding)
            }

        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = humanList[position]

        when (holder) {
            is MyViewHolder -> holder.onBind(item as HumanModel.MyModel)
            is FriendViewHolder -> holder.onBind(item as HumanModel.FriendModel)
            is FriendBirthdayViewHolder -> holder.onBind(item as HumanModel.FriendBirthdayModel)
        }
    }

    override fun getItemCount() = humanList.size

    override fun getItemViewType(position: Int) =
        when (humanList[position]) {
            is HumanModel.MyModel -> R.layout.item_my
            is HumanModel.FriendModel -> R.layout.item_friend
            is HumanModel.FriendBirthdayModel -> R.layout.item_birthday_friend
        }

    fun setHumanList(list: MutableList<HumanModel>) {
        if (!::humanList.isInitialized) {
            humanList = mutableListOf()
        }

        humanList.clear()
        humanList = list

        notifyDataSetChanged()
    }
}
