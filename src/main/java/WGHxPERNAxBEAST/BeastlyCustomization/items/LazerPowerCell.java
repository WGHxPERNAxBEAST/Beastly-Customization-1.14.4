package WGHxPERNAxBEAST.BeastlyCustomization.items;

import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;

public class LazerPowerCell extends ArrowItem{

	public LazerPowerCell(Properties builder) {
		super(builder);
	}
	
	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
		return true;
	}

}
