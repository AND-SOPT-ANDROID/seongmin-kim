package org.sopt.and.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    val route: String

    @Serializable
    data object SignInScreen : Routes {
        override val route = "sign_in"
    }

    @Serializable
    data object SignUpScreen : Routes {
        override val route = "sign_up"
    }

    @Serializable
    data object MyPageScreen : Routes {
        override val route = "my_page"
    }

    @Serializable
    data object HomeScreen : Routes {
        override val route = "home"
    }

    @Serializable
    data object SearchScreen : Routes {
        override val route = "search"
    }

}