package com.hfad.whattowatch

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.hfad.whattowatch.API.U

var streamerResults: ArrayList<U> = ArrayList()

class StreamingItemAdapter(val context: Context, var navController: NavController) :
    RecyclerView.Adapter<StreamingItemAdapter.StreamingItemViewHolder>() {

    // itemCount used to make sure we display all the data
    override fun getItemCount(): Int = streamerResults.size

    fun setSearchListItems(searchData: List<U>?) {
        if (searchData != null) {
            streamerResults = searchData as ArrayList<U>
            notifyDataSetChanged()
            Log.v("SearchListItems", "list updated: $streamerResults")
        } else {
            // Handle null searchData for no streaming information
            streamerResults.clear()
            notifyDataSetChanged()
            Log.v("SearchListItems", "searchData is null")

            // Show toast that there is no streaming information
            Toast.makeText(context, "No streaming information available", Toast.LENGTH_LONG).show();
        }
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
    class StreamingItemViewHolder(itemView: View, private val context: Context, var navController: NavController) : RecyclerView.ViewHolder(itemView) {
        private val information: TextView = itemView.findViewById(R.id.streamingInformation)
        private val streamingIcon: ImageView = itemView.findViewById(R.id.streamingIcon)
        private var pos: Int = 0

        init {
            itemView.setOnClickListener {
                val currMovie = streamerResults[pos]
                val link = currMovie.link // contains the website URL

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                context.startActivity(intent)
            }
        }

        fun bind(position: Int) {
            pos = position
            val currMovie = streamerResults[position]

            // Streaming Information
            val quality = currMovie.quality
            val service = currMovie.service
            val link = currMovie.link
            var price = currMovie.price

            val type = currMovie.streamingType

            val infoText: String
            if (price != null) {
                // Extract the numeric part of the price
                val priceString = price.formatted // Access the formatted price string
                val indexOfSpace = priceString.indexOf(' ') // Find the first space index
                if (indexOfSpace > 0) {
                    val priceWithoutCurrency = priceString.substring(0, indexOfSpace) // Extract price part
                    infoText = String.format("%s ~ %s ~ %s ~ Click to open ~ %s USD", service, type, quality, priceWithoutCurrency)
                } else {
                    // Handle case where price format is unexpected (use original price)
                    infoText = String.format("%s ~ %s ~ %s ~ Click to open ~ %s", service, type, quality, priceString)
                }
            } else {
                infoText = String.format("%s ~ %s ~ %s ~ Click to open ~ 0.00 USD", service, type, quality)
            }
            information.text = infoText


            // Set the icon based on the service name
            val iconResource = when (service) {
                "prime" -> R.drawable.amazon_icon
                "apple" -> R.drawable.apple_icon
                "paramount" -> R.drawable.paramount_icon
                "disney" -> R.drawable.disney_icon
                "hulu" -> R.drawable.hulu_icon
                "hbo" -> R.drawable.max_icon
                "hbomaxus" -> R.drawable.max_icon
                "max" -> R.drawable.max_icon
                "netflix" -> R.drawable.netflix_icon
                "peacock" -> R.drawable.peacock_icon
                else -> R.drawable.buy_icon // Use buy_icon for other cases or when only available to buy/rent
            }
            streamingIcon.setImageResource(iconResource)
        }
    }
}