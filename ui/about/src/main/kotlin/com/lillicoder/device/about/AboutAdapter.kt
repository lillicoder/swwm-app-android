/*
 * Copyright 2023 Scott Weeden-Moody
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

package com.lillicoder.device.about

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lillicoder.device.BuildInfo
import com.lillicoder.recycler.BindableViewHolder

/**
 * [RecyclerView.Adapter] for displaying basic about information.
 */
class AboutAdapter : RecyclerView.Adapter<BindableViewHolder<BuildInfo>>() {

    private val information: MutableList<BuildInfo> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindableViewHolder<BuildInfo> {
        val view = AboutListItemView(parent.context)
        return BindableViewHolder(view)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<BuildInfo>, position: Int) {
        val info = information[position]
        holder.bind(info)
    }

    override fun getItemCount(): Int {
        return information.size
    }

    /**
     * Updates this adapter's list of [BuildInfo].
     * @param updates Hardware info to show.
     */
    fun update(updates: List<BuildInfo>) {
        information.clear()
        information.addAll(updates)
        notifyItemRangeInserted(0, information.size)
    }
}
