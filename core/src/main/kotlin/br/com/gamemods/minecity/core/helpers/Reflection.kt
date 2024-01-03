package br.com.gamemods.minecity.core.helpers

import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * Checks if an object **is** an instance of **ANY** of the classes given.
 * @param types A list of [KClass] that will be checked.
 *
 * The following code examples are equivalent.
 * ```kt
 * obj is TypeA || obj is TypeB
 * obj.isInstanceOfAny(TypeA::class, TypeB::class)
 * ```
 * @author alikindsys
 */
fun Any.isInstanceOfAny(vararg types: KClass<*>): Boolean {
    // Short-circuiting on a list based on an OR operation.
    for (cls in types) {
        // ⊤ OR { ⊥,⊤ } = ⊤
        // { ⊥,⊤ } OR ⊥ = { ⊥,⊤ }
        if (this::class.isSubclassOf(cls)) return true
    }
    // ⊥ OR ⊥ = ⊥
    return false
}

/**
 * Checks if an object **is NOT** an instance of **ALL** the classes given.
 * @param types A list of [KClass] that will be .
 *
 * The following code examples are equivalent.
 * ```kt
 * obj !is TypeA && obj !is TypeB
 * obj.isInstanceOfNone(TypeA::class, TypeB::class)
 * ```
 * @author alikindsys
 */
fun Any.isInstanceOfNone(vararg types: KClass<*>): Boolean {
    // !(P OR Q) = (!P AND !Q)
    return !this.isInstanceOfAny(*types)
}