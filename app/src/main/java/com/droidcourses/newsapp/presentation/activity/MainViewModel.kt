package com.droidcourses.newsapp.presentation.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcourses.onboarding.usecase.OnBoardingUseCase
import com.droidcourses.newsapp.presentation.home.HOME_ROUTE
import com.droidcourses.newsapp.presentation.onboarding.ONBOARDING_ROUTE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val onBoardingUseCase: OnBoardingUseCase): ViewModel() {
    private var _startDestination = mutableStateOf(ONBOARDING_ROUTE)
    val startDestination: State<String> = _startDestination

    private var _shouldKeepSplash = mutableStateOf(true)
    val shouldKeepSplash : State<Boolean> = _shouldKeepSplash
    init {
       onBoardingUseCase
           .isOnBoardingVisited()
           .onEach { shouldStartFromHome ->
               if (shouldStartFromHome)
                   _startDestination.value = HOME_ROUTE
               else
                   _startDestination.value = ONBOARDING_ROUTE
               delay(200) // to simulate splash, for testing purpose
               _shouldKeepSplash.value = false
           }
           .launchIn(viewModelScope)
    }
}