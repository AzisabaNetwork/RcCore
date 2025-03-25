package net.azisaba.rccore.util

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

fun deserializeLegacyComponent(s: String) = LegacyComponentSerializer.legacyAmpersand().deserialize(s)