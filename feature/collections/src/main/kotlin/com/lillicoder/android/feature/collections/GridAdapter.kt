/*
 * Copyright 2025 Scott Weeden-Moody
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this project except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lillicoder.android.feature.collections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView.Adapter] for a grid of arbitrary [Item].
 * @param items Backing items.
 */
class GridAdapter(private val items: List<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(
                parent.context,
            ).inflate(
                R.layout.grid_item_image,
                parent,
                false,
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val resourceId = items[position].imageResourceId
        holder.itemView.findViewById<ImageView>(R.id.gridItemImage).setImageResource(resourceId)
    }

    override fun getItemCount() = items.size

    /**
     * View holder for this adapter.
     */
    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
