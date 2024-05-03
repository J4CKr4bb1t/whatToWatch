package com.hfad.whattowatch

import android.os.Bundle
//import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.whattowatch.API.SearchResult
import com.hfad.whattowatch.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: MovieItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        Log.d("searchFragment", "Fragment Created")
        super.onViewCreated(view, savedInstanceState)

        var search = binding.searchField

        //for recyclerview
        recyclerView = binding.searchMoviesView
        recyclerAdapter = MovieItemAdapter(requireContext(), Navigation.findNavController(view))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        binding.searchButton.setOnClickListener {
//            Log.v("button", "Searched for " + search.text.toString())
            val apiInterface = APIInterface.create().getSearch(search.text.toString(), "us")
            if (apiInterface != null) {
                apiInterface
                    .enqueue(object : Callback<SearchResult?> {
                        override fun onResponse(
                            call: Call<SearchResult?>,
                            response: Response<SearchResult?>
                        ){
//                            Log.v("API Response", "I just responded")

                            if (response?.body() != null) {
                                var movies = (response.body()!! as SearchResult).result;
//                                Log.v("API Response", "movies: " + movies)
                                recyclerAdapter.setSearchListItems(movies)
                            }

                        }
                        override fun onFailure(call: Call<SearchResult?>, t: Throwable) {
                            if (t != null) {
//                                t.message?.let { Log.d("onFailure", it) }

                            }
                        }
                    })

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}

