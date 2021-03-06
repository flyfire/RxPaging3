package com.solarexsoft.rxpaging3

import java.text.SimpleDateFormat
import java.util.*

class MoviesMapper {
    fun transform(response: MoviesResponse, locale: Locale): Movies {
        return with(response) {
            Movies(
                total = total,
                page = page,
                movies = results.map {
                    Movies.Movie(
                        0,
                        it.id,
                        it.popularity,
                        it.video,
                        it.posterPath?.let { path -> Movies.Image(path) },
                        it.adult,
                        it.backdropPath?.let { path -> Movies.Image(path) },
                        it.originalLanguage,
                        it.originalTitle,
                        it.title,
                        it.voteAverage,
                        it.overview,
                        it.releaseDate?.let { date ->
                            if (date.isNotEmpty()) {
                                SimpleDateFormat("YYYY-mm-dd", locale).parse(date)
                            } else {
                                null
                            }
                        }
                    )
                }
            )
        }
    }
}