package com.kbc.interview.test.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.kbc.interview.test.app.R

class CarViewModel(application: Application) : AndroidViewModel(application) {

    val carModel = MutableLiveData<String>()
    val isCarModelValid = MediatorLiveData<Boolean>()
    val error = MutableLiveData<String?>()

    init {
        isCarModelValid.value = false
        isCarModelValid.addSource(carModel) { validateCarName() }
    }

    fun validateCarName() {
        val isError = carModel.value != null && carModel.value!!.length < 3;
        isCarModelValid.value = !isError
        if (isError) error.value = getApplication<Application>().getString(R.string.car_model_error_too_short) else error.value = null
    }
}