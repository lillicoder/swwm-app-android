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
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lillicoder.android.domain.dialogs.DialogConfig
import com.lillicoder.android.domain.dialogs.DialogsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateDialogViewModel(
    private val repository: DialogsRepository,
    state: SavedStateHandle
) : ViewModel() {

    data class State(
        val dialogConfig: DialogConfig? = null
    )

    private val viewModelState = MutableStateFlow(State())
    val uiState = viewModelState.asStateFlow()

    init {
        val edit = state.get<DialogConfig>("thingToEdit")
        viewModelState.update { it.copy(dialogConfig = edit) }
    }

    /**
     * Updates and saves this view model's [DialogConfig] with the given values.
     * @param title Title.
     * @param message Message.
     * @param positiveButtonText Positive button text.
     * @param neutralButtonText Neutral button text.
     * @param negativeButtonText Negative button text.
     * @param isCancelable True if dialog can be canceled.
     * @param isCancelableOnTouchOutside True if dialog can be canceled by touch events.
     * @param isLinkable True if dialog has linked content (e.g. URLs).
     * @param shouldEmbed True if dialog should be embedded in a layout.
     */
    fun saveConfiguration(
        title: String,
        message: String,
        positiveButtonText: String,
        neutralButtonText: String,
        negativeButtonText: String,
        isCancelable: Boolean,
        isCancelableOnTouchOutside: Boolean,
        isLinkable: Boolean,
        shouldEmbed: Boolean
    ) {
        viewModelScope.launch {
            val configuration = DialogConfig(
                viewModelState.value.dialogConfig?.id ?: 0,
                0,
                0,
                title,
                message,
                positiveButtonText,
                neutralButtonText,
                negativeButtonText,
                isCancelable,
                isCancelableOnTouchOutside,
                isLinkable,
                shouldEmbed
            )
            repository.save(configuration)
        }
    }
}

class CreateDialogViewModelFactory(
    private val context: Context
) : AbstractSavedStateViewModelFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return if (modelClass.isAssignableFrom(CreateDialogViewModel::class.java)) {
            CreateDialogViewModel(DialogsRepository(context), handle) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
