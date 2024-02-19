package com.core.common.models

sealed class Routes(val routes: String) {
    object Home: Routes("home")
    object DetailEntry: Routes("detail_entry")
}