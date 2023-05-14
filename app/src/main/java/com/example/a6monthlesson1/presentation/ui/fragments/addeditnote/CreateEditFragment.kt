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
import com.example.a6monthlesson1.R
import com.example.a6monthlesson1.databinding.FragmentCreateEditBinding
import com.example.a6monthlesson1.domain.model.Note
import com.example.a6monthlesson1.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class CreateEditFragment : Fragment() {

    private lateinit var binding: FragmentCreateEditBinding
    private val viewModel by viewModels<CreateEditViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()

    }

    private fun initClickers() {
        binding.createEditBtn.setOnClickListener{
                createNote(Note(title = binding.titleEt.text.toString(), desc = binding.descEt.text.toString()))
                //editNote()
        }
    }

    private fun createNote(note: Note) {
        viewModel.createNote(note)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.createNoteState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is UIState.Loading -> {
                            //show progress bar
                        }

                        is UIState.Success -> {
                            //add note to list

                        }
                    }
                }
            }
        }
    }
    private fun editNote(note: Note) {
        viewModel.editNote(note)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.editNoteState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is UIState.Loading -> {
                            //show progress bar
                        }

                        is UIState.Success -> {
                            //edit note
                        }
                    }
                }
            }
        }
    }

}