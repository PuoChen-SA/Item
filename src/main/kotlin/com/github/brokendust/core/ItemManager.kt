package com.github.brokendust.core

import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.console
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.releaseResourceFile
import taboolib.library.xseries.XMaterial
import taboolib.module.configuration.Configuration
import taboolib.module.configuration.util.getStringColored
import taboolib.module.configuration.util.getStringListColored
import taboolib.module.lang.sendLang
import taboolib.platform.util.buildItem
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * @author:     BrokenDust
 * @description:  物品管理器
 * @date:    2022/12/19 0019 9:37
 * @version:    1.0
 */
object ItemManager {

    val itemMap = ConcurrentHashMap<String, ItemStack>()

    @Awake(LifeCycle.ENABLE)
    fun loadItems(){
        val startTime = System.currentTimeMillis()
        val file = File(getDataFolder() , "item")
        if (!file.exists()){
            releaseResourceFile("item/Example.yml")
        }
        loadItem(file)
        console().sendLang("Item-Loader-Loaded" , itemMap.values.size , System.currentTimeMillis() - startTime)
        itemMap.values.forEach { it ->
            console().sendMessage(it.type.toString())
        }
    }

    fun loadItem(file: File){
        when{
            file.isDirectory -> file.listFiles()?.forEach { loadItem(it) }
            file.extension == "yml" || file.extension == "json" -> {
                Configuration.loadFromFile(file).run {
                    getKeys(false).forEach { it ->
                        val section = getConfigurationSection(it)!!
                        itemMap[section.name] = buildItem(XMaterial.matchXMaterial(section.getString("mats")!!).get()){
                            name = section.getStringColored("name")
                            lore.addAll(section.getStringListColored("lore"))
                        }
                    }
                }
            }
        }
    }

    fun reloadItems() {
        loadItems()
    }

}