package com.example.wtmobile.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wtmobile.R
import com.example.wtmobile.data.models.Event
import com.example.wtmobile.data.models.User

//import java.time.format.DateTimeFormatter

class UsersAdapter(
    private val users : MutableList<User>
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addUser(user : User) {
        users.add(user)
    }

    fun deleteUser(index : Int) {
        users.removeAt(index)
        notifyItemRemoved(index)
    }

    fun clear() {
        users.clear();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.itemView.apply {
            findViewById<TextView>(R.id.tvUserName).text = user.name
            findViewById<TextView>(R.id.tvUserEmail).text = user.email                       // TODO: Make this variable a date again! .format(DateTimeFormatter.ofPattern("dd LLLL yyyy"))
            findViewById<TextView>(R.id.tvPassword).text = user.pwdToken
            findViewById<TextView>(R.id.tvUserType).text =  user.userType
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}