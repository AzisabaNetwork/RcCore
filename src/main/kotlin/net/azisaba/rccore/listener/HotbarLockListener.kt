package net.azisaba.rccore.listener

import net.azisaba.rccore.item.HotbarItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.player.PlayerGameModeChangeEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

class HotbarLockListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerJoin(e: PlayerJoinEvent) {
        if (needsLockSlot(e.player.gameMode)) {
            setLeftSideSlot(e.player.inventory)
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onGameModeChange(e: PlayerGameModeChangeEvent) {
        if (needsLockSlot(e.newGameMode)) {
            setLeftSideSlot(e.player.inventory)
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.clickedInventory !is PlayerInventory || !needsLockSlot(e.whoClicked.gameMode)) {
            return
        }

        if (6 <= e.slot && e.slot <= 8) {
            e.isCancelled = true
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onInventoryDrag(e: InventoryDragEvent) {
        if (e.inventory !is PlayerInventory || !needsLockSlot(e.whoClicked.gameMode)) {
            return
        }

        e.inventorySlots.also {
            if (it.contains(6) || it.contains(7) || it.contains(8)) {
                e.isCancelled = true
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerInteract(e: PlayerInteractEvent) {
        if(!needsLockSlot(e.player.gameMode)) return
        val player = e.player
        val itemSlot = player.inventory.heldItemSlot
        if(6 <= itemSlot && itemSlot <= 8) {
            val cmd = HotbarItem.entries[itemSlot - 6].getSetting().onClick
            val targetCmd: String = cmd.worlds[player.world.name] ?: cmd.default
            if(!player.performCommand(targetCmd)) {
                player.sendMessage(
                    Component.text("ツールが使用できませんでした。運営にお問い合わせください。").color(NamedTextColor.RED)
                )
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onHandItemSwap(event: PlayerSwapHandItemsEvent) {
        if(!needsLockSlot(event.player.gameMode)) return
        val heldItemSlot = event.player.inventory.heldItemSlot
        if(6 <= heldItemSlot && heldItemSlot <= 8) {
            event.isCancelled = true
        }
    }

    companion object {
        private fun needsLockSlot(gm: GameMode): Boolean = gm == GameMode.SURVIVAL || gm == GameMode.ADVENTURE

        private fun setLeftSideSlot(playerInventory: PlayerInventory) {
            for (i in HotbarItem.entries) {
                playerInventory.setItem(i.slot, i.getStack())
            }
        }
    }
}
