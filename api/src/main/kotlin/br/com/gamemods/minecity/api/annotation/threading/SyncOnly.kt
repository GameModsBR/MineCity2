package br.com.gamemods.minecity.api.annotation.threading

/**
 * Indicates that the annotated element must be executed only on the main server thread to prevent crashes or bad behaviours.
 */
@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR, message = "Make sure this is being executed on the main server thread"
)
public annotation class SyncOnly
