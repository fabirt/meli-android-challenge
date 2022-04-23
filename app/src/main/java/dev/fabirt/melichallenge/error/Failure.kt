package dev.fabirt.melichallenge.error

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.fabirt.melichallenge.R

sealed class Failure(@StringRes val key: Int) {

    object Network : Failure(R.string.network_error)

    object Unexpected : Failure(R.string.unexpected_error)

    class Custom(@StringRes key: Int) : Failure(key)

    @Composable
    fun translate(): String = stringResource(key)
}