package com.tirexmurina.viewxmlrandomusr.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tirexmurina.viewxmlrandomusr.R
import com.tirexmurina.viewxmlrandomusr.databinding.ItemUserBinding
import com.tirexmurina.viewxmlrandomusr.domain.entity.User

class HomeAdapter(
    private val itemClickListener: (User) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>(){

    var users: List<User> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(users[position], itemClickListener)
    }
}

class HomeViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        user: User,
        itemClickListener: (User) -> Unit
    ){
        var res = itemView.context.resources
        with(binding){
            itemTitle.text = user.name.title
            itemName.text = "${user.name.first} ${user.name.last}"
            itemGender.text = user.gender
            Glide.with(itemPhoto.context)
                .load(user.picture.medium)
                .placeholder(R.drawable.recycler_view_placeholder)
                .error(R.drawable.recycler_view_placeholder)
                .into(itemPhoto)
        }
        itemView.setOnClickListener{ itemClickListener(user)}
    }

}
