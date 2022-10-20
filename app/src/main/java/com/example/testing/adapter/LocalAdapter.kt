package com.example.testing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.R
import com.example.testing.model.LocalModel
import kotlinx.android.synthetic.main.local_item.view.*

class LocalAdapter(var localList: MutableList<LocalModel>) :
    RecyclerView.Adapter<LocalAdapter.LocalViewHolder>() {

    class LocalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = itemView.name
        val distance = itemView.distance
        val image = itemView.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LocalViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.local_item, parent, false)
    )

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        val item = localList[position]

        holder.apply {
            name.text = item.name
            distance.text = item.distance
            image.setImageResource(item.image)
        }

    }

    override fun getItemCount() = localList.size
}