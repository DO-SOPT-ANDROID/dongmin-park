package org.sopt.dosopttemplate

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemUsersBinding
import org.sopt.dosopttemplate.model.responseModel.ResponseListUserUserDto

class UserAdapter(context: Context) : RecyclerView.Adapter<UserViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    // 임시의 빈 리스트
    private var userList: List<ResponseListUserUserDto> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUsersBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount() = userList.size

    // 임시 리스트에 준비해둔 가짜 리스트를 연결하는 함수
    fun setUserList(userList: List<ResponseListUserUserDto>) {
        this.userList = userList.toList()
        notifyDataSetChanged()
    }
}