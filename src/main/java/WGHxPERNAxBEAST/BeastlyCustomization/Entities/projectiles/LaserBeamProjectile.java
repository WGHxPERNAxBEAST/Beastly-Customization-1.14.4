package WGHxPERNAxBEAST.BeastlyCustomization.Entities.projectiles;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LaserBeamProjectile extends AbstractArrowEntity{
	private static final DataParameter<Integer> COLOR = EntityDataManager.createKey(LaserBeamProjectile.class, DataSerializers.VARINT);
	   

	protected LaserBeamProjectile(EntityType<? extends LaserBeamProjectile> type, World worldIn) {
		super(type, worldIn);
	}
	protected LaserBeamProjectile(EntityType<? extends LaserBeamProjectile> type, double x, double y, double z, World worldIn) {
	    this(type, worldIn);
	    this.setPosition(x, y, z);
	}

	protected LaserBeamProjectile(EntityType<? extends LaserBeamProjectile> type, LivingEntity shooter, World worldIn) {
	    this(type, shooter.posX, shooter.posY + (double)shooter.getEyeHeight() - (double)0.1F, shooter.posZ, worldIn);
	    this.setShooter(shooter);
	    if (shooter instanceof PlayerEntity) {
	    	this.pickupStatus = AbstractArrowEntity.PickupStatus.ALLOWED;
	    }
	}

	@Override
	protected ItemStack getArrowStack() {
		ItemStack stack = new ItemStack(ItemList.lazer_pow_cell);
		stack.setCount(0);
		return stack;
	}
	
	public int getColor() {
		return this.dataManager.get(COLOR);
	}
	
	public void setColor(int color) {
		this.dataManager.set(COLOR, color);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		super.handleStatusUpdate(id);
	}

}
