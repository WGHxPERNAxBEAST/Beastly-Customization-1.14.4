package WGHxPERNAxBEAST.BeastlyCustomization.world;

import WGHxPERNAxBEAST.BeastlyCustomization.config.OreGenConfig;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.BlockList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {
	
	public static void setupOreGeneration() {
		for(Biome biome : ForgeRegistries.BIOMES) {
			if (OreGenConfig.gen_carbon_rock.get()) {
				biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockList.carbon_rock.getDefaultState(), OreGenConfig.carbon_rock_chance.get()), Placement.COUNT_RANGE, new CountRangeConfig(10, 10, 15, 160)));
			}
		}
	}
}
