package WGHxPERNAxBEAST.BeastlyCustomization.lists;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.BatteryTile;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.CarbonDustGeneratorTile;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.ChickenFactoryTile;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.DeathBoxTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class TileList {
	@ObjectHolder(BeastlyCustomizationMain.modid + ":chick_fact")
	public static TileEntityType<ChickenFactoryTile> chicken_factory;
	@ObjectHolder(BeastlyCustomizationMain.modid + ":cd_pow_gener")
	public static TileEntityType<CarbonDustGeneratorTile> cd_pow_gener;
	@ObjectHolder(BeastlyCustomizationMain.modid + ":bs_battery")
	public static TileEntityType<BatteryTile> battery;
	@ObjectHolder(BeastlyCustomizationMain.modid + ":death_box")
	public static TileEntityType<DeathBoxTile> death_box;
}
