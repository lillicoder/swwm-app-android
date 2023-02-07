package com.lillicoder.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DialogsFragment : Fragment(), DialogsContract.View {

    companion object {

        /**
         * Creates a new instance of this fragment.
         * @return New [DialogsFragment].
         */
        fun newInstance() = DialogsFragment()
    }

    private lateinit var fab: FloatingActionButton
    private lateinit var presenter: DialogsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DialogsPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.view_dialogs, container, false)

        fab = root.findViewById(R.id.fab)
        fab.setOnClickListener { presenter.newDialog() }

        return root
    }

    override fun show(fragment: Fragment, tag: String) {
        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right_short, R.anim.fade_out_short)
            .add(R.id.fragmentContainer, fragment, tag)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}
