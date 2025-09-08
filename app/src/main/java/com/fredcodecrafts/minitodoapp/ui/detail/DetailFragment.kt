package com.fredcodecrafts.minitodoapp.ui.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fredcodecrafts.minitodoapp.data.Todo
import com.fredcodecrafts.minitodoapp.data.TodoViewModel
import com.fredcodecrafts.minitodoapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TodoViewModel by activityViewModels()

    private var currentTodo: Todo? = null
    private var isEditing = false
    private var originalTitle = ""
    private var originalDescription = ""

    companion object {
        private const val TAG = "DetailFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextWatchers()
        setupButtonListeners()
        observeSelectedTodo()
    }

    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkForChanges()
            }
        }

        binding.etDetailTitle.addTextChangedListener(textWatcher)
        binding.etDetailDesc.addTextChangedListener(textWatcher)
    }

    private fun setupButtonListeners() {
        binding.btnBack.setOnClickListener {
            handleBackPress()
        }

        binding.btnSave.setOnClickListener {
            saveChanges()
        }
    }

    private fun observeSelectedTodo() {
        viewModel.selectedTodo.observe(viewLifecycleOwner) { todo ->
            todo?.let {
                currentTodo = it
                originalTitle = it.title
                originalDescription = it.description

                binding.etDetailTitle.setText(it.title)
                binding.etDetailDesc.setText(it.description)

                isEditing = false
                updateSaveButtonState()
                Log.d(TAG, "Todo loaded: ${it.title}")
            }
        }
    }

    private fun checkForChanges() {
        val currentTitle = binding.etDetailTitle.text.toString()
        val currentDescription = binding.etDetailDesc.text.toString()

        isEditing = currentTitle != originalTitle || currentDescription != originalDescription
        updateSaveButtonState()
    }

    private fun updateSaveButtonState() {
        val currentTitle = binding.etDetailTitle.text.toString()
        binding.btnSave.isEnabled = isEditing && currentTitle.isNotBlank()
    }

    private fun handleBackPress() {
        if (isEditing) {
            showDiscardChangesDialog()
        } else {
            findNavController().navigateUp()
        }
    }

    private fun saveChanges() {
        val newTitle = binding.etDetailTitle.text.toString().trim()
        val newDescription = binding.etDetailDesc.text.toString().trim()

        if (newTitle.isBlank()) {
            binding.etDetailTitle.error = "Judul tidak boleh kosong"
            return
        }

        currentTodo?.let { todo ->
            val updatedTodo = todo.copy(
                title = newTitle,
                description = newDescription,
                updatedAt = System.currentTimeMillis()
            )

            viewModel.update(updatedTodo)
            Log.d(TAG, "Task updated: ${updatedTodo.title}")

            originalTitle = newTitle
            originalDescription = newDescription
            isEditing = false
            updateSaveButtonState()

            showToast("Perubahan disimpan")
            findNavController().navigateUp()
        }
    }

    private fun showDiscardChangesDialog() {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Batal Edit")
            .setMessage("Perubahan yang belum disimpan akan hilang. Yakin ingin batal?")
            .setPositiveButton("Ya") { _, _ ->
                binding.etDetailTitle.setText(originalTitle)
                binding.etDetailDesc.setText(originalDescription)
                isEditing = false
                updateSaveButtonState()
                findNavController().navigateUp()
            }
            .setNegativeButton("Lanjut Edit", null)
            .show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }
}