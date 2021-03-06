package net.bdew.lib.data.base

import net.bdew.lib.container.NoInvContainer
import net.minecraft.entity.player.{PlayerEntity, ServerPlayerEntity}

trait ContainerDataSlots extends NoInvContainer {
  val dataSource: TileDataSlots
  var lastSentChange: Long = dataSource.lastChange

  override protected def playerAdded(player: ServerPlayerEntity): Unit = {
    super.playerAdded(player)
    player.connection.send(dataSource.getDataSlotPacket)
  }

  override def broadcastChanges(): Unit = {
    super.broadcastChanges()
    // only send updates every 10 ticks
    if (dataSource.lastChange >= lastSentChange + 10 || (dataSource.lastChange > lastSentChange && lastSentChange + 10 < dataSource.getWorldObject.getGameTime)) {
      lastSentChange = dataSource.lastChange
      val pkt = dataSource.getDataSlotPacket

      for (player <- players)
        player.connection.send(pkt)
    }
  }

  override def stillValid(player: PlayerEntity): Boolean = dataSource.isEntityInRange(player, 64D)
}
