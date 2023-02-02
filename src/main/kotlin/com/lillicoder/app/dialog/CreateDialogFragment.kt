package com.lillicoder.app.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.lillicoder.app.R

class CreateDialogFragment : Fragment(), CreateDialogContract.View {

    companion object {

        /**
         * Creates a new instance of this fragment.
         * @return New [CreateDialogFragment].
         */
        fun newInstance() = CreateDialogFragment()
    }

    private lateinit var presenter: CreateDialogContract.Presenter

    private lateinit var titleInput: EditText
    private lateinit var messageInput: EditText
    private lateinit var positiveButtonInput: EditText
    private lateinit var neutralButtonInput: EditText
    private lateinit var negativeButtonInput: EditText
    private lateinit var cancelable: CheckBox
    private lateinit var cancelableOnTouchOutside: CheckBox
    private lateinit var linkable: CheckBox
    private lateinit var embed: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = CreateDialogPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_create_dialog, container, false).apply {
            titleInput = findViewById(R.id.title)
            messageInput = findViewById(R.id.message)
            positiveButtonInput = findViewById(R.id.positiveButton)
            neutralButtonInput = findViewById(R.id.neutralButton)
            negativeButtonInput = findViewById(R.id.negativeButton)
            cancelable = findViewById(R.id.cancelable)
            cancelableOnTouchOutside = findViewById(R.id.cancelableOnTouchOutside)
            linkable = findViewById(R.id.linkable)
            embed = findViewById(R.id.embed)
        }
    }

    override fun showConfiguration(builder: AlertDialogFragment.Builder) {
        with(builder) {
            titleInput.setText(title())
            messageInput.setText(message())
            positiveButtonInput.setText(positiveButton())
            neutralButtonInput.setText(neutralButton())
            negativeButtonInput.setText(negativeButton())
            cancelable.isChecked = isCancelable()
            cancelableOnTouchOutside.isChecked = isCancelableOnTouchOutside()
            linkable.isChecked = isLinkable()
            embed.isChecked = shouldEmbed()
        }
    }
}
