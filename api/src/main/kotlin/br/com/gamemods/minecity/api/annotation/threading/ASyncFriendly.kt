package br.com.gamemods.minecity.api.annotation.threading

/**
 * Indicates that the annotated element can be executed outside the main server thread without problems.
 */
@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.PROPERTY_GETTER)
public annotation class ASyncFriendly
