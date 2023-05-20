package com.example.a6monthlesson1.presentation.ui.fragments.listofnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.a6monthlesson1.R
import com.example.a6monthlesson1.databinding.FragmentListOfNoteBinding
import com.example.a6monthlesson1.domain.model.Note
import com.example.a6monthlesson1.presentation.base.BaseFragment
import com.example.a6monthlesson1.presentation.ui.fragments.listofnote.adapter.ListOfNoteAdapter
import com.example.a6monthlesson1.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListOfNoteFragment : BaseFragment() {

    private lateinit var binding: FragmentListOfNoteBinding
    private val viewModel by viewModels<ListOfNoteViewModel>()
    private lateinit var adapter: ListOfNoteAdapter
//    private val viewModel: ListOfNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListOfNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListOfNoteAdapter(this::removeNote, this::editClick)
        binding.noteRv.adapter = adapter
    }

    override fun initClickers() {
        binding.addFab.setOnClickListener {
            findNavController().navigate(R.id.createEditFragment)
        }
    }

    private fun editClick(note: Note, id: Int) {
        findNavController().navigate(
            R.id.createEditFragment, bundleOf(
                "note" to note,
                "id" to id
            )
        )
    }

    override fun loadView() {
        viewModel.getAllNotes()
        viewModel.getAllNoteState.collectState(
            state = { state ->
                binding.progressBar.isVisible = state is UIState.Loading
            },
            onSuccess = { data ->
                //noteAdapter.addList(data)
                adapter.addNotes(data)
            })
    }

    private fun removeNote(note: Note, id: Int) {
        viewModel.removeNote(note)
        viewModel.removeNoteState.collectState(
            state = { state ->
                binding.progressBar.isVisible = state is UIState.Loading
            }, onSuccess = { data ->
                //remove
                loadView()
            })
    }

}