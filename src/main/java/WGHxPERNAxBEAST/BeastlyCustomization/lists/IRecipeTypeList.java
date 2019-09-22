package WGHxPERNAxBEAST.BeastlyCustomization.lists;

import java.util.Optional;

import WGHxPERNAxBEAST.BeastlyCustomization.utils.IToolCrafterRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public interface IRecipeTypeList <T extends IRecipe<?>> {
	   IRecipeType<IToolCrafterRecipe> TOOL_CRAFTING = register("tool_crafting");

	   static <T extends IRecipe<?>> IRecipeType<T> register(final String key) {
	      return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new IRecipeType<T>() {
	         public String toString() {
	            return key;
	         }
	      });
	   }

	   @SuppressWarnings("unchecked")
	default <C extends IInventory> Optional<T> matches(IRecipe<C> recipe, World worldIn, C inv) {
	      return recipe.matches(inv, worldIn) ? Optional.of((T)recipe) : Optional.empty();
	   }
	}