package com.github.brokendust

import org.bukkit.Bukkit
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.console
import taboolib.module.lang.Language
import taboolib.module.lang.sendLang
import taboolib.platform.BukkitPlugin

object Item : Plugin() {

    val plugin by lazy { BukkitPlugin.getInstance() }

    override fun onLoad() {
        Language.default = "zh_CN"
        console().sendLang("Plugin-Loading", Bukkit.getVersion())
    }

    override fun onEnable() {
        console().sendLang("Plugin-Enabled", plugin.description.version)
    }

}