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
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lillicoder.android.ui.recycler.DefaultSpacingDecoration
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
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.addItemDecoration(DefaultSpacingDecoration(recyclerView.context))
        recyclerView.adapter = DialogsAdapter()

        fab = root.findViewById(R.id.fab)
        fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_dialogsFragment_to_createDialogFragment)
        }

        val viewModel: DialogsViewModel by viewModels { DialogsViewModelFactory(root.context) }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { bind(it) }
            }
        }

        return root
    }

    /**
     * Gets the adapter for the dialogs list.
     * @return Adapter.
     */
    private fun adapter(): DialogsAdapter = recyclerView.adapter as DialogsAdapter

    /**
     * Updates this fragment to show the state in the given [DialogsViewModel.State].
     * @param state View state.
     */
    private fun bind(state: DialogsViewModel.State) {
        if (state.stateToEdit != null) {
            val bundle = bundleOf("thingToEdit" to state.stateToEdit)
            findNavController().navigate(R.id.action_dialogsFragment_to_editDialogFragment, bundle)
        } else if (state.isLoading) {
            fab.visibility = View.GONE
            recyclerView.visibility = View.GONE
            empty.visibility = View.GONE

            progressBar.visibility = View.VISIBLE
        } else {
            fab.visibility = View.VISIBLE
            progressBar.visibility = View.GONE

            when (state.uiStates.isEmpty()) {
                true -> {
                    empty.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                false -> {
                    adapter().update(state.uiStates)
                    empty.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
        }
    }
}
