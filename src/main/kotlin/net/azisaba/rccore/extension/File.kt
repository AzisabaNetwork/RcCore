package net.azisaba.rcfreemarket.extension

import java.io.File

fun File.getChildFile(name: String): File = File(this, name)
