package com.tylerdavidohearn.github.dyaus.data.entities


import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val current: Current
)