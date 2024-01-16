package com.droidcourses.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcourses.newsapp.domain.usecase.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val onboardingUseCase: OnBoardingUseCase): ViewModel() {


    private fun setOnBoardingVisited() {
       viewModelScope.launch {
           onboardingUseCase.setOnBoardingVisited(true)
       }
    }

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.OnBoardingVisited -> setOnBoardingVisited()
        }
    }

}

sealed class OnBoardingEvent {
    object OnBoardingVisited : OnBoardingEvent()
}