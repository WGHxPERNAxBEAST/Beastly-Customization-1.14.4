package WGHxPERNAxBEAST.BeastlyCustomization.lists;

import WGHxPERNAxBEAST.BeastlyCustomization.crafting.IToolCrafterRecipe;
import WGHxPERNAxBEAST.BeastlyCustomization.crafting.ToolCraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;

public class IRecipeTypeList {
	   public static IRecipeType<IToolCrafterRecipe> TOOL_CRAFTING;
	   public static IRecipeSerializer<ToolCraftingRecipe> TOOL_CRAFTING_SER;
}