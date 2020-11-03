package com.example.nikolaiturev.mvvmroomdatabase.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikolaiturev.mvvmroomdatabase.model.User
import com.example.nikolaiturev.mvvmroomdatabase.databinding.CustomRowBinding

class ListUsersAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(users: List<User>){
        this.userList = users
        notifyDataSetChanged()
    }
}

class MyViewHolder(
    private val binding: CustomRowBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        binding.apply {
            idTxt.text = user.id.toString()
            firstNameTxt.text = user.firstName
            lastNameTxt.text = user.lastName
            ageTxt.text = user.age.toString()
        }
    }
}



