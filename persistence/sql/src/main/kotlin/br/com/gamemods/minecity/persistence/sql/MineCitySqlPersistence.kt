package br.com.gamemods.minecity.persistence.sql

import br.com.gamemods.minecity.api.server.MineCityServer
import br.com.gamemods.minecity.persistence.base.MineCityPersistence
import br.com.gamemods.minecity.persistence.base.MineCityPersistenceException
import br.com.gamemods.minecity.persistence.base.annotation.InternalMineCityPersistenceApi
import com.github.michaelbull.logging.InlineLogger
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.job
import org.flywaydb.core.Flyway
import javax.sql.DataSource

@InternalMineCityPersistenceApi
class MineCitySqlPersistence(
    private val server: MineCityServer,
    private val dataSource: DataSource,
): MineCityPersistence {
    override val coroutineContext = SupervisorJob(server.coroutineContext.job) + Dispatchers.IO + CoroutineName("MineCitySqlPersistence ${server.serverIp}")
    private val log = InlineLogger()

    @Throws(MineCityPersistenceException::class)
    fun upgrade() {
        val flyway = Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:br/com/gamemods/minecity/persistence/sql/migration")
            .load()

        val result = try {
            flyway.migrate()
        } catch (e: Throwable) {
            log.error(e) {
                "SQL migration failed with an exception!"
            }
            throw MineCityPersistenceException("SQL migration failed with an exception!", e)
        }
        if (!result.success) {
            log.error {
                "SQL migration failed."
            }
        } else if (result.migrationsExecuted > 0) {
            log.info {
                "SQL databased migrated from ${result.initialSchemaVersion} to ${result.targetSchemaVersion} (${result.migrationsExecuted} migrations executed) in ${result.totalMigrationTime}ms."
            }
        } else {
            log.info { "SQL database is up to date." }
        }
        if (result.warnings.isNotEmpty()) {
            log.warn {
                buildString {
                    append("SQL database migration raised ${result.warnings.size} warning(s):")
                    appendLine()
                    result.warnings.forEach {
                        append(it)
                        appendLine()
                        appendLine()
                    }
                }
            }
        }
        TODO()
    }
}
