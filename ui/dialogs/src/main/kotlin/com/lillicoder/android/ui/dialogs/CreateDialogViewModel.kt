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
import kotlinx.coroutines.launch

class CreateDialogViewModel(
    private val repository: DialogsRepository
) : ViewModel() {

    /**
     * Saves the given [DialogConfig].
     * @param configuration Configuration to save.
     */
    fun saveConfiguration(configuration: DialogConfig) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.save(configuration)
        }
    }
}

class CreateDialogViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CreateDialogViewModel::class.java)) {
            CreateDialogViewModel(DialogsRepository(context)) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
