package WGHxPERNAxBEAST.BeastlyCustomization.world;

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
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockList.carbon_rock.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(22, 4, 8, 200)));
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockList.azr_ore.getDefaultState(), 4), Placement.COUNT_RANGE, new CountRangeConfig(6, 6, 10, 120)));
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockList.pop_ore.getDefaultState(), 4), Placement.COUNT_RANGE, new CountRangeConfig(6, 6, 10, 120)));
			
		}
	}
}
