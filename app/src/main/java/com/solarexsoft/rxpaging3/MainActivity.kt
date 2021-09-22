package com.solarexsoft.rxpaging3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.solarexsoft.rxpaging3.databinding.ActivityMovieListBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var viewModel: GetMoviesRxViewModel
    private lateinit var adapter: MoviesRxAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, Injection.provideRxViewModel(this)).get(GetMoviesRxViewModel::class.java)
        adapter = MoviesRxAdapter()
        binding.list.layoutManager = GridLayoutManager(this, 2)
        binding.list.adapter = adapter.withLoadStateFooter(
            footer = LoadingGridStateAdapter()
        )
        viewModel.getFavoriteMovies()
            .auto(this)
            .subscribe(
                {
                    adapter.submitData(lifecycle, it)
                },
                {
                    it.printStackTrace()
                }
            )
    }
}