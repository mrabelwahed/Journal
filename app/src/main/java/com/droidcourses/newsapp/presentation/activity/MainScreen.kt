package com.droidcourses.newsapp.presentation.activity

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.droidcourses.newsapp.presentation.bookmark.BOOKMARK_SCREEN
import com.droidcourses.newsapp.presentation.home.HOME_ROUTE
import com.droidcourses.newsapp.presentation.navigation.NavGraph
import com.droidcourses.newsapp.presentation.search.SEARCH_SCREEN


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(startDestination: String, navController: NavHostController) {
    Scaffold(bottomBar = { BottomNavigationView(navController) }) {
        NavGraph(startDestination = startDestination, navController)
    }
}

@Composable
fun BottomNavigationView(navController: NavHostController) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navItems = listOf(NavItem.Home, NavItem.Search, NavItem.Bookmark)
    val backStackState = navController.currentBackStackEntryAsState()
    val isBottomBarVisible = backStackState.value?.destination?.route == HOME_ROUTE
            || backStackState.value?.destination?.route == SEARCH_SCREEN
            || backStackState.value?.destination?.route == BOOKMARK_SCREEN

    selectedItemIndex = when (backStackState.value?.destination?.route) {
        HOME_ROUTE -> 0
        SEARCH_SCREEN -> 1
        BOOKMARK_SCREEN -> 2
        else -> 0
    }
    if (isBottomBarVisible) {
        BottomAppBar {
            NavigationBar {
                navItems.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(navItem.route)
                        }, icon = {
                            Icon(
                                if (selectedItemIndex == index) navItem.selectedIcon else navItem.unselectedIcon,
                                contentDescription = navItem.label
                            )
                        }
                    )
                }
            }
        }
    }
}

sealed class NavItem(
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val label: String,
    val route: String
) {
    object Home : NavItem(Icons.Outlined.Home, Icons.Filled.Home, "Home", HOME_ROUTE)
    object Search :
        NavItem(Icons.Outlined.Search, Icons.Filled.Search, "Search", SEARCH_SCREEN)

    object Bookmark :
        NavItem(Icons.Outlined.Favorite, Icons.Filled.Favorite, "Bookmark", BOOKMARK_SCREEN)
}