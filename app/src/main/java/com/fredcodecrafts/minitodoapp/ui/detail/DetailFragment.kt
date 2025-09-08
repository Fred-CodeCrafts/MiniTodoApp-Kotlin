package com.fredcodecrafts.minitodoapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
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

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("DetailFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("DetailFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("DetailFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DetailFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("DetailFragment", "onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DetailFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("DetailFragment", "onDetach")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("DetailFragment", "onSaveInstanceState")
    }
}
