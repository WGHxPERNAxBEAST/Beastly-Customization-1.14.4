package WGHxPERNAxBEAST.BeastlyCustomization.Entities;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PPS_AdultGolem extends AdultGolem{
	
	public static String textName;
	
	public PPS_AdultGolem(EntityType<? extends AdultGolem> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public AdultGolem setAttributes(EntityType<? extends AdultGolem> type, World worldIn, Double healthIn,
			Double speedIn, Double kb_resistIn, Float targetingRange, String name) {
		textName = name;
		return super.setAttributes(type, worldIn, healthIn, speedIn, kb_resistIn, targetingRange, name);
	}
	
	@Override
	public ResourceLocation getTextureLoc() {
		return BeastlyCustomizationMain.location("textures/entity/" + textName + ".png");
	}

}