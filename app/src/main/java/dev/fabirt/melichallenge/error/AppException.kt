package dev.fabirt.melichallenge.error

sealed class AppException : Exception() {

    object Network : AppException()
    object Unexpected : AppException()

    fun toFailure(): Failure {
        return when (this) {
            Network -> Failure.Network
            Unexpected -> Failure.Unexpected
        }
    }
}