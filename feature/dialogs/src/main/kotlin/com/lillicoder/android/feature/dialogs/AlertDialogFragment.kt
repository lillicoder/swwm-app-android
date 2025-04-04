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

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.parcelize.Parcelize

private const val ARGUMENT_BUILDER = "argument_builder"

/**
 * [DialogFragment] analog to the [AlertDialog] class.
 */
class AlertDialogFragment : DialogFragment() {
    companion object {
        /**
         * Creates a new instance of [AlertDialogFragment] with the given [Builder].
         * @param builder Builder.
         */
        fun newInstance(builder: Builder): AlertDialogFragment {
            val dialog = AlertDialogFragment()

            val arguments = Bundle()
            arguments.putParcelable(ARGUMENT_BUILDER, builder)
            dialog.arguments = arguments

            return dialog
        }
    }

    @Parcelize
    enum class Event : Parcelable {
        /**
         * Event fired when the dialog's positive button is clicked.
         */
        POSITIVE_BUTTON_CLICK,

        /**
         * Event fired when the dialog's negative button is clicked.
         */
        NEGATIVE_BUTTON_CLICK,

        /**
         * Event fired when the dialog's neutral button is clicked.
         */
        NEUTRAL_BUTTON_CLICK,

        /**
         * Event fired when the dialog is cancelled.
         */
        CANCEL,

        /**
         * Event fired when the dialog is dismissed.
         */
        DISMISS,
    }

    private lateinit var icon: ImageView
    private lateinit var title: TextView
    private lateinit var message: TextView
    private lateinit var positiveButton: Button
    private lateinit var neutralButton: Button
    private lateinit var negativeButton: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: Builder? = getBuilder()
        val alertDialogBuilder: AlertDialog.Builder =
            createAlertDialogBuilder(
                requireActivity(),
                builder,
            )

        // Cancel on touch outside flag can only be set on the dialog itself, if we have a builder set this value
        val dialog = alertDialogBuilder.create()
        if (builder != null) {
            dialog.setCanceledOnTouchOutside(builder.isCancelableOnTouchOutside())
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root =
            inflater.inflate(
                R.layout.fragment_alert_dialog,
                container,
                false,
            ).apply {
                icon = findViewById(R.id.icon)
                title = findViewById(R.id.alertTitle)
                message = findViewById(R.id.message)

                positiveButton = findViewById(R.id.button1)
                positiveButton.setOnClickListener { setEventAsResult(Event.POSITIVE_BUTTON_CLICK) }

                neutralButton = findViewById(R.id.button3)
                neutralButton.setOnClickListener { setEventAsResult(Event.NEUTRAL_BUTTON_CLICK) }

                negativeButton = findViewById(R.id.button2)
                negativeButton.setOnClickListener { setEventAsResult(Event.NEGATIVE_BUTTON_CLICK) }
            }

        return root
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        setEventAsResult(Event.CANCEL)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        setEventAsResult(Event.DISMISS)
    }

