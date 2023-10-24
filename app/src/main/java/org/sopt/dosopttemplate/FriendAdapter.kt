package org.sopt.dosopttemplate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.sopt.dosopttemplate.Model.Friend
import org.sopt.dosopttemplate.Model.HumanModel
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.databinding.ItemMyBinding
import org.sopt.dosopttemplate.utilprivate.makeToast

class FriendAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    // 임시의 빈 리스트
    private lateinit var friendList: MutableList<HumanModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when(viewType){
            R.layout.item_my -> {
                val binding = ItemMyBinding.inflate(inflater, parent, false)
               MyViewHolder(binding)
            }
            else -> {
                val binding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(binding)
            }

        }

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val item = friendList[position]
//
//        when (holder) {
//            is FriendViewHolder -> holder.onBind(item as HumanModel.FriendModel)
//            is MyViewHolder -> holder.onBind(item as HumanModel.MyModel)
//        }
//    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = friendList[position]

        when (holder) {
            is FriendViewHolder -> holder.onBind(item as HumanModel.FriendModel)
            is MyViewHolder -> holder.onBind(item as HumanModel.MyModel)
        }
    }

    override fun getItemCount() = friendList.size

    override fun getItemViewType(position: Int) = when (friendList[position]) {
        is HumanModel.MyModel -> R.layout.item_my
        is HumanModel.FriendModel -> R.layout.item_friend
    }

    fun setFriendList(list: MutableList<HumanModel>) {
        if (!::friendList.isInitialized) {
            friendList = mutableListOf()
        }
        friendList.clear()
        friendList = list
        notifyDataSetChanged()
    }
}
