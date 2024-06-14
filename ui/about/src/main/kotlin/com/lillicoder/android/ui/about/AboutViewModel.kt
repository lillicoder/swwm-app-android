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

package com.lillicoder.android.ui.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lillicoder.android.data.device.BuildInfo
import com.lillicoder.android.data.device.DeviceInformationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * [ViewModel] that can display basic app information.
 */
class AboutViewModel(
    private val repository: com.lillicoder.android.data.device.DeviceInformationRepository,
) : ViewModel() {
    data class State(
        val info: List<com.lillicoder.android.data.device.BuildInfo> = listOf(),
        val isLoading: Boolean = false,
    )

    private val viewModelState = MutableStateFlow(State())
    val uiState = viewModelState.asStateFlow()

    init {
        refreshDeviceInfo()
    }

    private fun refreshDeviceInfo() {
        viewModelState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            val hardware = repository.hardware()
            viewModelState.update {
                it.copy(info = hardware, isLoading = false)
            }
        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AboutViewModel(com.lillicoder.android.data.device.DeviceInformationRepository()) as T
                }
            }
    }
}
