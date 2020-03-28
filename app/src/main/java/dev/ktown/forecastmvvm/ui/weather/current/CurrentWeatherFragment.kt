package dev.ktown.forecastmvvm.ui.weather.current

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import dev.ktown.forecastmvvm.R
import dev.ktown.forecastmvvm.data.WeatherStackApiService
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)

        val apiService = WeatherStackApiService()
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val currentWeatherResult = apiService.getCurrentWeather("London").await()
                textView.text = currentWeatherResult.toString()
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

}
