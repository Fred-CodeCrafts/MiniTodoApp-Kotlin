package com.fredcodecrafts.minitodoapp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fredcodecrafts.minitodoapp.data.TodoViewModel
import com.fredcodecrafts.minitodoapp.databinding.FragmentAddBinding

/**
 * Form to add a new Todo (title + description).
 */
class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text?.toString().orEmpty().trim()
            val desc  = binding.etDesc.text?.toString().orEmpty().trim()

            if (title.isBlank()) {
                Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.addTodo(title, desc)
            findNavController().navigateUp() // go back to list
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
