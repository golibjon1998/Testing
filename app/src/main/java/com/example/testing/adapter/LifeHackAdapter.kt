package com.example.testing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.R
import com.example.testing.model.LifeModel
import kotlinx.android.synthetic.main.life_hack_item.view.*

class LifeHackAdapter(var lifeHackList: MutableList<LifeModel>) :
    RecyclerView.Adapter<LifeHackAdapter.LifeHackViewHolder>() {

    class LifeHackViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val background = itemView.backgroundTint
        val image = itemView.image
        val name = itemView.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LifeHackViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.life_hack_item, parent, false)
    )

    override fun onBindViewHolder(holder: LifeHackViewHolder, position: Int) {
        val item = lifeHackList[position]

        holder.apply {
            background.setBackgroundColor(ContextCompat.getColor(itemView.context, item.back))
            image.setImageResource(item.image)
            name.text = item.name
        }
    }

    override fun getItemCount() = lifeHackList.size

}