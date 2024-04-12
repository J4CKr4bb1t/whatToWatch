package com.hfad.whattowatch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class HomeFragment : Fragment() {
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
}

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)
    val searchButton = view.findViewById<Button>(R.id.button_search)
    val favButton = view.findViewById<Button>(R.id.button_favorites)
    searchButton.setOnClickListener {
        view.findNavController()
            .navigate(R.id.action_homeFragment_to_searchFragment)

    }
    favButton.setOnClickListener {
        view.findNavController()
            .navigate(R.id.action_homeFragment_to_favoritesFragment)
    }

    return view
}}



/**
 *
 *  **avoid using databinding until after everything is working.
 *  * It wouldn't take long to change the code to implement data binding**
import com.hfad.whattowatch.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSearch.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }
}
**/

