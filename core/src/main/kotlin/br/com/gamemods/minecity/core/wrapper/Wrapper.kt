package br.com.gamemods.minecity.core.wrapper

import br.com.gamemods.minecity.api.annotation.internal.InternalMineCityApi

/**
 * Wraps a value, should be used only on `value class`es.
 *
 * @property native The native object that is being wrapped
 */
@InternalMineCityApi
interface Wrapper {
    val native: Any

    /**
     * Adds a shortcut to wrap an object to a wrapper.
     *
     * @property O The class that is being wrapped by [W]
     * @property W The class that wraps [O]
     * @property constructor The constructor of [W] that receives only [O] as parameter.
     *
     * @property wrapper Shorthand for wrapping [O] into [W].
     */
    abstract class WrapperClass<O: Any, W: Wrapper>(@PublishedApi internal val constructor: (O) -> W) {
        inline val O.wrapper get() = constructor(this)
    }
}
