package WGHxPERNAxBEAST.BeastlyCustomization.Entities;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RDS_BabyGolem extends BabyGolem{
	
	public static String textName;
	
	public RDS_BabyGolem(EntityType<? extends BabyGolem> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public BabyGolem setAttributes(EntityType<? extends BabyGolem> type, World worldIn, Double healthIn,
			Double speedIn, Double kb_resistIn, Float targetingRange, String name) {
		textName = name;
		return super.setAttributes(type, worldIn, healthIn, speedIn, kb_resistIn, targetingRange, name);
	}
	
	@Override
	public ResourceLocation getTextureLoc() {
		return BeastlyCustomizationMain.location("textures/entity/" + textName + ".png");
	}

}