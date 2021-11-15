package com.tests.advoticmovie

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tests.advoticmovie.data.entity.Movie
import com.tests.advoticmovie.data.response.ResultData
import com.tests.advoticmovie.databinding.FragmentHomeBinding
import com.tests.advoticmovie.ui.MovieViewModel
import com.tests.advoticmovie.ui.adapter.MovieAdapter
import com.tests.advoticmovie.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.LinearSnapHelper

import androidx.recyclerview.widget.SnapHelper
import com.tests.advoticmovie.ui.adapter.PopularMovieAdapter
import com.tests.advoticmovie.utils.StartSnapHelper

@AndroidEntryPoint
class HomeFragment : BaseFragment<MovieViewModel, FragmentHomeBinding>() {

    override val layoutRes: Int = R.layout.fragment_home
    override val viewModel: MovieViewModel by viewModels()

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var moviepopulerAdapter: PopularMovieAdapter

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        movieAdapter = MovieAdapter(requireContext())
        binding.popularMovieRecView.setHasFixedSize(true)
        binding.popularMovieRecView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.popularMovieRecView.adapter = movieAdapter

        moviepopulerAdapter = PopularMovieAdapter(requireContext())
        binding.hotMovieRecView.setHasFixedSize(true)
        binding.hotMovieRecView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.hotMovieRecView.adapter = moviepopulerAdapter

        val startSnapHelper: SnapHelper = StartSnapHelper()
        startSnapHelper.attachToRecyclerView(binding.popularMovieRecView)
        startSnapHelper.attachToRecyclerView(binding.hotMovieRecView)

        viewModel.getAllMovies(this.requireContext())
        viewModel.getAllPopularMovies(this.requireContext())
    }

    override fun observeViewModel() {
        viewModel.getMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResultData.Success -> {
                    binding.progressBarPopular.visibility = View.GONE
                    binding.popularMovieRecView.visibility = View.VISIBLE

                    movieAdapter!!.updateList(it.data!!)
                }
                is ResultData.Failed -> {
                    Toast.makeText(
                        this.requireContext(),
                        this.requireContext().getString(R.string.movie_get_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ResultData.Loading -> {
                    binding.progressBarPopular.visibility = View.VISIBLE
                    binding.popularMovieRecView.visibility = View.INVISIBLE
                }
            }
        })

        viewModel.getPopularMovies.observe(viewLifecycleOwner, {
            when (it) {
                is ResultData.Success -> {
                    binding.progressBarHot.visibility = View.GONE
                    binding.hotMovieRecView.visibility = View.VISIBLE

                    moviepopulerAdapter!!.updateList(it.data!!)
                }
                is ResultData.Failed -> {
                    Toast.makeText(
                        this.requireContext(),
                        this.requireContext().getString(R.string.movie_get_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ResultData.Loading -> {
                    binding.progressBarHot.visibility = View.VISIBLE
                    binding.hotMovieRecView.visibility = View.INVISIBLE
                }
            }
        })
    }
}