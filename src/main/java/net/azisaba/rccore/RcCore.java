package net.azisaba.rccore;

import net.azisaba.rccore.config.IntegrationConfig;
import net.azisaba.rccore.integration.auctionhouse.MarketEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RcCore extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        IntegrationConfig.loadConfig(getConfig());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MarketEventListener(), this);

        getSLF4JLogger().info("initialized!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getSLF4JLogger().info("See you!");
    }
}
