package com.example.brewmaster.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.brewmaster.R
import com.example.brewmaster.model.brewerymodel.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.beer_row_item.view.*
import kotlinx.android.synthetic.main.beer_row_item.view.result_image
import kotlinx.android.synthetic.main.beer_row_item.view.result_title
import kotlinx.android.synthetic.main.brewery_row_item.view.*

class BreweryAdapterAdapter: RecyclerView.Adapter<BreweryAdapterAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryAdapterAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.brewery_row_item,parent,false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BreweryAdapterAdapter.MyViewHolder, position: Int) {

        val currentItem = differ.currentList[position]

        holder.itemView.result_title.text = currentItem.name
        holder.itemView.brewWebsite.text = currentItem.website
        if(currentItem.images !== null) {
            Picasso.get().load(currentItem.images.squareMedium).fit().centerInside()
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