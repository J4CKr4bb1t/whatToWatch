package com.hfad.whattowatch

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.hfad.whattowatch.API.Result
import com.hfad.whattowatch.API.U

var streamerResults: ArrayList<U> = ArrayList()

class StreamingItemAdapter(val context: Context, var navController: NavController) :
    RecyclerView.Adapter<StreamingItemAdapter.StreamingItemViewHolder>() {

    // itemCount used to make sure we display all the data
    override fun getItemCount(): Int = streamerResults.size

    fun setSearchListItems(searchData: List<U>) {
        streamerResults = searchData as ArrayList<U>
       // streamerResults.clear()
        //streamerResults.addAll(searchData)
        notifyDataSetChanged()
        Log.v("SearchListItems", "list updated: $streamerResults")
    }

    // creates view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreamingItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_streaming, parent, false)
        return StreamingItemViewHolder(view, context, navController)
    }

    // binds data to the view
    override fun onBindViewHolder(holder: StreamingItemViewHolder, position: Int) {
        holder.bind(position)
    }

    // defines the view
    class StreamingItemViewHolder(itemView: View, private val context: Context, var navController: NavController)
        : RecyclerView.ViewHolder(itemView) {


        private val information: TextView = itemView.findViewById(R.id.streamingInformation)
        private var pos: Int = 0

        // listener for streaming fragment, pass along streaming info
        init {
            itemView.setOnClickListener {
                Log.v("Navigating", "RecyclerView Clicked")
                // detail to streaming fragment navigation
                val action = DetailFragmentDirections.actionDetailFragmentToStreamingFragment(pos)
                navController.navigate(action)
            }
        }

        fun bind(position: Int) {
            pos = position
            val currMovie = streamerResults[position]

            // Streaming Information
            val quality = currMovie.quality
            val service = currMovie.service
            val link = currMovie.link
            val price = currMovie.price

            val infoText = "$quality ~ $service ~ $link ~ $price"
            information.text = infoText
        }
    }
}
