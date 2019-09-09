package WGHxPERNAxBEAST.BeastlyCustomization.utils;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onEntityDeath(LivingDeathEvent event) {
		Entity entityIn = event.getEntity();
		Entity source = event.getSource().getTrueSource();
		if (source instanceof PlayerEntity) {
			int rand = (((int)(Math.random() * 1000.0D) % 100) + 1);
			if (rand % 9 == 0) {
				entityIn.entityDropItem(ItemList.brain);
			} else if (rand % 35 == 0) {
				ItemStack fragStack = new ItemStack(ItemList.brain_fragment);
				fragStack.setCount(randFlip(randFlip(1, 2), randFlip(3, randFlip(2, 4))));
				entityIn.entityDropItem(fragStack);
			}
		} else if (entityIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityIn;
			PlayerInventory inv = player.inventory;
			BlockPos pos = player.getPosition();
			World world = player.getEntityWorld();
			world.setBlockState(pos, Blocks.CHEST.getDefaultState());
			ChestContainer chest = (ChestContainer) world.getBlockState(pos).getContainer(world, pos);
			for(int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack stack = inv.getStackInSlot(i);
				if (stack != null) {
					chest.putStackInSlot(i, stack);
				}
			}
			world.getBlockState(pos).getContainer(world, pos).getDisplayName().appendText(player.getName().getString() + "'s Death Box");
		}
	}

	private int randFlip(int opt1, int opt2) {
		int rand = (((int)(Math.random() * 1000.0D) % 100) + 1);
		if (rand % 2 == 0) {
			return opt1;
		} else {
			return opt2;
		}
	}
}
