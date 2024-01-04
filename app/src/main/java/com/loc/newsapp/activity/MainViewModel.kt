package com.loc.newsapp.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.OnBoardingUseCase
import com.loc.newsapp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val onBoardingUseCase: OnBoardingUseCase): ViewModel() {
    private var _startDestination = mutableStateOf(Screen.AppStart.route)
    val startDestination: State<String> = _startDestination

    private var _shouldKeepSplash = mutableStateOf(true)
    val shouldKeepSplash : State<Boolean> = _shouldKeepSplash
    init {
       onBoardingUseCase
           .isOnBoardingVisited()
           .onEach { shouldStartFromHome ->
               if (shouldStartFromHome)
                   _startDestination.value = Screen.NewsNav.route
               else
                   _startDestination.value = Screen.AppStart.route
               delay(200) // to simulate splash, for testing purpose
               _shouldKeepSplash.value = false
           }
           .launchIn(viewModelScope)
    }
}