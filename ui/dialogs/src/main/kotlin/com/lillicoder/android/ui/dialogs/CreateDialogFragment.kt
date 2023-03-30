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
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.lillicoder.android.domain.dialogs.DialogConfig

class CreateDialogFragment : Fragment() {

    private lateinit var titleInput: EditText
    private lateinit var messageInput: EditText
    private lateinit var positiveButtonInput: EditText
    private lateinit var neutralButtonInput: EditText
    private lateinit var negativeButtonInput: EditText
    private lateinit var cancelable: CheckBox
    private lateinit var cancelableOnTouchOutside: CheckBox
    private lateinit var linkable: CheckBox
    private lateinit var embed: CheckBox
    private lateinit var fab: ExtendedFloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_create_dialog, container, false).apply {
            titleInput = findViewById(R.id.title)
            messageInput = findViewById(R.id.message)
            positiveButtonInput = findViewById(R.id.positiveButton)
            neutralButtonInput = findViewById(R.id.neutralButton)
            negativeButtonInput = findViewById(R.id.negativeButton)
            cancelable = findViewById(R.id.cancelable)
            cancelableOnTouchOutside = findViewById(R.id.cancelableOnTouchOutside)
            linkable = findViewById(R.id.linkable)
            embed = findViewById(R.id.embed)
            fab = findViewById(R.id.fab)

            val scrollView = findViewById<ScrollView>(R.id.scrollView)
            scrollView.viewTreeObserver.addOnScrollChangedListener {
                if (!scrollView.canScrollVertically(-1)) fab.extend() // Top of scroll
                else fab.shrink() // Anywhere else
            }
        }

        val viewModel: CreateDialogViewModel by viewModels {
            CreateDialogViewModelFactory(root.context)
        }
        val navController = findNavController()
        fab.setOnClickListener {
            viewModel.saveConfiguration(configuration())
            navController.navigateUp()
        }

        return root
    }

    /**
     * Gets a [DialogConfig] reflecting this view's current configuration.
     * @return Dialog configuration.
     */
    private fun configuration() = DialogConfig(
        title = titleInput.text,
        message = messageInput.text,
        positiveButtonText = positiveButtonInput.text,
        neutralButtonText = neutralButtonInput.text,
        negativeButtonText = negativeButtonInput.text,
        isCancelable = cancelable.isChecked,
        isCancelableOnTouchOutside = cancelableOnTouchOutside.isChecked,
        isLinkable = linkable.isChecked,
        shouldEmbed = embed.isChecked
    )
}
