package WGHxPERNAxBEAST.BeastlyCustomization.lists;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.BatteryContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.CarbonDustGeneratorContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.ChickenFactoryContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

public class ContainerList {
	@ObjectHolder(BeastlyCustomizationMain.modid + ":chick_fact")
	public static ContainerType<ChickenFactoryContainer> chicken_factory;
	@ObjectHolder(BeastlyCustomizationMain.modid + ":cd_pow_gener")
	public static ContainerType<CarbonDustGeneratorContainer> cd_pow_gener;
	@ObjectHolder(BeastlyCustomizationMain.modid + ":battery")
	public static ContainerType<BatteryContainer> battery;
}
