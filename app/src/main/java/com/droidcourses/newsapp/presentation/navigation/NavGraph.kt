package com.droidcourses.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.domain.models.Source
import com.droidcourses.newsapp.presentation.bookmark.BookmarkScreen
import com.droidcourses.newsapp.presentation.details.DetailsViewModel
import com.droidcourses.newsapp.presentation.details.NewsDetailsScreen
import com.droidcourses.newsapp.presentation.home.HomeScreen
import com.droidcourses.newsapp.presentation.home.HomeViewModel
import com.droidcourses.newsapp.presentation.onboarding.OnBoardingScreen
import com.droidcourses.newsapp.presentation.onboarding.OnBoardingViewModel
import com.droidcourses.newsapp.presentation.search.SearchScreen
import com.droidcourses.newsapp.presentation.search.SearchViewModel


@Composable
fun NavGraph(startDestination: String, navController: NavHostController) {

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Screen.AppStart.route,
            startDestination = Screen.OnBoardingScreen.route
        ){
            composable(Screen.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onBoardingEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Screen.NewsNav.route,
            startDestination = Screen.Home.route
        ){

            composable(Screen.Home.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles){
                    navController.navigate(Screen.Search.route)
                }
            }

            composable(Screen.Search.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(viewModel.state.value,viewModel::onEvent )
            }

            composable(Screen.Details.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                NewsDetailsScreen(
                    Article("dsds",
                        "\n" +
                                "Where does it come from?\n" +
                                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                                "\n" +
                                "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                                "\n" +
                                "Where can I get some?\n" +
                                "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.\n" +
                                "\n" +
                                "5\n" +
                                "\tparagraphs\n" +
                                "\twords\n" +
                                "\tbytes\n" +
                                "\tlists\n" +
                                "\tStart with 'Lorem\n" +
                                "ipsum dolor sit amet...'\n" +
                                "\n" +
                                "Translations: Can you help translate this site into a foreign language ? Please email us with details if you can help.\n" +
                                "There is a set of mock banners available here in three colours and in a range of standard banner sizes:\n" +
                                "BannersBannersBanners\n" +
                                "Donate: If you use this site regularly and would like to help keep the site on the Internet, please consider donating a small sum to help pay for the hosting and bandwidth bill. There is no minimum donation, any sum is appreciated - click here to donate using PayPal. Thank you for your support.\n" +
                                "Donate Bitcoin: 16UQLq1HZ3CNwhvgrarV6pMoA2CDjb4tyF\n" +
                                "NodeJS Python Interface GTK Lipsum Rails .NET Groovy\n" +
                                "The standard Lorem Ipsum passage, used since the 1500s\n" +
                                "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" +
                                "\n" +
                                "Section 1.10.32 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" +
                                "\"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\"\n" +
                                "\n" +
                                "1914 translation by H. Rackham\n" +
                                "\"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?\"\n" +
                                "\n" +
                                "Section 1.10.33 of \"de Finibus Bonorum et Malorum\", written by Cicero in 45 BC\n" +
                                "\"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\"\n" +
                                "\n" +
                                "1914 translation by H. Rackham\n" +
                                "\"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.\"\n" +
                                "\n" +
                                "help@lipsum.com\n" +
                                "Privacy Policy · Do Not Sell My Personal Information · Change Consent · \n",
                        "dsdsdsds",
                        "dsadsadsd",
                        Source("adsdsd","ddsdsa"),
                        "Grow with DroidCourses",
                        url = "https://www.android.com/",
                        urlToImage = "https://images.unsplash.com/photo-1563713665854-e72327bf780e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80")
                    ,onEvent = viewModel::onEvent
                )
            }

            composable(Screen.Bookmark.route) {
                BookmarkScreen()
            }
        }
    }
}