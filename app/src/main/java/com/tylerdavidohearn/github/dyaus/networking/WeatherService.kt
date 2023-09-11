package com.tylerdavidohearn.github.dyaus.networking

import android.R.attr.value
import com.tylerdavidohearn.github.dyaus.data.entities.WeatherData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET ("current.json")
    fun getWeatherData(@Query("q") zipcode: String,
                       @Query("key") apikey: String = "ef34cdf7c9b64f5190f221717231009"): Observable<WeatherData>
}