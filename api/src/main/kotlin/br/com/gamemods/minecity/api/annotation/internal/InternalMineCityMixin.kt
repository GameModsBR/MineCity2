package br.com.gamemods.minecity.api.annotation.internal

/**
 * Indicates that the annotated element should only be used by MineCity Mixin, not API users.
 */
@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS, AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_SETTER
)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR, message = "This is an internal MineCity Mixin that " +
            "should not be used from outside of br.com.gamemods.minecity's mixins. No compatibility guarantees are provided. " +
            "It is recommended to report your use-case of internal API to MineCity2 issue tracker, " +
            "so stable API could be provided instead"
)
public annotation class InternalMineCityMixin
