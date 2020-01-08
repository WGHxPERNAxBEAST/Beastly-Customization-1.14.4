package WGHxPERNAxBEAST.BeastlyCustomization.Entities;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class BS_Skelly extends MetalSkelleton{
	
	public BS_Skelly(EntityType<? extends MetalSkelleton> type, World worldIn) {
		super(type, worldIn);
		ItemStack bow = new ItemStack(Items.BOW);
		bow.addEnchantment(Enchantments.POWER, 8);
		bow.addEnchantment(Enchantments.PUNCH, 3);
		bow.addEnchantment(Enchantments.FLAME, 1);
		bow.addEnchantment(Enchantments.INFINITY, 1);
		bow.setDisplayName(new StringTextComponent("Bow of Death"));
		this.setSkellyBaseSpeed(0.35D);
		this.setSkellyMaxHealth(45.0D);
		this.setSkellyViewRange(8.0F);
		this.setSkellyEquipment(bow, new ItemStack(ItemList.bs_helmet), new ItemStack(ItemList.bs_chestplate), new ItemStack(ItemList.bs_leggings), new ItemStack(ItemList.bs_boots));
		this.setTextureLoc(BeastlyCustomizationMain.location("textures/entity/bs_skelly.png"));
	}
	
	@Override
	public ResourceLocation getTextureLoc() {
		return BeastlyCustomizationMain.location("textures/entity/bs_skelly.png");
	}

}
