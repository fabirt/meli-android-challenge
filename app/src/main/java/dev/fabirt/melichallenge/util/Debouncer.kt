package dev.fabirt.melichallenge.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Debouncer(private val milliseconds: Long) {

    private var job: Job? = null

    fun launch(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit) {
        job?.cancel()
        job = scope.launch {
            delay(milliseconds)
            block()
        }
    }
}