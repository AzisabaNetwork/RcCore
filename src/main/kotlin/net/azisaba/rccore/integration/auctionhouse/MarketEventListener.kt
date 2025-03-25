package net.azisaba.rccore.integration.auctionhouse

import com.badbones69.crazyauctions.api.events.AuctionListEvent
import net.azisaba.rccore.config
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class MarketEventListener : Listener {
    @EventHandler
    fun onListNewItem(event: AuctionListEvent) {
        val loreList = event.item.lore()
        if (loreList == null) return

        val isDenyItem = loreList.stream()
            .map { l: Component -> LegacyComponentSerializer.legacyAmpersand().serialize(l) }
            .anyMatch { l: String -> config.integrationSetting.marketDenyItemLore.stream().anyMatch { s: String -> l.contains(s) } }

        if (isDenyItem) {
            event.isCancelled = true
            // check: 何かしらのプレイヤーへのフィードバックがいるかも？
        }
    }
}
