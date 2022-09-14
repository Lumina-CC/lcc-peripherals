package pw.switchcraft.peripherals.util

import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.world.World
import pw.switchcraft.library.Tooltips.addDescLines
import pw.switchcraft.peripherals.ScPeripherals.modId

abstract class BaseItem(
  private val itemName: String,
  settings: Settings
) : Item(settings) {
  override fun getTranslationKey() = itemTranslationKey(itemName)

  override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
    super.appendTooltip(stack, world, tooltip, context)
    addDescLines(tooltip, getTranslationKey(stack))
  }

  companion object {
    fun itemTranslationKey(name: String) = "item.$modId.$name"
  }
}
