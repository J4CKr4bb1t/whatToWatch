package com.hfad.whattowatch

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.hfad.whattowatch.favorites.Media


var favorites : List<Media>? = ArrayList<Media>()

class FavItemAdapter(val context: Context, var navController: NavController) :
    RecyclerView.Adapter<FavItemAdapter.FavItemViewHolder>() {

    //itemCount used to make sure we display all the data
    //returns 0 if favorites is null (which it might be if database is empty)
    override fun getItemCount() = favorites?.size ?: 0

    fun setSearchListItems(favData: List<Media>?)
    {
        Log.v("Database Checked", "Loaded into recycle")
        favorites = favData
        notifyDataSetChanged()

    }

    //creates view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite,parent,false)
        return FavItemViewHolder(view, context, navController)
    }


    //binds data to the view
    override fun onBindViewHolder(holder: FavItemAdapter.FavItemViewHolder, position: Int) {
        holder.bind(position)
    }

    //defines the view
    class FavItemViewHolder (itemView: View, private val context: Context, var navController: NavController)
        : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView!!.findViewById(R.id.movieNameFav)
        private val information: TextView = itemView!!.findViewById(R.id.movieInformationFav)

        private var pos:Int = 0

        //listener for detail fragment, pass along song info
        init {
            itemView.setOnClickListener {
                Log.v("Favorite", "RecycleView Clicked")
                //Search to detail fragment navigation
                //val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(pos)
                //navController.navigate(action)
            }
        }

        fun bind(position: Int){

            pos = position
            val currMovie = favorites?.get(position)
            Log.d("CURR MOVIE", currMovie.toString())

            if (currMovie != null) {
                title.text = currMovie.mediaTitle

                //Movie Information
                val type = currMovie.mediaType
                val year = currMovie.mediaYear
                val showYear = currMovie.mediaYear
                val genre = currMovie.mediaGenre

                val infoText: String
                if (type == "series") {
                    infoText = type + ", " + showYear + ", " + genre
                } else {
                    infoText = type + ", " + year + ", " + genre
                }
                information.text = infoText
            }
        }
    }
}
