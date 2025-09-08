package com.fredcodecrafts.minitodoapp.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fredcodecrafts.minitodoapp.R
import com.fredcodecrafts.minitodoapp.data.Todo
import com.fredcodecrafts.minitodoapp.data.TodoViewModel
import com.fredcodecrafts.minitodoapp.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoViewModel by activityViewModels()

    companion object {
        private const val TAG = "ListFragment"
    }

    private val adapter = TodoAdapter(
        onClick = { todo ->
            viewModel.selectTodo(todo)
            findNavController().navigate(R.id.action_list_to_detail)
        },
        onDelete = { todo ->
            showDeleteConfirmation(todo) // panggil fungsi
        }
    )

    private fun showDeleteConfirmation(todo: Todo) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Todo")
            .setMessage("Are you sure you want to delete \"${todo.title}\"?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.delete(todo)
                Log.d(TAG, "Task deleted: ${todo.title} - ${todo.description}")
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.todoRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.todoRecycler.adapter = adapter

        viewModel.allTodos.observe(viewLifecycleOwner) { list ->
            adapter.submit(list)
        }

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_list_to_add)
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
    }
}
