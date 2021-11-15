package com.tests.advoticmovie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.entity.PopularMovie
import com.tests.advoticmovie.databinding.ItemMovieSingleBinding
import com.tests.advoticmovie.network.URL_IMG
import java.util.ArrayList

class PopularMovieAdapter(val context: Context) :
    RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {
    private var appList: List<PopularMovie>

    fun updateList(appList: List<PopularMovie>) {
        this.appList = appList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieSingleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.onBind(position)
    }

    inner class ViewHolder(private val binding: ItemMovieSingleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val app = appList[position]
            Glide.with(context!!)
                .load(URL_IMG + app.poster_path)
                .into(binding.imageViewSingleMovie)
            binding.tvTitle.text = appList[position].original_title
            binding.tvDate.text = appList[position].release_date
            binding.rating.text = appList[position].vote_average.toString()
            binding.progressBarMovieDetails.progress = appList[position].vote_average.toInt()
        }
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    init {
        appList = ArrayList()
    }
}