package net.bdew.lib.sensors.widgets

import com.mojang.blaze3d.matrix.MatrixStack
import net.bdew.lib.gui._
import net.bdew.lib.gui.widgets.Widget
import net.bdew.lib.sensors.SensorSystem
import net.minecraft.util.text.ITextComponent

import scala.collection.mutable.ArrayBuffer

class WidgetSensorResult[T](val p: Point, state: => T, system: SensorSystem[_, T]) extends Widget {
  override val rect = new Rect(p, 16, 16)

  override def handleTooltip(p: Point, tip: ArrayBuffer[ITextComponent]): Unit =
    tip += system.getResultText(state)

  override def draw(m: MatrixStack, mouse: Point, partial: Float): Unit =
    system.drawResult(m, state, rect, parent)
}
