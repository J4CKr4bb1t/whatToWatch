package com.hfad.whattowatch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hfad.whattowatch.databinding.FragmentDetailBinding
import com.hfad.whattowatch.API.Result


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
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        val title = currMovie.title
        binding.tvMediaTitle.text = title
        val type = currMovie.type
        val year = currMovie.year
        val genre = currMovie.genres[0].name

        val overview = currMovie.overview
        binding.tvDescription.text = overview

        val infoText = type +"~"+ year +"~"+ genre

        binding.tvMediaInfo.text = infoText

        // Set the image based on the type of media
        val imageResource = when (type.toLowerCase()) {
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