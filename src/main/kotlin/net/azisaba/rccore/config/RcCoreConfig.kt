package net.azisaba.rccore.config

import kotlinx.serialization.Serializable
import net.azisaba.rccore.item.HotbarItem

@Serializable
data class RcCoreConfig(
    val slotSetting: Map<HotbarItem, EachSlotSetting> = getDefaultSlotSetting(),
    val integrationSetting: IntegrationConfig = IntegrationConfig(),
)
