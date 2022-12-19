package com.github.brokendust.core

import org.bukkit.inventory.ItemStack
import taboolib.platform.type.BukkitProxyEvent

/**
 * @author:     BrokenDust
 * @description:  物品产生事件
 * @date:    2022/12/19 0019 14:19
 * @version:    1.0
 */
class ItemGenerateEvent(val item:ItemStack , val amount:Int) : BukkitProxyEvent() {
}