package net.bdew.lib.multiblock.render

import net.bdew.lib.Client
import net.bdew.lib.multiblock.ResourceProvider
import net.bdew.lib.multiblock.block.BlockOutput
import net.bdew.lib.render.connected.ConnectedTextureBlock
import net.minecraft.block.Block
import net.minecraft.client.renderer.{RenderType, RenderTypeLookup}

object MultiblockRenderHelper {
  def setup(blocks: Iterable[Block], resources: ResourceProvider): Unit = {
    blocks.filter(_.isInstanceOf[ConnectedTextureBlock])
      .foreach(block =>
        RenderTypeLookup.setRenderLayer(block,
          (x: RenderType) => x == RenderType.cutout() || x == RenderType.solid())
      )
    Client.blockColors.register(
      new OutputBlockColor(resources),
      blocks.filter(_.isInstanceOf[BlockOutput[_]]).toSeq: _*
    )
  }
}
