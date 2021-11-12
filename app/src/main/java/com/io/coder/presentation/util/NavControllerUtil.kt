package com.io.coder.presentation.util

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.io.coder.domain.model.Employee
import com.io.coder.presentation.error_screen.model_parcelize.toParcelize
import com.io.coder.util.Constants.EMPLOYEE

fun NavController.navigate(
    route: String,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    args: List<Pair<String, Parcelable>>? = null,
) {
    if (args == null || args.isEmpty()) {
        navigate(route, navOptions, navigatorExtras)
        return
    }
    navigate(route, navOptions, navigatorExtras)
    val addedEntry: NavBackStackEntry = backQueue.last()
    val argumentBundle: Bundle = addedEntry.arguments ?: Bundle().also {
        addedEntry.arguments?.putAll(it)
    }
    args.forEach { (key, arg) ->
        argumentBundle.putParcelable(key, arg)
    }
}

inline fun <reified T : Parcelable> NavController.navigateC(
    route: String,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    arg: T? = null,

    ) {
    if (arg == null) {
        navigate(route, navOptions, navigatorExtras)
        return
    }
    navigate(
        route = route,
        navOptions = navOptions,
        navigatorExtras = navigatorExtras,
        args = listOf(T::class.qualifiedName!! to arg),
    )
}

fun NavBackStackEntry.requiredArguments(): Bundle = arguments ?: throw IllegalStateException("Arguments were expected, but none were provided!")

@Composable
inline fun <reified T : Parcelable> NavBackStackEntry.rememberRequiredArgument(
    key: String = T::class.qualifiedName!!,
): T = remember {
    requiredArguments().getParcelable<T>(key) ?: throw IllegalStateException("Expected argument with key: $key of type: ${T::class.qualifiedName!!}")
}

@Composable
inline fun <reified T : Parcelable> NavBackStackEntry.rememberArgument(
    key: String = T::class.qualifiedName!!,
): T? = remember {
    arguments?.getParcelable(key)
}