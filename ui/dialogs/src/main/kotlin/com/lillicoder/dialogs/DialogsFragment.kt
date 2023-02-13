package com.lillicoder.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DialogsFragment : Fragment(), DialogsContract.View {

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
        fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_dialogsFragment_to_createDialogFragment)
        }

        return root
    }
}
