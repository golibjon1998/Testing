package com.example.testing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.FastModel
import com.example.testing.R
import kotlinx.android.synthetic.main.fast_item.view.*

class FastAdapter(val itemList: MutableList<FastModel>) : RecyclerView.Adapter<FastAdapter.FastViewModel>() {

    class FastViewModel(view: View) : RecyclerView.ViewHolder(view) {
        val title = itemView.title
        val image = itemView.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FastViewModel {
        return FastViewModel(
            LayoutInflater.from(parent.context).inflate(R.layout.fast_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FastViewModel, position: Int) {
        val item = itemList[position]

        holder.title.text = item.title
        holder.image.setImageResource(item.image)

    }

    override fun getItemCount() = itemList.size
}