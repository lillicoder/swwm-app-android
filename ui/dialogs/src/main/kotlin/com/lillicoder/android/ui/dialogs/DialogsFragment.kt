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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
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
import com.lillicoder.android.domain.dialogs.DialogConfig
import com.lillicoder.android.domain.dialogs.DialogsRepository
import com.lillicoder.android.ui.recycler.DefaultSpacingDecoration
import kotlinx.coroutines.launch

class DialogsFragment : Fragment() {

    private lateinit var fab: FloatingActionButton
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var empty: TextView

    private val showDialogTag = "showDialog"

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
            val action = DialogsFragmentDirections.actionDialogsToCreate()
            it.findNavController().navigate(action)
        }

        val viewModel: DialogsViewModel by viewModels {
            DialogsViewModelFactory(DialogsRepository(root.context.applicationContext))
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { bind(it) }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.edit.collect { edit(it) }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detail.collect { detail(it) }
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
        when (state.isLoading) {
            true -> showProgressBar()
            else -> when (state.uiStates.isEmpty()) {
                true -> showEmpty()
                else -> showDialogs(state.uiStates)
            }
        }
    }

    /**
     * Navigates to the detail view for the given [DialogConfig].
     * @param config Config to show.
     */
    private fun detail(config: DialogConfig) {
        config.apply {
            val dialog = AlertDialogFragment.Builder()
                .title(title)
                .message(message)
                .positiveButton(positiveButtonText)
                .negativeButton(negativeButtonText)
                .neutralButton(neutralButtonText)
                .isCancelable(isCancelable)
                .isCancelableOnTouchOutside(isCancelableOnTouchOutside)
                .isLinkable(isLinkable)
                .create()
            dialog.show(parentFragmentManager, showDialogTag)
            setFragmentResultListener(showDialogTag) { _, bundle ->
                val event: AlertDialogFragment.Event? = bundle.getParcelable("event")
                Toast.makeText(context, "$event received.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Navigates to the edit view for the given [DialogConfig].
     * @param config Config to edit.
     */
    private fun edit(config: DialogConfig) {
        val action = DialogsFragmentDirections.actionDialogsToCreate(config)
        findNavController().navigate(action)
    }

    /**
     * Shows the dialogs list for this view.
     */
    private fun showDialogs(states: List<DialogItemUiState>) {
        adapter().update(states)
        progressBar.visibility = View.GONE
        empty.visibility = View.GONE
        fab.visibility = View.VISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    /**
     * Shows the empty state for this view.
     */
    private fun showEmpty() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE
        fab.visibility = View.VISIBLE
        empty.visibility = View.VISIBLE
    }

    /**
     * Shows the progress bar for this view.
     */
    private fun showProgressBar() {
        fab.visibility = View.GONE
        recyclerView.visibility = View.GONE
        empty.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }
}
