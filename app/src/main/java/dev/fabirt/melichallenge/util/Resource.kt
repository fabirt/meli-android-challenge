package dev.fabirt.melichallenge.util

import dev.fabirt.melichallenge.error.Failure

sealed class Resource<out T> {
    object Idle : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    data class Error(val failure: Failure) : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
}