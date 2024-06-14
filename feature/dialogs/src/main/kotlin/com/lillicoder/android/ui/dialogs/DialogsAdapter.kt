/*
 * Copyright 2024 Scott Weeden-Moody
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

package com.lillicoder.android.feature.dialogs

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lillicoder.android.feature.recycler.BindableViewHolder

class DialogsAdapter : RecyclerView.Adapter<BindableViewHolder<DialogItemUiState>>() {
    private val states: MutableList<DialogItemUiState> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BindableViewHolder<DialogItemUiState> {
        val view = DialogCardView(parent.context)
        return BindableViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BindableViewHolder<DialogItemUiState>,
        position: Int,
    ) {
        val state = states[position]
        holder.bind(state)
    }

    override fun getItemCount() = states.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(updates: List<DialogItemUiState>) {
        states.clear()
        states.addAll(updates)
        notifyDataSetChanged() // TODO Smart updates
    }
}
