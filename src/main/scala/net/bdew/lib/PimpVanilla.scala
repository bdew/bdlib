package net.bdew.lib

import net.bdew.lib.nbt.converters._
import net.bdew.lib.rich._
import net.minecraft.block.BlockState
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.math.BlockPos
import net.minecraft.world.{IBlockReader, World}
import net.minecraftforge.client.model.data.IModelData
import net.minecraftforge.common.util.LazyOptional

import scala.language.implicitConversions

/**
 * This object provides a bunch of implicits that makes minecraft classes nicer to work with
 * It also includes definitions that makes working with them in NBT data easier
 */
object PimpVanilla {
  implicit def pimpBlockPos(p: BlockPos): RichBlockPos = new RichBlockPos(p)
  implicit def pimpWorld(p: World): RichWorld = new RichWorld(p)
  implicit def pimpBlockReader(p: IBlockReader): RichBlockReader = new RichBlockReader(p)
  implicit def pimpNBT(p: CompoundNBT): RichCompoundNBT = new RichCompoundNBT(p)
  implicit def pimpBlockState(p: BlockState): RichBlockState = new RichBlockState(p)
  implicit def pimpModelData(p: IModelData): RichIModelData = new RichIModelData(p)
  implicit def pimpLazyOpt[T](o: LazyOptional[T]): RichLazyOpt[T] = new RichLazyOpt(o)

  // Helpers for NBT stuff

  implicit val tBlockPos: TBlockPos.type = TBlockPos
  implicit val tBoolean: TBoolean.type = TBoolean
  implicit val tFluid: TFluid.type = TFluid
  implicit val tFluidStack: TFluidStack.type = TFluidStack
  implicit val tItemStack: TItemStack.type = TItemStack
  implicit val tDirection: TDirection.type = TDirection
}
