package net.azisaba.rccore

import net.azisaba.rccore.config.RcCoreConfig

val config: RcCoreConfig
    get() = RcCore.getInstance().config