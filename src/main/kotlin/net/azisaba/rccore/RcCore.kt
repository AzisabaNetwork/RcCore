package net.azisaba.rccore

import com.charleskorn.kaml.Yaml
import java.io.File
import net.azisaba.rccore.config.IntegrationConfig
import net.azisaba.rccore.config.RcCoreConfig
import net.azisaba.rccore.extension.registerEvents
import net.azisaba.rccore.integration.auctionhouse.MarketEventListener
import net.azisaba.rccore.listener.HotbarLockListener
import net.azisaba.rcfreemarket.extension.getChildFile
import org.bukkit.plugin.java.JavaPlugin

class RcCore : JavaPlugin() {
    private val configFile: File = dataFolder.getChildFile("config.yml")
    lateinit var config: RcCoreConfig

    override fun onEnable() {
        dataFolder.mkdirs()
        logger.info("Welcome to RcCore! version: ${pluginMeta.version}")

        // === Config setup ===
        logger.info("Loading config...")
        // if not config exists, save default config
        if (!configFile.exists()) {
            saveDefaultConfig()

            logger.info("New config file was wrote.")
            logger.warning("Edit config and enable it.")

            // disable self
//            server.pluginManager.disablePlugin(this)
//            return
        }

        loadConfig()

        registerEvents(
            MarketEventListener(),
            HotbarLockListener(),
        )

        slF4JLogger.info("initialized!")
        instance = this
    }

    override fun saveDefaultConfig() {
        saveConfig(RcCoreConfig())
    }

    override fun saveConfig() {
        saveConfig(config)
    }

    /**
     * Update config
     * To get fresh configuration!
     */
    fun updateConfig() {
        loadConfig()
    }

    private fun saveConfig(config: RcCoreConfig) {
        configFile.writeText(
            Yaml.default.encodeToString(RcCoreConfig.serializer(), config),
        )
    }

    private fun loadConfig() {
        config =
            Yaml.default.decodeFromString(
                RcCoreConfig.serializer(),
                configFile.readText(),
            )
    }

    override fun onDisable() {
        // Plugin shutdown logic
        slF4JLogger.info("See you!")
        instance = null
    }

    companion object {
        private var instance: RcCore? = null

        fun getInstance(): RcCore {
            if(instance == null) {
                error("RcCore wasn't initialized!")
            }
            return instance!!
        }
    }
}
