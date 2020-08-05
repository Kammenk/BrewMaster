package com.example.brewmaster.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.brewmaster.R
import com.example.brewmaster.model.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_item.view.*

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val currentItem = differ.currentList[position]

        holder.itemView.result_title.text = currentItem.name
        holder.itemView.result_alk.text = currentItem.abv
        holder.itemView.result_category.text = currentItem.style.name
        if(currentItem.labels !== null) {
            Picasso.get().load(currentItem.labels.contentAwareMedium).fit().centerInside()
                .into(holder.itemView.result_image)
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)
}