package br.com.gamemods.minecity.api.annotation

/**
 * Indicates that the annotated element has behavior that varies depending on the currently running platform.
 */
@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS, AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.CONSTRUCTOR
)
@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING, message = "This API behaves differently depending on the Minecraft platform, take care."
)
public annotation class PlatformDependent
