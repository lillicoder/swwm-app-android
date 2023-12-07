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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lillicoder.android.domain.dialogs.DialogConfig
import com.lillicoder.android.domain.dialogs.DialogsRepository
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
    private val repository: DialogsRepository
) : ViewModel() {

    /**
     * UI state for this view model.
     */
    data class State(
        /**
         * List of [DialogItemUiState] for all known dialog configurations .
         */
        val uiStates: List<DialogItemUiState> = listOf(),

        /**
         * Indicates if loading dialog configurations is ongoing.
         */
        val isLoading: Boolean = true
    )

    /**
     * UI state flow for this view model.
     */
    val uiState = repository.configurations.map { configs ->
        val states = toUiStates(configs)
        State(states, false)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        State()
    )

    private val navigateToEdit = MutableSharedFlow<DialogConfig>(replay = 0)

    /**
     * Shared flow for when a navigation request to edit a [DialogConfig] is fired.
     */
    val edit = navigateToEdit.asSharedFlow()

    private val navigateToDetail = MutableSharedFlow<DialogConfig>(replay = 0)

    /**
     * Shared flow for when a navigation request to view a [DialogConfig] is fired.
     */
    val detail = navigateToDetail.asSharedFlow()

    /**
     * Converts the given list of [DialogConfig] to an equivalent list of [DialogItemUiState].
     * @param configs Dialog configs to convert.
     * @return Dialog item UI states for each dialog config.
     */
    private fun toUiStates(configs: List<DialogConfig>): List<DialogItemUiState> {
        return configs.map { config ->
            DialogItemUiState(
                config,
                onDelete = { viewModelScope.launch { repository.delete(config) } },
                onDetail = { viewModelScope.launch { navigateToDetail.emit(config) } },
                onEdit = { viewModelScope.launch { navigateToEdit.emit(config) } }
            )
        }
    }
}

/**
 * [ViewModelProvider.Factory] for [DialogsViewModel].
 * @param repository [DialogsRepository] for dialog configuration CRUD operations.
 */
class DialogsViewModelFactory(
    private val repository: DialogsRepository
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
