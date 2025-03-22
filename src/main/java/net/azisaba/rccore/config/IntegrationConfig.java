package net.azisaba.rccore.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

// 連携先が増えた場合にクラスの切り離しが必要そう
public class IntegrationConfig {
    private static List<String> marketDenyItemLore;

    public static List<String> getMarketDenyItemLore() {
        return marketDenyItemLore;
    }

    public static void loadConfig(FileConfiguration config) {
        marketDenyItemLore = config.getStringList("marketDenyItemLore");
    }
}
