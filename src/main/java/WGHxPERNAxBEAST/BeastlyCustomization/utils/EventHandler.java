package WGHxPERNAxBEAST.BeastlyCustomization.utils;

import WGHxPERNAxBEAST.BeastlyCustomization.blocks.DeathBoxBlock;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.BlockList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.DeathBoxTile;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
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
			if (rand % 55 == 0) {
				entityIn.entityDropItem(ItemList.brain);
			} else if (rand % 46 == 0) {
				ItemStack fragStack = new ItemStack(ItemList.brain_fragment);
				fragStack.setCount(randFlip(randFlip(1, 2), randFlip(3, randFlip(2, 4))));
				entityIn.entityDropItem(fragStack);
			}
		}
		if (entityIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityIn;
			PlayerInventory inv = player.inventory;
			if (!inv.isEmpty()) {
				BlockPos pos = player.getPosition().up();
				World world = player.getEntityWorld();
				if (pos.getY() < 0) {
					pos = pos.up(pos.getY() * -1);
					//BeastlyCustomizationMain.logger.log(Level.INFO, "Block should be @{}", pos);
				}
				DeathBoxBlock deathBox = (DeathBoxBlock) BlockList.death_box.getBlock();
				deathBox.setBoxOwner(player, true);
				world.setBlockState(pos.down(), Blocks.COBBLESTONE.getDefaultState());
				world.setBlockState(pos, deathBox.getDefaultState());
				DeathBoxTile chest = (DeathBoxTile) deathBox.getContainer(world.getBlockState(pos), world, pos);
				DeathBoxBlock.setBoxContense(chest, inv);
			}
		} else if (entityIn instanceof EnderDragonEntity) {
			ItemStack elytraStack = new ItemStack(Items.ELYTRA);
			elytraStack.setCount(1);
			elytraStack.setDisplayName(new StringTextComponent("Dragon Wings"));
			entityIn.entityDropItem(elytraStack);
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
