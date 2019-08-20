package WGHxPERNAxBEAST.BeastlyCustomization.lists;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum ToolMatList implements IItemTier{
	azr_mat(3, 1173, 6.0F, 2.25F, 19, ItemList.azr_ingot),
	pop_mat(3, 1274, 5.4F, 2.325F, 18, ItemList.pop_ingot),
	bs_mat(5, 2342, 12.0F, 4.5F, 16, ItemList.blue_steel_ingot),
	rds_mat(5, 2547, 10.8F, 4.65F, 14, ItemList.rds_ingot),
	pps_mat(6, 3513, 18.0F, 6.75F, 13, ItemList.pps_ingot);
	
	private float attackDamage, efficiency;
	private int durability, harvestLevel, enchantability;
	private Item repairMat;
	
	private ToolMatList(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Item repairMaterialIn) {
	      this.harvestLevel = harvestLevelIn;
	      this.durability = maxUsesIn;
	      this.efficiency = efficiencyIn;
	      this.attackDamage = attackDamageIn;
	      this.enchantability = enchantabilityIn;
	      this.repairMat = repairMaterialIn;
	   }

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getMaxUses() {
		return this.durability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(this.repairMat);
	}
}
