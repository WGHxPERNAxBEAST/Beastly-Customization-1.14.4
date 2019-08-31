package WGHxPERNAxBEAST.BeastlyCustomization.items;

import java.util.function.Predicate;

import WGHxPERNAxBEAST.BeastlyCustomization.Entities.projectiles.LaserBeamProjectile;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemLazerGun extends ShootableItem{

	private int useTime;
	private float maxVelocity;
	
	public static final Predicate<ItemStack> LaserCell = (stack) -> {
		if (stack.getItem() == ItemList.lazer_pow_cell) {
			return true;
		} else {
			return false;
		}
	};
	
	public ItemLazerGun(int useTimeIn, float maxVelocityIn, Properties builder) {
		super(builder);
		this.useTime = useTimeIn;
		this.maxVelocity = maxVelocityIn;
		this.addPropertyOverride(new ResourceLocation("pull"), (stack, world, entity) -> {
	       if (entity == null) {
	          return 0.0F;
	       } else {
	          return !(entity.getActiveItemStack().getItem() instanceof ItemLazerGun) ? 0.0F : (float)(stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
	       }
	    });
	    this.addPropertyOverride(new ResourceLocation("pulling"), (stack, world, entity) -> {
	       return entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F;
	    });
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
	      if (entityLiving instanceof PlayerEntity) {
	         PlayerEntity playerentity = (PlayerEntity)entityLiving;
	         boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
	         ItemStack itemstack = playerentity.findAmmo(stack);

	         int i = this.getUseDuration(stack) - timeLeft;
	         i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
	         if (i < 0) return;

	         if (!itemstack.isEmpty() || flag) {
	            if (itemstack.isEmpty()) {
	               itemstack = new ItemStack(ItemList.lazer_pow_cell);
	            }

	            float f = getLazerVelocity(i);
	            if (!((double)f < 0.1D)) {
	               boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof LazerPowerCell && ((LazerPowerCell)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
	               if (!worldIn.isRemote) {
	            	  LazerPowerCell arrowitem = (LazerPowerCell)(itemstack.getItem() instanceof LazerPowerCell ? itemstack.getItem() : ItemList.lazer_pow_cell);
	                  LaserBeamProjectile abstractarrowentity = (LaserBeamProjectile) arrowitem.createArrow(worldIn, itemstack, playerentity);
	                  abstractarrowentity = customeArrow(abstractarrowentity);
	                  abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
	                  if (f == 1.0F) {
	                     abstractarrowentity.setIsCritical(true);
	                  }

	                  stack.damageItem(1, playerentity, (player) -> {
	                	  player.sendBreakAnimation(playerentity.getActiveHand());
	                  });

	                  worldIn.addEntity(abstractarrowentity);
	               }

	               worldIn.playSound((PlayerEntity)null, playerentity.posX, playerentity.posY, playerentity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	               if (!flag1 && !playerentity.abilities.isCreativeMode) {
	                  itemstack.shrink(1);
	                  if (itemstack.isEmpty()) {
	                     playerentity.inventory.deleteStack(itemstack);
	                  }
	               }

	               playerentity.addStat(Stats.ITEM_USED.get(this));
	            }
	         }
	      }
	   }
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	      ItemStack itemstack = playerIn.getHeldItem(handIn);
	      boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

	      ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
	      if (ret != null) return ret;

	      if (!playerIn.abilities.isCreativeMode && !flag) {
	         return flag ? new ActionResult<>(ActionResultType.PASS, itemstack) : new ActionResult<>(ActionResultType.FAIL, itemstack);
	      } else {
	         playerIn.setActiveHand(handIn);
	         return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	      }
	   }

	private LaserBeamProjectile customeArrow(LaserBeamProjectile arrow) {
		return arrow;
	}
	
	public int getUseDuration(ItemStack stack) {
	      return this.useTime;
	}
	
	public UseAction getUseAction(ItemStack stack) {
	      return UseAction.BOW;
	   }

	public float getLazerVelocity(int charge) {
		float f = (float)charge / (this.maxVelocity * 20.0F);
	    f = (f * f + f * (this.maxVelocity * 2.0F)) / (this.maxVelocity * 3.0F);
	    if (f > this.maxVelocity) {
	    	f = this.maxVelocity;
	    }
	    return f;
	}

	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return LaserCell;
	}

}
