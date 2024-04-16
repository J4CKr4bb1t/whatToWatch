package com.hfad.whattowatch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.hfad.whattowatch.databinding.FragmentStreamingBinding

class StreamingFragment : Fragment() {
    private var _binding:FragmentStreamingBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStreamingBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnToSearchFromStreaming.setOnClickListener {
            it.findNavController().navigate(R.id.action_streamingFragment_to_searchFragment)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}