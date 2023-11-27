package br.com.gamemods.minecity.api.annotation.side

/**
 * Indicates that the annotated element must be executed only on server side and might crash on client.
 */
@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR, message = "Make sure this is being executed on server side"
)
public annotation class ServerSideOnly
