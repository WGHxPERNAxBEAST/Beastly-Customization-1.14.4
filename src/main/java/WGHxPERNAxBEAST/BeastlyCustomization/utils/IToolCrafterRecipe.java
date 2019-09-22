package WGHxPERNAxBEAST.BeastlyCustomization.utils;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.IRecipeTypeList;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;

public interface IToolCrafterRecipe extends IRecipe<CraftingInventory> {
	default IRecipeType<?> getType() {
		return IRecipeTypeList.TOOL_CRAFTING;
	}
}