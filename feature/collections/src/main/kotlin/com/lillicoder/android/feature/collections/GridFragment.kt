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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lillicoder.android.feature.common.updateSystemBarInsets

/**
 * Activity that shows a grid.
 */
class GridFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.activity_grid, container, false)
        recyclerView = root.findViewById(R.id.grid)
        recyclerView.layoutManager =
            GridLayoutManager(
                root.context,
                3,
                GridLayoutManager.VERTICAL,
                false,
            )
        recyclerView.adapter = GridAdapter(getItems())
        recyclerView.updateSystemBarInsets()

        return root
    }

    /**
     * Gets the list of [Item] this activity's grid displays.
     */
    private fun getItems() =
        listOf(
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_sixteen_by_nine),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_square),
            Item(R.drawable.rect_four_by_three),
            Item(R.drawable.rect_sixteen_by_nine),
        )
}
