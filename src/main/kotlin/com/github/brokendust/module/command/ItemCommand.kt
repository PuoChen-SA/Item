package com.github.brokendust.module.command

import com.github.brokendust.core.ItemGenerateEvent
import com.github.brokendust.core.ItemManager
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.*
import taboolib.expansion.createHelper

/**
 * @author:     BrokenDust
 * @description:  物品命令
 * @date:    2022/12/19 0019 14:18
 * @version:    1.0
 */
@CommandHeader("item" , aliases = ["it"] , permissionDefault = PermissionDefault.OP)
object ItemCommand {

    @CommandBody
    val helper = mainCommand {
        createHelper()
    }

    @CommandBody
    val give = subCommand {
        dynamic("itemId"){
            suggestion<CommandSender> { _, _ -> ItemManager.itemMap.keys().toList() }
            execute<CommandSender> { sender, context, _ ->
                val player = sender as Player
                player.inventory.addItem(ItemManager.itemMap[context.argumentOrNull(0)])
                ItemGenerateEvent(ItemManager.itemMap[context.argumentOrNull(0)]!!, 1).call()
            }
        }
    }

    @CommandBody
    val reload = subCommand {
        execute<CommandSender> { _, _, _ ->
            ItemManager.reloadItems()
        }
    }
}