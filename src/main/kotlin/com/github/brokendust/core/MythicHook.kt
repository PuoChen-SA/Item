package com.github.brokendust.core

import ink.ptms.um.event.MobDeathEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.util.random
import taboolib.common5.Coerce

/**
 * @author:     BrokenDust
 * @description:  兼容MythicMobs
 * @date:    2022/12/19 0019 16:15
 * @version:    1.0
 */
object MythicHook {
    @SubscribeEvent
    fun onMythicMobsSpawn(e: MobDeathEvent){
        val section = e.mob.config.getStringList(e.mob.id + "EItems.drops").forEach{
            val args = it.split(" ")
            if (args.size == 3 && !random(Coerce.toDouble(args[2]))) {
                return@forEach
            }
            val item = ItemManager.itemMap[args[0]]!!
            val amount = args.getOrElse(1) { "1" }.split("-").map { a -> Coerce.toInteger(a) }
            item.amount = random(amount[0], amount.getOrElse(1) { amount[0] })
            e.drop.add(item)
        }
    }
}