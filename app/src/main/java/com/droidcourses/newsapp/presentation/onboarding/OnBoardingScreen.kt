package com.droidcourses.newsapp.presentation.onboarding

import android.content.res.Configuration
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
import com.droidcourses.newsapp.R
import com.droidcourses.newsapp.designsystem.mediumSpacing
import com.droidcourses.newsapp.presentation.components.OnBoardingItem
import com.droidcourses.newsapp.presentation.components.PageIndicator
import com.droidcourses.newsapp.presentation.components.PrimaryButton
import com.droidcourses.newsapp.presentation.components.SecondaryButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(onBoardingEvent: (OnBoardingEvent) -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })
    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround){
        HorizontalPager(state = pagerState) { page ->
            OnBoardingItem(
                modifier = Modifier,
                page = pages[page]
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
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = mediumSpacing)
            ,horizontalArrangement = Arrangement.End
        ) {
            val scope = rememberCoroutineScope()
            if (pagerState.currentPage > 0) {
                SecondaryButton(text = stringResource(id = R.string.back)) {
                    scope.launch {
                        pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                    }
                }
            }
            val ctaString = if (pagerState.currentPage == 2)
                stringResource(id = R.string.get_started)
            else
                stringResource(id = R.string.next)
            PrimaryButton(text = ctaString) {
                scope.launch {
                    if (pagerState.currentPage == 2)
                        onBoardingEvent(OnBoardingEvent.OnBoardingVisited)
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
    OnBoardingItem(modifier = Modifier, page = pages[0])
}