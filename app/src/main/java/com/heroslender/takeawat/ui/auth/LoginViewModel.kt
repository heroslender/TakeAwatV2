package com.heroslender.takeawat.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heroslender.takeawat.utils.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _failure = MutableLiveData<Failure>()
    val failure: LiveData<Failure>
        get() = _failure

    fun login(user: String, password: String): Boolean {
        _failure.postValue(Failure("Invalid credentials"))
        return false
    }
}