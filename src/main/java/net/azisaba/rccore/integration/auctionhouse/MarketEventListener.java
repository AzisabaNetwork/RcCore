package net.azisaba.rccore.integration.auctionhouse;

import com.spawnchunk.auctionhouse.events.ListItemEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class MarketEventListener implements Listener {
    // TODO: 販売禁止アイテムに関する実装
    @EventHandler
    public void onListNewItem(ListItemEvent event) {
        List<Component> loreList = event.getItem().lore();
        if(loreList == null) return;
        loreList.stream()
                .map(l -> LegacyComponentSerializer.legacyAmpersand().serialize(l))
                .anyMatch(l -> l.contains(""));
    }
}
