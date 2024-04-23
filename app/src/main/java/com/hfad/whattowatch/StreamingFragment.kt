package com.hfad.whattowatch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.whattowatch.databinding.FragmentStreamingBinding

class StreamingFragment : Fragment() {

    var stream_num = 0
    private var _binding:FragmentStreamingBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: StreamingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle == null) {
            Log.e("StreamingFragment", "StreamingFragment did not receive stream_num")

            return
        }


       stream_num = StreamingFragmentArgs.fromBundle(bundle).streamNum

    }


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



        //Attaching recycleView
        Log.e("RecycleView","RecycleView was created")
        recyclerView = binding.streamingRecycle
        recyclerAdapter = StreamingItemAdapter(requireContext(), Navigation.findNavController(view))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter





        binding.returnToSearchFromStreaming.setOnClickListener {
            it.findNavController().navigate(R.id.action_streamingFragment_to_searchFragment)
        }

        Log.e("Info","Recycle view info passed")
            var currMovie = streamerResults.get(stream_num)
            val service = currMovie.service
            val quality = currMovie.quality
            val link = currMovie.link
            val price = currMovie.price





    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}