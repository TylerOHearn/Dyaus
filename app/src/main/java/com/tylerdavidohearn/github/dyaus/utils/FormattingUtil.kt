package com.tylerdavidohearn.github.dyaus.utils

import com.tylerdavidohearn.github.dyaus.data.entities.WeatherData

object FormattingUtil {
    @JvmStatic
    fun formatIconURL(weatherData: WeatherData) : String {
        return "https://" + weatherData.current.condition.icon.replace("64" , "128")
    }
}