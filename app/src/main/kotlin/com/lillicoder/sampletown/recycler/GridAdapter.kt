package com.lillicoder.sampletown.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lillicoder.sampletown.R

/**
 * [RecyclerView.Adapter] for a grid of arbitrary [Item].
 * @param items Backing items.
 */
class GridAdapter(private val items: List<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val resourceId = items[position].imageResourceId
        holder.itemView.findViewById<ImageView>(R.id.gridItemImage).setImageResource(resourceId)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * View holder for this adapter.
     */
    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}