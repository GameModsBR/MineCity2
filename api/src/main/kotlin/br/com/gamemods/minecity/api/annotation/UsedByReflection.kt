package br.com.gamemods.minecity.api.annotation

/**
 * Indicates that something that may be marked as unused by IDE is actually used by reflection,
 * so you can suppress unused warnings annotated by [UsedByReflection] safely.
 */
@Retention(value = AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS, AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.CONSTRUCTOR
)
public annotation class UsedByReflection(
    val value: String = ""
)
