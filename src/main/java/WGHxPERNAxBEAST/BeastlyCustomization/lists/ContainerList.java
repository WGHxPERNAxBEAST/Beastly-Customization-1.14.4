package WGHxPERNAxBEAST.BeastlyCustomization.lists;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.ChickenFactoryContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

public class ContainerList {
	@ObjectHolder(BeastlyCustomizationMain.modid + ":chick_fact")
	public static ContainerType<ChickenFactoryContainer> chicken_factory;
}
