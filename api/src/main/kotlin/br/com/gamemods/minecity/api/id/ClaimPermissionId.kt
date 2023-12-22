package br.com.gamemods.minecity.api.id

import br.com.gamemods.minecity.api.id.ClaimPermissionId.Companion.VALID_REGEX
import kotlinx.serialization.Serializable

/**
 * Represents a unique identifier for claim permissions.
 * This ID is used to manage and identify different types of claim permissions.
 * It follows a specific pattern as defined in [VALID_REGEX].
 *
 * @property id The unique string identifier for the permission.
 */
@Serializable
@JvmInline
public value class ClaimPermissionId(private val id: String) {

    init {
        require(id.matches(VALID_REGEX)) {
            "Invalid PermissionId pattern: $id (PermissionId pattern must match: $VALID_REGEX)"
        }
    }

    /**
     * Returns the string representation of the claim permission ID.
     *
     * @return The claim permission ID as a string.
     */
    override fun toString(): String = id

    public companion object {
        /**
         * The regex pattern that valid ClaimPermissionId instances must match.
         */
        private val VALID_REGEX = Regex("^([a-z][a-z0-9]*)(_[a-z][a-z0-9]*)*:/([a-z][a-z0-9]*)(_[a-z][a-z0-9]*)*$")

        /**
         * ClaimPermissionId instance for door-related permissions.
         */
        public val DOORS: ClaimPermissionId = ClaimPermissionId("minecity:doors")

        /**
         * ClaimPermissionId instance for button-related permissions.
         */
        public val BUTTONS: ClaimPermissionId = ClaimPermissionId("minecity:buttons")

        /**
         * ClaimPermissionId instance for computer screen-related permissions.
         */
        public val COMPUTER_SCREEN: ClaimPermissionId = ClaimPermissionId("minecity:computer_screen")

        /**
         * ClaimPermissionId instance for machine-related permissions.
         */
        public val MACHINES: ClaimPermissionId = ClaimPermissionId("minecity:machines")

        /**
         * ClaimPermissionId instance for panel-related permissions.
         */
        public val PANELS: ClaimPermissionId = ClaimPermissionId("minecity:panels")

        /**
         * ClaimPermissionId instance for computer-related permissions.
         */
        public val COMPUTERS: ClaimPermissionId = ClaimPermissionId("minecity:computers")

        /**
         * ClaimPermissionId instance for building-related permissions.
         */
        public val BUILD: ClaimPermissionId = ClaimPermissionId("minecity:build")
    }
}
