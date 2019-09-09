package WGHxPERNAxBEAST.BeastlyCustomization.utils;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
	
	private int chest1Iter = 0;
	
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
			BlockPos pos1 = player.getPosition().up();
			World world = player.getEntityWorld();
			world.setBlockState(pos, Blocks.CHEST.getDefaultState());
			world.setBlockState(pos1, Blocks.CHEST.getDefaultState());
			ChestTileEntity chest = (ChestTileEntity) world.getBlockState(pos).getContainer(world, pos);
			ChestTileEntity chest1 = (ChestTileEntity) world.getBlockState(pos1).getContainer(world, pos1);
			for(int i = 0; i < 27; i++) {
				ItemStack stack = inv.getStackInSlot(i);
				if (stack != null) {
					//BeastlyCustomizationMain.logger.log(Level.INFO, "Adding stack: {}", stack);
					chest.setInventorySlotContents(i, stack);
					inv.deleteStack(stack);
				}
			}
			for(chest1Iter = 0; chest1Iter < 9; chest1Iter++) {
				ItemStack stack = inv.getStackInSlot(chest1Iter + 27);
				if (stack != null) {
					//BeastlyCustomizationMain.logger.log(Level.INFO, "Adding stack: {}", stack);
					chest1.setInventorySlotContents(chest1Iter, stack);
					inv.deleteStack(stack);
				}
			}
			player.getArmorInventoryList().forEach(stack->{
				chest1.setInventorySlotContents(chest1Iter, stack); chest1Iter++;
				inv.deleteStack(stack);
			});
			chest1.setInventorySlotContents(chest1Iter, player.getHeldItemMainhand());chest1Iter++;
			inv.deleteStack(player.getHeldItemMainhand());
			chest1.setInventorySlotContents(chest1Iter, player.getHeldItemOffhand());chest1Iter++;
			inv.deleteStack(player.getHeldItemOffhand());
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