    override fun onStart() {
        super.onStart()

        // Enable links on the message view (if present); this can only be done here
        // as doing this will end up making a requestFeature() call that will crash the app
        // if the dialog isn't ready yet
        val builder = getBuilder()
        if (builder != null && builder.isLinkable()) {
            val view: View = dialog?.findViewById(android.R.id.message) ?: message
            if (view is TextView) {
                view.movementMethod = LinkMovementMethod.getInstance()
            }
        }

        if (dialog == null) {
            // Embedded, set config now
            builder?.apply {
                icon.setImageResource(icon())
                title.text = title()
                message.text = message()

                when (val text = positiveButton()) {
                    null -> positiveButton.visibility = View.INVISIBLE
                    else -> {
                        positiveButton.text = text
                        positiveButton.visibility = View.VISIBLE
                    }
                }

                when (val text = neutralButton()) {
                    null -> neutralButton.visibility = View.INVISIBLE
                    else -> {
                        neutralButton.text = text
                        neutralButton.visibility = View.VISIBLE
                    }
                }

                when (val text = negativeButton()) {
                    null -> negativeButton.visibility = View.INVISIBLE
                    else -> {
                        negativeButton.text = text
                        negativeButton.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    /**
     * Gets the [Builder] for this dialog from its arguments [Bundle].
     * @return Builder or null if there was no builder in the arguments bundle.
     */
    private fun getBuilder(): Builder? {
        return arguments?.getParcelable(ARGUMENT_BUILDER)
    }

    /**
     * Creates a fully prepared [AlertDialog.Builder] instance configured from the given [Builder].
     * @param context [Context] for the alert dialog builder.
     * @param builder Builder to configure the alert dialog with.
     * @return Alert dialog builder.
     */
    @SuppressLint("ResourceType")
    private fun createAlertDialogBuilder(
        context: Context,
        builder: Builder?,
    ): MaterialAlertDialogBuilder {
        val alertDialogBuilder = MaterialAlertDialogBuilder(context)
        if (builder != null) {
            isCancelable = builder.isCancelable()
            alertDialogBuilder
                .setIcon(builder.icon())
                .setTitle(builder.title())

            // Configure body content; either a custom layout or a basic message
            if (builder.view() > 0) {
                val body = layoutInflater.inflate(builder.view(), null)
                alertDialogBuilder.setView(body)
            } else {
                alertDialogBuilder.setMessage(builder.message())
            }

            alertDialogBuilder
                .setPositiveButton(builder.positiveButton()) { _, _ ->
                    setEventAsResult(Event.POSITIVE_BUTTON_CLICK)
                }
                .setNegativeButton(builder.negativeButton()) { _, _ ->
                    setEventAsResult(Event.NEGATIVE_BUTTON_CLICK)
                }
                .setNeutralButton(builder.neutralButton()) { _, _ ->
                    setEventAsResult(Event.NEUTRAL_BUTTON_CLICK)
                }
        }
        return alertDialogBuilder
    }

    /**
     * Sets the fragment result for this fragment to the given [Event].
     * @param event Event to set.
     */
    private fun setEventAsResult(event: Event) {
        val tag = tag ?: ""
        setFragmentResult(tag, Bundle().apply { putSerializable("event", event) })
    }

    /**
     * Builder that can create instances of [AlertDialogFragment].
     */
    @Suppress("unused")
    @Parcelize
    class Builder(
        @DrawableRes private var iconId: Int = 0,
        @LayoutRes private var layoutId: Int = 0,
        private var title: CharSequence? = null,
        private var message: CharSequence? = null,
        private var positiveButtonText: CharSequence? = null,
        private var negativeButtonText: CharSequence? = null,
        private var neutralButtonText: CharSequence? = null,
        private var isCancelable: Boolean = true,
        private var isCancelableOnTouchOutside: Boolean = true,
        private var isLinkable: Boolean = false,
        private var shouldEmbed: Boolean = false,
    ) : Parcelable {
        /**
         * Creates an [AlertDialogFragment] from this builder's configuration.
         * @return Configured dialog.
         */
        fun create() = newInstance(this)

        /**
         * Gets the dialog is cancelable flag for this builder.
         * @return True if the flag is active, false otherwise.
         */
        fun isCancelable() = isCancelable

        /**
         * Sets whether the dialog is cancelable or not (defaults to true).
         * @param isCancelable True to enable cancel, false to disable cancel.
         * @return This builder instance.
         */
        fun isCancelable(isCancelable: Boolean) = apply { this.isCancelable = isCancelable }

        /**
         * Gets the dialog is cancelable on touch outside flag for this builder.
         * @return True if the flag is active, false otherwise.
         */
        fun isCancelableOnTouchOutside() = isCancelableOnTouchOutside

        /**
         * Sets whether the dialog is cancelable on touch events
         * outside its view bounds (defaults to true).
         * @param isCancelable True to enable cancel on touch outside, false to disable.
         * @return This builder instance.
         */
        fun isCancelableOnTouchOutside(isCancelable: Boolean) = apply { this.isCancelableOnTouchOutside = isCancelable }

        /**
         * Gets the link-ify flag for this builder.
         * @return True if the flag is active, false otherwise.
         */
        fun isLinkable() = isLinkable

        /**
         * Sets whether the dialog's message will have link elements.
         * @param isLinkable True to link-ify the message text, false otherwise.
         * @return This builder instance.
         */
        fun isLinkable(isLinkable: Boolean) = apply { this.isLinkable = isLinkable }

        /**
         * Gets the dialog icon resource ID for this builder.
         * @return Icon resource ID.
         */
        @DrawableRes
        fun icon() = iconId

        /**
         * Sets the resource ID of the icon to be used in the dialog title.
         * @return This builder instance.
         */
        fun icon(
            @DrawableRes id: Int,
        ) = apply { this.iconId = id }

        /**
         * Gets the dialog message for this builder.
         * @return Message.
         */
        fun message() = message

        /**
         * Sets the message of the dialog generated by this builder using the given resource ID.
         * @param id Resource ID of the message to set.
         * @return This builder instance.
         */
        fun message(
            context: Context,
            @StringRes id: Int,
        ) = apply { this.message = context.getText(id) }

        /**
         * Sets the message of the dialog generated by this builder.
         * @param message Message to set.
         * @return This builder instance.
         */
        fun message(message: CharSequence?) = apply { this.message = message }

        /**
         * Gets the dialog positive button text for this builder.
         * @return Positive button text.
         */
        fun positiveButton() = positiveButtonText

        /**
         * Sets the text for the positive button of the dialog generated
         * by this builder using the given resource ID.
         * @param context [Context] to get text with.
         * @param id Resource ID of the text to set.
         * @return This builder instance.
         */
        fun positiveButton(
            context: Context,
            @StringRes id: Int,
        ) = apply { this.positiveButtonText = context.getText(id) }

        /**
         * Sets the text for the positive button of the dialog generated by this builder.
         * @param text Text to set.
         * @return This builder instance.
         */
        fun positiveButton(text: CharSequence?) = apply { this.positiveButtonText = text }

        /**
         * Gets the dialog negative button text for this builder.
         * @return Negative button text.
         */
        fun negativeButton() = negativeButtonText

        /**
         * Sets the text for the negative button of the dialog generated
         * by this builder using the given resource ID.
         * @param context [Context] to get text with.
         * @param id Resource ID of the text to set.
         * @return This builder instance.
         */
        fun negativeButton(
            context: Context,
            @StringRes id: Int,
        ) = apply { this.negativeButtonText = context.getText(id) }

        /**
         * Sets the text for the negative button of the dialog generated by this builder.
         * @param text Text to set.
         * @return This builder instance.
         */
        fun negativeButton(text: CharSequence?) = apply { this.negativeButtonText = text }

        /**
         * Gets the dialog neutral button text for this builder.
         * @return Neutral button text.
         */
        fun neutralButton() = neutralButtonText

        /**
         * Sets the text for the neutral button of the dialog generated
         * by this builder using the given resource ID.
         * @param context [Context] to get text with.
         * @param id Resource ID of the text to set.
         * @return This builder instance.
         */
        fun neutralButton(
            context: Context,
            @StringRes id: Int,
        ) = apply { this.neutralButtonText = context.getText(id) }

        /**
         * Sets the text for the neutral button of the dialog generated by this builder.
         * @param text Text to set.
         * @return This builder instance.
         */
        fun neutralButton(text: CharSequence?) = apply { this.neutralButtonText = text }

        /**
         * Determines if this dialog's content should be embedded in a layout.
         * @return True if dialog content should be embedded, false otherwise.
         */
        fun shouldEmbed() = shouldEmbed

        /**
         * Sets if the dialog content should be embedded in a layout.
         * @param shouldEmbed True to embed, false to show as a dialog.
         * @return This builder instance.
         */
        fun shouldEmbed(shouldEmbed: Boolean) = apply { this.shouldEmbed = shouldEmbed }

        /**
         * Gets the dialog title for this builder.
         * @return Title.
         */
        fun title() = title

        /**
         * Sets the title of the dialog generated by this builder using the given resource ID.
         * @param context [Context] to get text with.
         * @param id Resource ID of the title to set.
         * @return This builder instance.
         */
        fun title(
            context: Context,
            @StringRes id: Int,
        ) = apply { this.title = context.getText(id) }

        /**
         * Sets the title of the dialog generated by this builder.
         * @param title Title to set.
         * @return This builder instance.
         */
        fun title(title: CharSequence?) = apply { this.title = title }

        /**
         * Gets the dialog view layout ID for this builder.
         * @return Layout ID.
         */
        @LayoutRes
        fun view() = layoutId

        fun view(
            @LayoutRes id: Int,
        ) = apply { this.layoutId = id }
    }
}
