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

package com.lillicoder.android.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class DialogsFragment : Fragment() {

    private lateinit var fab: FloatingActionButton
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var empty: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dialogs, container, false)

        empty = root.findViewById(R.id.empty)
        progressBar = root.findViewById(R.id.progressBar)
        recyclerView = root.findViewById(R.id.recyclerView)

        fab = root.findViewById(R.id.fab)
        fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_dialogsFragment_to_createDialogFragment)
        }

        val viewModel: DialogsViewModel by viewModels { DialogsViewModel.factory }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { bind(it) }
            }
        }

        return root
    }

    private fun bind(state: DialogsViewModel.State) {
        if (state.isLoading) {
            fab.visibility = View.GONE
            recyclerView.visibility = View.GONE
            empty.visibility = View.GONE

            progressBar.visibility = View.VISIBLE
        } else {
            fab.visibility = View.VISIBLE
            progressBar.visibility = View.GONE

            when (state.dialogs.isEmpty()) {
                true -> {
                    empty.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                false -> {
                    empty.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
        }
    }
}
