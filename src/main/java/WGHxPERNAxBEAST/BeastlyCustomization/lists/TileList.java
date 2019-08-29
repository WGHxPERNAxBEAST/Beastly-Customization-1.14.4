package WGHxPERNAxBEAST.BeastlyCustomization.lists;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.ChickenFactoryTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class TileList {
	@ObjectHolder(BeastlyCustomizationMain.modid + ":chick_fact")
	public static TileEntityType<ChickenFactoryTile> chicken_factory;
}
