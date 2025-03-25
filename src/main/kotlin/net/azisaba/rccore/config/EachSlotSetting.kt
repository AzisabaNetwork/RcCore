package net.azisaba.rccore.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.azisaba.rccore.item.HotbarItem

internal fun getDefaultSlotSetting(): Map<HotbarItem, EachSlotSetting> {
    return mutableMapOf<HotbarItem, EachSlotSetting>().apply {
        HotbarItem.entries.forEach {
            this[it] = EachSlotSetting.getDefault(it.name.lowercase())
        }
    }.toMap()
}

@Serializable
data class EachSlotSetting(
    val name: String,
    val lore: List<String>,
    @SerialName("on_click") val onClick: OnClickSetting
) {
    companion object {
        fun getDefault(name: String): EachSlotSetting {
            return EachSlotSetting(
                "$name Stick",
                listOf("This is $name stick."),
                OnClickSetting.getDefault(name)
            )
        }
    }
}

@Serializable
data class OnClickSetting(
    val default: String,
    val worlds: Map<String, String>
) {
    companion object {
        fun getDefault(name: String): OnClickSetting {
            return OnClickSetting(
                "me default $name",
                mapOf("worldA" to "me hello $name")
            )
        }
    }
}