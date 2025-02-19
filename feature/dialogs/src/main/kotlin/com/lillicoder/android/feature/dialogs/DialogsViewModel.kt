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

package com.lillicoder.android.feature.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lillicoder.android.data.dialogs.Dialog
import com.lillicoder.android.data.dialogs.DialogsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * [ViewModel] for [DialogsFragment].
 */
class DialogsViewModel(
    private val repository: DialogsRepository,
) : ViewModel() {
    /**
     * UI state for this view model.
     * @param uiStates List of [DialogItemUiState].
     * @param isLoading Indicates if loading dialogs is ongoing.
     */
    data class State(
        val uiStates: List<DialogItemUiState> = listOf(),
        val isLoading: Boolean = true,
    )

    /**
     * UI state flow for this view model.
     */
    val uiState =
        repository.dialogs.map { dialogs ->
            val states = toUiStates(dialogs)
            State(states, false)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            State(),
        )

    private val navigateToEdit = MutableSharedFlow<Dialog>(replay = 0)

    /**
     * Shared flow for when a navigation request to edit a [Dialog] is fired.
     */
    val edit = navigateToEdit.asSharedFlow()

    private val navigateToDetail = MutableSharedFlow<Dialog>(replay = 0)

    /**
     * Shared flow for when a navigation request to view a [Dialog] is fired.
     */
    val detail = navigateToDetail.asSharedFlow()

    /**
     * Converts the given list of [Dialog] to an equivalent list of [DialogItemUiState].
     * @param dialogs Dialogs to convert.
     * @return Dialog item UI states.
     */
    private fun toUiStates(dialogs: List<Dialog>): List<DialogItemUiState> {
        return dialogs.map { config ->
            DialogItemUiState(
                config,
                onDelete = { viewModelScope.launch { repository.delete(config) } },
                onDetail = { viewModelScope.launch { navigateToDetail.emit(config) } },
                onEdit = { viewModelScope.launch { navigateToEdit.emit(config) } },
            )
        }
    }
}

/**
 * [ViewModelProvider.Factory] for [DialogsViewModel].
 * @param repository [DialogsRepository] for dialog configuration CRUD operations.
 */
class DialogsViewModelFactory(
    private val repository: DialogsRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DialogsViewModel::class.java)) {
            DialogsViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
