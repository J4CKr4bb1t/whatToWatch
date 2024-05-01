package com.hfad.whattowatch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.tasks.TasksViewModelFactory
import com.hfad.whattowatch.databinding.FragmentFavoritesBinding
import com.hfad.whattowatch.favorites.MediaDatabase
import com.hfad.whattowatch.favorites.MediaViewModel

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: FavItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //for recyclerview
        recyclerView = binding.favoritesRecycle
        recyclerAdapter = FavItemAdapter(requireContext(), Navigation.findNavController(view))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        //forDatabase information
        val application = requireNotNull(this.activity).application
        val dao = MediaDatabase.getInstance(application).mediaDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(MediaViewModel::class.java)

        binding.viewModel = viewModel
        //end database establishing

        //set recycleAdapter with all the movies tagged favorite
        Log.d("Database", dao.getAll().toString())
        Log.d("Database", dao.getAll().value?.get(0).toString())
        //passes List<Media> to recycleView
        recyclerAdapter.setSearchListItems(dao.getAll().value)


        //return to home
        binding.returnToHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_favoritesFragment_to_homeFragment)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}