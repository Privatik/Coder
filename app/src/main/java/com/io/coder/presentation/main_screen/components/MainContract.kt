package com.io.coder.presentation.main_screen.components


data class MainState(
    val searchtext: String
){
    companion object {
        fun initial() = MainState(
            searchtext = ""
        )
    }

}

sealed class MainAction{

    class ChangeText(val searchText: String): MainAction()
}