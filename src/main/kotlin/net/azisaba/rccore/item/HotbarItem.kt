package net.azisaba.rccore.item

import net.azisaba.rccore.config
import net.azisaba.rccore.config.EachSlotSetting
import net.azisaba.rccore.util.deserializeLegacyComponent
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

enum class HotbarItem(val slot: Int) {
    LEFT(6),
    MIDDLE(7),
    RIGHT(8);
    fun getSetting(): EachSlotSetting {
        return config.slotSetting[this] ?: error("Slot setting for ${this.name} wasn't found.")
    }

    fun getStack(): ItemStack {
        val setting: EachSlotSetting = getSetting()
        return ItemStack(Material.STICK, 1).apply {
            editMeta { meta ->
                meta.displayName(deserializeLegacyComponent(setting.name))
                meta.lore(setting.lore.map { s -> deserializeLegacyComponent(s) })
                meta.setCustomModelData(ordinal + 1)
            }
        }
    }
}