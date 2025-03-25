package net.azisaba.rccore.config

import kotlinx.serialization.Serializable

@Serializable
data class IntegrationConfig (
    val marketDenyItemLore: List<String> = listOf(
        "Unsellable Item",
        "取引禁止"
    )
)
