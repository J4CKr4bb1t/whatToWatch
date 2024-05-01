package com.hfad.whattowatch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hfad.tasks.TasksViewModelFactory
import com.hfad.whattowatch.databinding.FragmentDetailBinding
import com.hfad.whattowatch.API.Result
import com.hfad.whattowatch.favorites.MediaDatabase
import com.hfad.whattowatch.favorites.MediaViewModel


class DetailFragment : Fragment() {

    var movie_num = 0
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   recipient = arguments!!.getString("recipient")
        val bundle = arguments
        if (bundle == null) {
            Log.e("DetailFragment", "DetailFragment did not receive song_num")

            return
        }
        movie_num = DetailFragmentArgs.fromBundle(bundle).movieNum
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false )
        val view = binding.root

        //for media database, establish viewModel
        val application = requireNotNull(this.activity).application
        val dao = MediaDatabase.getInstance(application).mediaDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(MediaViewModel::class.java)

        binding.viewModel = viewModel
        //done establishing viewModel

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //forDatabase information
        val application = requireNotNull(this.activity).application
        val dao = MediaDatabase.getInstance(application).mediaDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(MediaViewModel::class.java)

        binding.viewModel = viewModel

        binding.whereToWatchButton.setOnClickListener {
            Log.e("stream_Num","passing movie_Num " + movie_num)

            // Pass movie_num as stream_num
            val action = DetailFragmentDirections
                .actionDetailFragmentToStreamingFragment(movie_num)
            it.findNavController().navigate(action)
        }

        binding.returnToSearchButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailFragment_to_searchFragment)
        }

        val currMovie = results.get(movie_num)
        Log.d("streaming","detail movie streaming" + currMovie.streamingInfo)

        val TMDB = currMovie.tmdbId

        val title = currMovie.title
        binding.tvMediaTitle.text = title
        val type = currMovie.type
        val year = currMovie.year
        val genre = currMovie.genres[0].name

        val overview = currMovie.overview
        binding.tvDescription.text = overview

        val infoText = type +"~"+ year +"~"+ genre

        binding.tvMediaInfo.text = infoText

        //pass info to viewModel in case we call addMedia()
        viewModel.newMediaTMDB = TMDB
        viewModel.newMediaTitle = title
        viewModel.newMediaType = type
        viewModel.newMediaYear = year
        viewModel.newMediaGenre = genre
        viewModel.newMediaDesc = overview
        //end pass to database

        // Set the image based on the type of media
        val imageResource = when (type.lowercase()) {
            "movie" -> R.drawable.movie_icon
            "series" -> R.drawable.streaming_icon
            else -> R.drawable.media_icon
        }
        binding.mediaImage.setImageResource(imageResource)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}