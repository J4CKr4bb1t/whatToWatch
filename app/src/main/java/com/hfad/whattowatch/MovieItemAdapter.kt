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


var results : ArrayList<Result> = ArrayList()


class MovieItemAdapter(val context: Context, var navController: NavController) :
    RecyclerView.Adapter<MovieItemAdapter.MovieItemViewHolder>() {

    //itemCount used to make sure we display all the data
    override fun getItemCount() = results.size

    fun setSearchListItems(searchData: List<Result>)
    {
        Log.v("SearchListItems","recieved: " + searchData)
        results = searchData as ArrayList<Result>
        notifyDataSetChanged()
        Log.v("SearchListItems","list: " + Result)

    }

    //creates view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return MovieItemViewHolder(view, context, navController)
    }

    //binds data to the view
    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(position)
    }

    //defines the view
    class MovieItemViewHolder (itemView: View, private val context: Context, var navController: NavController)
        : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView!!.findViewById(R.id.movieName)
        private val information: TextView = itemView!!.findViewById(R.id.movieInformation)

        private var pos:Int = 0


        //listener for detail fragment, pass along song info
        init {
            itemView.setOnClickListener {
                Log.v("Navigating", "RecycleView Clicked")
                //Search to detail fragment navigation
                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(pos)
                navController.navigate(action)
            }
        }

        fun bind(position: Int){

            pos = position
            val currMovie = results.get(position)
            title.text = currMovie.title

            //Movie Information
            val type = currMovie.type
            val year = currMovie.year
            val genre = currMovie.genres[0].name
            //val genre = genres.name

            val infoText = type +"~"+ year +"~"+ genre

            information.text =  infoText
        }
    }
}
