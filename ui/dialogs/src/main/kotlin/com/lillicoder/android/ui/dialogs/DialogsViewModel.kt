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

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lillicoder.android.domain.dialogs.DialogConfig
import com.lillicoder.android.domain.dialogs.DialogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DialogsViewModel(
    private val repository: DialogsRepository
) : ViewModel() {

    data class State(
        val configurations: List<DialogConfig> = listOf(),
        val isLoading: Boolean = false
    )

    private val viewModelState = MutableStateFlow(State())
    val uiState = viewModelState.asStateFlow()

    init {
        refreshDialogs()
    }

    private fun refreshDialogs() {
        viewModelState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch(Dispatchers.IO) {
            val configurations = repository.configurations()
            viewModelState.update {
                it.copy(configurations = configurations, isLoading = false)
            }
        }
    }
}

class DialogsViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DialogsViewModel::class.java)) {
            DialogsViewModel(DialogsRepository(context)) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
