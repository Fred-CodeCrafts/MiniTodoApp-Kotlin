package com.fredcodecrafts.minitodoapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fredcodecrafts.minitodoapp.data.TodoViewModel
import com.fredcodecrafts.minitodoapp.databinding.FragmentDetailBinding

/**
 * Shows selected Todo details. Data pulled from shared ViewModel.
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedTodo.observe(viewLifecycleOwner) { todo ->
            todo?.let {
                binding.tvDetailTitle.text = it.title
                binding.tvDetailDesc.text = it.description
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
