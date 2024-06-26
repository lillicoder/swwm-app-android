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

package com.lillicoder.android.feature.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lillicoder.android.feature.recycler.DefaultSpacingDecoration
import kotlinx.coroutines.launch

/**
 * [Fragment] that displays a list of app and device information.
 */
class AboutFragment : Fragment() {
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        progressBar = root.findViewById(R.id.progressBar)

        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL,
            ),
        )
        recyclerView.addItemDecoration(DefaultSpacingDecoration(root.context))
        recyclerView.adapter = AboutAdapter()

        val viewModel: AboutViewModel by viewModels { AboutViewModel.factory }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { bind(it) }
            }
        }

        return root
    }

    /**
     * Gets the adapter for the about list.
     * @return View adapter.
     */
    private fun adapter(): AboutAdapter = recyclerView.adapter as AboutAdapter

    /**
     * Binds the fragment to display the given [AboutViewModel.State].
     * @param state View state.
     */
    private fun bind(state: AboutViewModel.State) {
        if (state.isLoading) {
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            adapter().update(state.info)
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }
}
