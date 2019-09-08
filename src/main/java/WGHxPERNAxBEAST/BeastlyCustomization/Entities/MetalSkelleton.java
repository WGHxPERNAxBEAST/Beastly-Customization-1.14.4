package WGHxPERNAxBEAST.BeastlyCustomization.Entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class MetalSkelleton extends AbstractSkeletonEntity{

	private double skellyBaseSpeed;// = 0.35D;
	private double skellyMaxHealth;// = 30.0D;
	private float skellyViewRange;// = 8.0F;
	private Item skellyMainHand;// = Items.BOW;
	private double skellyAttackDamage;// = 4.5D;
	public static ResourceLocation textureLoc;// = BeastlyCustomizationMain.location("textures/entity/bs_skelly.png");
	
	public MetalSkelleton(EntityType<? extends MetalSkelleton> type, World world) {
		super(type, world);
	}

	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(2, new RestrictSunGoal(this));
	      this.goalSelector.addGoal(3, new FleeSunGoal(this, this.skellyBaseSpeed * 4));
	      this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, this.skellyBaseSpeed * 4));
	      this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, this.skellyViewRange));
	      this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
	      this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
	    this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.skellyMaxHealth);
	    this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.skellyBaseSpeed);
	    this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(this.skellyAttackDamage);
	}
	
	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		super.setEquipmentBasedOnDifficulty(difficulty);
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(this.skellyMainHand));
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
	      return SoundEvents.ENTITY_SKELETON_AMBIENT;
	}
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SKELETON_DEATH;
	}
	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_SKELETON_STEP;
	}
	
	public ResourceLocation getTextureLoc() {
		return textureLoc;
	}
	
	public void setTextureLoc(ResourceLocation textureLocIn) {
		textureLoc = textureLocIn;
	}
	
	public double getSkellyBaseSpeed() {
		return skellyBaseSpeed;
	}

	public void setSkellyBaseSpeed(double skellyBaseSpeed) {
		this.skellyBaseSpeed = skellyBaseSpeed;
	}

	public double getSkellyMaxHealth() {
		return skellyMaxHealth;
	}

	public void setSkellyMaxHealth(double skellyMaxHealth) {
		this.skellyMaxHealth = skellyMaxHealth;
	}

	public float getSkellyViewRange() {
		return skellyViewRange;
	}

	public void setSkellyViewRange(float skellyViewRange) {
		this.skellyViewRange = skellyViewRange;
	}

	public Item getSkellyMainHandItem() {
		return skellyMainHand;
	}

	public void setSkellyMainHandItem(Item skellyMainHandItem) {
		this.skellyMainHand = skellyMainHandItem;
	}

	public double getSkellyAttackDamage() {
		return skellyAttackDamage;
	}

	public void setSkellyAttackDamage(double skellyAttackDamage) {
		this.skellyAttackDamage = skellyAttackDamage;
	}

}
