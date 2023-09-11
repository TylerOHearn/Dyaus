package com.tylerdavidohearn.github.dyaus.app

import androidx.lifecycle.ViewModel
import com.tylerdavidohearn.github.dyaus.data.entities.WeatherData
import com.tylerdavidohearn.github.dyaus.networking.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherViewModel: ViewModel() {

    fun getWeatherData(zip: String, onSuccess: (WeatherData) -> Unit, onError: (Throwable) -> Unit = {}) {
        RetrofitClient.getService().getWeatherData(zip).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({weatherdata -> onSuccess(weatherdata) }, { error -> onError(error)})
    }
}