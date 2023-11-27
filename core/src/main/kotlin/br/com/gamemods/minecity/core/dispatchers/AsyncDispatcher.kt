package br.com.gamemods.minecity.core.dispatchers

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@JvmSynthetic
@PublishedApi
internal lateinit var asyncDispatcher: CoroutineDispatcher

@Suppress("UnusedReceiverParameter")
@InternalMineCityApi
inline var Dispatchers.Async: CoroutineDispatcher
    get() = asyncDispatcher
    set(value) {
        asyncDispatcher = value
    }
