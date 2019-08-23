package WGHxPERNAxBEAST.BeastlyCustomization.utils;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
			if (rand % 7 == 0) {
				entityIn.entityDropItem(ItemList.brain);
			} else if (rand % 15 == 0) {
				ItemStack fragStack = new ItemStack(ItemList.brain_fragment);
				fragStack.setCount(randFlip(randFlip(1, 2), randFlip(3, randFlip(2, 4))));
				entityIn.entityDropItem(fragStack);
			}
			
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
