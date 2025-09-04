package com.fredcodecrafts.minitodoapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fredcodecrafts.minitodoapp.R
import com.fredcodecrafts.minitodoapp.data.TodoViewModel
import com.fredcodecrafts.minitodoapp.databinding.FragmentListBinding

/**
 * Shows list of Todos using RecyclerView.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoViewModel by activityViewModels()

    // Pass click listener into adapter
    private val adapter = TodoAdapter { todo ->
        viewModel.selectTodo(todo) // ✅ fixed
        findNavController().navigate(R.id.action_list_to_detail)
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
        binding.todoRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.todoRecycler.adapter = adapter

        // Observe LiveData so RecyclerView updates automatically
        viewModel.allTodos.observe(viewLifecycleOwner) { list -> // ✅ fixed
            adapter.submit(list)
        }

        // Navigate to AddFragment when FAB clicked
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_list_to_add)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
