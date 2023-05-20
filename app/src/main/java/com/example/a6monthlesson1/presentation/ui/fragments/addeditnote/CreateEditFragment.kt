package com.example.a6monthlesson1.presentation.ui.fragments.addeditnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.a6monthlesson1.R
import com.example.a6monthlesson1.databinding.FragmentCreateEditBinding
import com.example.a6monthlesson1.domain.model.Note
import com.example.a6monthlesson1.presentation.base.BaseFragment
import com.example.a6monthlesson1.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class CreateEditFragment : BaseFragment() {

    private lateinit var binding: FragmentCreateEditBinding
    private val viewModel by viewModels<CreateEditViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
    }

    override fun initClickers() {
        binding.createEditBtn.setOnClickListener {
            if (arguments?.getInt("id") != null) {
                var note = arguments?.getSerializable("note") as Note
                note.title = binding.titleEt.text.toString()
                note.desc = binding.descEt.text.toString()
                editNote(note = note)
                findNavController().navigateUp()
            } else {
                createNote(
                    Note(
                        title = binding.titleEt.text.toString(),
                        desc = binding.descEt.text.toString()
                    )
                )
            }
        }
    }

    private fun createNote(note: Note) {
        viewModel.createNote(note)
        viewModel.createNoteState.collectState(state = { state ->
            // binding.progressBar.isVisible = state is UIState.Loading
        }, onSuccess = { data ->
            //adapter.addList(data)
        })
    }

    private fun editNote(note: Note) {
        viewModel.editNote(note)
        viewModel.editNoteState.collectState(state = { state ->
            // binding.progressBar.isVisible = state is UIState.Loading
        }, onSuccess = { data ->
            //adapter.addList(data)
        })
    }

}