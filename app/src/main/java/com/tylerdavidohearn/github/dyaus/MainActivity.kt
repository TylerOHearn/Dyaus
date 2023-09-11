package com.tylerdavidohearn.github.dyaus

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.tylerdavidohearn.github.dyaus.data.entities.WeatherData
import com.tylerdavidohearn.github.dyaus.networking.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.squareup.picasso.Picasso
import com.tylerdavidohearn.github.dyaus.R
import com.tylerdavidohearn.github.dyaus.utils.FormattingUtil


class MainActivity : AppCompatActivity() {

    val zip = "33716"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWeatherData(zip)
    }

    fun getWeatherData(zip: String) {
        RetrofitClient.getService().getWeatherData(zip).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({weatherdata -> onResponse(weatherdata) }, { error -> })
    }

    fun onResponse(weatherData: WeatherData){
        Log.e("Repository", weatherData.toString())
        val backgroundColor = findViewById<ConstraintLayout>(R.id.background)
        if (weatherData.current.isDay == 0){backgroundColor.setBackgroundColor(ContextCompat.getColor(this,R.color.black))}
        else {backgroundColor.setBackgroundColor(ContextCompat.getColor(this, R.color.skyblue))}
        val cityTextView = findViewById<TextView>(R.id.city)
        cityTextView.text = weatherData.location.name
        val stateTextView = findViewById<TextView>(R.id.state)
        stateTextView.text = weatherData.location.region
        val descriptionTextView = findViewById<TextView>(R.id.description)
        descriptionTextView.text = "Current Weather: " + weatherData.current.condition.text
        val temperatureTextView = findViewById<TextView>(R.id.temperature)
        temperatureTextView.text = weatherData.current.tempF.toString() + "F"
        val zipTextView = findViewById<TextView>(R.id.zip)
        zipTextView.text = zip
        val icon = findViewById<ImageView>(R.id.weather_icon)
        Picasso.get()
            .load(FormattingUtil.formatIconURL(weatherData))
            .into(icon)
    }
}