package WGHxPERNAxBEAST.BeastlyCustomization.Entities;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BS_Skelly extends MetalSkelleton{
	
	public BS_Skelly(EntityType<? extends MetalSkelleton> type, World worldIn) {
		super(type, worldIn);
		super.setSkellyBaseSpeed(0.35D);
		super.setSkellyMaxHealth(45.0D);
		super.setSkellyViewRange(8.0F);
		super.setSkellyMainHandItem(Items.BOW);
		super.setSkellyAttackDamage(3.4D);
		super.setTextureLoc(BeastlyCustomizationMain.location("textures/entity/bs_skelly.png"));
	}
	
	@Override
	public ResourceLocation getTextureLoc() {
		return BeastlyCustomizationMain.location("textures/entity/bs_skelly.png");
	}

}
