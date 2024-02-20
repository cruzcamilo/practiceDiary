package com.core.common.models

sealed class Routes(val route: String) {
    data object Home: Routes("home")
    data object CreateEntry: Routes("create_entry")
    data object DetailEntry: Routes("detail_entry")
}