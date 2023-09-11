package com.tylerdavidohearn.github.dyaus.app

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.tylerdavidohearn.github.dyaus.data.entities.WeatherData
import com.squareup.picasso.Picasso
import com.tylerdavidohearn.github.dyaus.R
import com.tylerdavidohearn.github.dyaus.utils.FormattingUtil


class MainActivity : AppCompatActivity() {
    private val viewModel: WeatherViewModel by viewModels()

    val zip = "33716"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getWeatherData(zip, { onResponse(it) }, {onFail(it)})
    }

    fun onFail(throwable: Throwable) {
        Toast.makeText(this ,
            R.string.error_message,
            Toast.LENGTH_LONG).show()

    }

    fun onResponse(weatherData: WeatherData){
        Log.e("Repository", weatherData.toString())
        val backgroundColor = findViewById<ConstraintLayout>(R.id.background)
        if (weatherData.current.isDay == 0){
            backgroundColor.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        }
        else {
            backgroundColor.setBackgroundColor(ContextCompat.getColor(this, R.color.skyblue))
            window.statusBarColor = ContextCompat.getColor(this, R.color.skyblue)
        }
        val cityTextView = findViewById<TextView>(R.id.city)
        cityTextView.text = weatherData.location.name
        val stateTextView = findViewById<TextView>(R.id.state)
        stateTextView.text = weatherData.location.region
        val descriptionTextView = findViewById<TextView>(R.id.description)
        descriptionTextView.text = "Current Weather: " + weatherData.current.condition.text
        val temperatureTextView = findViewById<TextView>(R.id.temperature)
        temperatureTextView.text = weatherData.current.tempF.toString() + "\u00B0F"
        val zipTextView = findViewById<TextView>(R.id.zip)
        zipTextView.text = zip
        val icon = findViewById<ImageView>(R.id.weather_icon)
        Picasso.get()
            .load(FormattingUtil.formatIconURL(weatherData))
            .into(icon)
    }
}