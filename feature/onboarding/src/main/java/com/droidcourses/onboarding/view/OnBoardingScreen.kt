package com.droidcourses.onboarding.view

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidcourses.designsystem.mediumSpacing
import com.droidcourses.designsystem.R as designR
import com.droidcourses.uicomponents.OnBoardingItem
import com.droidcourses.uicomponents.PageIndicator
import com.droidcourses.uicomponents.PrimaryButton
import com.droidcourses.uicomponents.SecondaryButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(viewModel: OnBoardingViewModel = hiltViewModel()) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })
    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround){
        HorizontalPager(state = pagerState) { page ->
            OnBoardingItem(
                modifier = Modifier,
                title = pages[page].title,
                description = pages[page].description,
                imageRes = pages[page].image
            )
        }
        Spacer(modifier = Modifier.height(mediumSpacing))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = mediumSpacing)
                .fillMaxWidth()
                .navigationBarsPadding()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
               PageIndicator(pages = pages.size, selectedPage = pagerState.currentPage, modifier = Modifier.width(52.dp) )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumSpacing)
            ,horizontalArrangement = Arrangement.End
        ) {
            val scope = rememberCoroutineScope()
            AnimatedVisibility (pagerState.currentPage > 0) {
                SecondaryButton(text = stringResource(id = designR.string.back)) {
                    scope.launch {
                        pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                    }
                }
            }

            val ctaString = if (pagerState.currentPage == 2)
                stringResource(id = designR.string.get_started)
            else
                stringResource(id = designR.string.next)

            PrimaryButton(
                text = ctaString,
                modifier = Modifier.animateContentSize ( animationSpec = tween(durationMillis = 300,easing = LinearOutSlowInEasing))) {
                scope.launch {
                    if (pagerState.currentPage == 2)
                        viewModel.onEvent(OnBoardingEvent.OnBoardingVisited)
                    else
                        pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingItem(modifier = Modifier, title = "hello", description = "description", imageRes = com.droidcourses.designsystem.R.drawable.ic_home)
}