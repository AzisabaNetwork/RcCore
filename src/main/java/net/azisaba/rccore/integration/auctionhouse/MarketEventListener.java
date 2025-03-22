package net.azisaba.rccore.integration.auctionhouse;

import com.spawnchunk.auctionhouse.events.ListItemEvent;
import net.azisaba.rccore.config.IntegrationConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class MarketEventListener implements Listener {
    @EventHandler
    public void onListNewItem(ListItemEvent event) {
        List<Component> loreList = event.getItem().lore();
        if (loreList == null) return;

        boolean isDenyItem = loreList.stream()
                .map(l -> LegacyComponentSerializer.legacyAmpersand().serialize(l))
                .anyMatch(l ->
                        IntegrationConfig.getMarketDenyItemLore().stream().anyMatch(l::contains)
                );

        if(isDenyItem) {
            event.setCancelled(true);
            // check: 何かしらのプレイヤーへのフィードバックがいるかも？
        }
    }
}
