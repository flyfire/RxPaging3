package com.solarexsoft.rxpaging3

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import java.util.*

object Injection {
    private fun provideLocale(): Locale = Locale.getDefault()

    fun provideRxViewModel(context: Context): ViewModelProvider.Factory {
        val pagingSource =
            GetMoviesRxPagingSource(
                service = TMDBService.create(),
                apiKey = context.getString(R.string.api_key),
                mapper = MoviesMapper(),
                locale = provideLocale()
            )

        val repository =
            GetMoviesRxRepositoryImpl(
                pagingSource = pagingSource
            )

        return GetMoviesRxViewModelFactory(
            repository
        )
    }

}