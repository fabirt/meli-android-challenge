package dev.fabirt.melichallenge.util

import android.util.Log
import arrow.core.Either
import dev.fabirt.melichallenge.error.AppException
import dev.fabirt.melichallenge.error.Failure
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

suspend fun <T> runCatching(
    block: suspend () -> Either<Failure, T>
): Either<Failure, T> {
    return try {
        block()
    } catch (e: AppException) {
        Either.Left(e.toFailure())
    } catch (e: UnknownHostException) {
        Either.Left(Failure.Network)
    } catch (e: CancellationException) {
        Either.Left(Failure.Cancellation)
    } catch (e: Exception) {
        Either.Left(Failure.Unexpected)
    }
}