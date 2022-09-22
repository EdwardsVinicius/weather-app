//package com.weather.ui.viewmodel
//
//import androidx.databinding.Bindable
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.weather.ui.model.*
//
//class WeatherViewModel: ViewModel() {
//    val isStringEmpty = MutableLiveData<Boolean>()
//    @Bindable
//    val inputCityName = MutableLiveData<String>()
//    val list = MutableLiveData<ArrayList<ForecastDayArray>>()
//    private val arraList = ArrayList<ForecastDayArray>()
//
//    init {
//        isStringEmpty.value = false
//    }
//
//    fun addData() {
//        val cityName = inputCityName.value!!
//        if(cityName.isBlank()){
//            isStringEmpty.value = true
//        }else{
//            inputCityName.value = " "
//            var weatherModel = WeatherModel(
//                LocationData(cityName),
//                CurrentData(),
//                ForecastData()
//            )
//            arraList.add(noteItem)
//            list.value = arraylst
//        }
//
//    }
//
//    fun clearData(){
//        arraylst.clear()
//        list.value = arraylst
//    }
//
//    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
//    }
//
//    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
//    }
//}