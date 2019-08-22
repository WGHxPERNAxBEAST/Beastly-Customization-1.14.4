package WGHxPERNAxBEAST.BeastlyCustomization.lists;

import java.util.Set;

import javax.annotation.Nullable;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BabyGolem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockMaterialMatcher;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.event.RegistryEvent;

public class EntitiesList {
	
	@Nullable
	private BlockPattern babyGolemPattern;
	@Nullable
	private BlockPattern GolemPattern;
	
	@SuppressWarnings("unchecked")
	public static EntityType<?extends BabyGolem> BABY_GOLEM = (EntityType<? extends BabyGolem>) EntityType.Builder.create(BabyGolem::new, EntityClassification.CREATURE).build(BeastlyCustomizationMain.modid + ":baby_golem").setRegistryName(BeastlyCustomizationMain.location("baby_golem"));
	@SuppressWarnings("unchecked")
	public static EntityType<?extends AdultGolem> ADULT_GOLEM = (EntityType<? extends AdultGolem>) EntityType.Builder.create(AdultGolem::new, EntityClassification.CREATURE).build(BeastlyCustomizationMain.modid + ":adult_golem").setRegistryName(BeastlyCustomizationMain.location("adult_golem"));
	
	public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				
		);
	}
	
	public static void registerEntityWorldSpawns() {
		//registerEntityWorldSpawn(BS_BABY_GOLEM, Biome.BIOMES);
	}

	public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, ItemGroup itemGroup, String name) {
		SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(itemGroup));
		item.setRegistryName(BeastlyCustomizationMain.location(name));
		return item;
	}
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, Biome...biomes) {
		for (Biome biome : biomes) {
			if (biome != null) {
				biome.getSpawns(entity.getClassification()).add(new SpawnListEntry(entity, 10, 1, 7));
			}
		}
	}
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, Set<Biome> biomes) {
		for (Biome biome : biomes) {
			if (biome != null) {
				biome.getSpawns(entity.getClassification()).add(new SpawnListEntry(entity, 10, 1, 7));
			}
		}
	}
	
	public static void trySpawnGolems(World worldIn, BlockPos pos){
		AdultGolemSpawner(worldIn, pos, ADULT_GOLEM.create(worldIn).setAttributes(140.0D, 0.5D, 1.5D, "bs_golem"), BlockList.blue_steel_block);
		babyGolemSpawner(worldIn, pos, BABY_GOLEM.create(worldIn).setAttributes(120.0D, 0.7D, 1.5D, "bs_golem"), BlockList.blue_steel_block);
   }

	private static void babyGolemSpawner(World worldIn, BlockPos pos, BabyGolem babyGolemEntity, Block bodyBlock) {
		BlockPattern.PatternHelper blockpattern$patternhelper = getBabyGolemPattern(bodyBlock).match(worldIn, pos);
	    if (blockpattern$patternhelper != null) {
	       for(int j = 0; j < getBabyGolemPattern(bodyBlock).getPalmLength(); ++j) {
	          for(int k = 0; k < getBabyGolemPattern(bodyBlock).getThumbLength(); ++k) {
	             CachedBlockInfo cachedblockinfo2 = blockpattern$patternhelper.translateOffset(j, k, 0);
	             worldIn.setBlockState(cachedblockinfo2.getPos(), Blocks.AIR.getDefaultState(), 2);
	             worldIn.playEvent(2001, cachedblockinfo2.getPos(), Block.getStateId(cachedblockinfo2.getBlockState()));
	          }
	       }
	
	       BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
	       babyGolemEntity.setPlayerCreated(true);
	       babyGolemEntity.setLocationAndAngles((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
	       worldIn.addEntity(babyGolemEntity);
	
	       for(ServerPlayerEntity serverplayerentity1 : worldIn.getEntitiesWithinAABB(ServerPlayerEntity.class, babyGolemEntity.getBoundingBox().grow(5.0D))) {
	          CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity1, babyGolemEntity);
	       }
	
	       for(int i1 = 0; i1 < getBabyGolemPattern(bodyBlock).getPalmLength(); ++i1) {
	          for(int j1 = 0; j1 < getBabyGolemPattern(bodyBlock).getThumbLength(); ++j1) {
	             CachedBlockInfo cachedblockinfo1 = blockpattern$patternhelper.translateOffset(i1, j1, 0);
	             worldIn.notifyNeighbors(cachedblockinfo1.getPos(), Blocks.AIR);
	          }
	       }
	    }
	}

	private static void AdultGolemSpawner(World worldIn, BlockPos pos, AdultGolem GolemEntity, Block bodyBlock) {
		BlockPattern.PatternHelper blockpattern$patternhelper = getGolemPattern(bodyBlock).match(worldIn, pos);
	    if (blockpattern$patternhelper != null) {
	       for(int j = 0; j < getGolemPattern(bodyBlock).getPalmLength(); ++j) {
	          for(int k = 0; k < getGolemPattern(bodyBlock).getThumbLength(); ++k) {
	             CachedBlockInfo cachedblockinfo2 = blockpattern$patternhelper.translateOffset(j, k, 0);
	             worldIn.setBlockState(cachedblockinfo2.getPos(), Blocks.AIR.getDefaultState(), 2);
	             worldIn.playEvent(2001, cachedblockinfo2.getPos(), Block.getStateId(cachedblockinfo2.getBlockState()));
	          }
	       }
	
	       BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
	       GolemEntity.setPlayerCreated(true);
	       GolemEntity.setLocationAndAngles((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.05D, (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
	       worldIn.addEntity(GolemEntity);
	
	       for(ServerPlayerEntity serverplayerentity1 : worldIn.getEntitiesWithinAABB(ServerPlayerEntity.class, GolemEntity.getBoundingBox().grow(5.0D))) {
	          CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayerentity1, GolemEntity);
	       }
	
	       for(int i1 = 0; i1 < getBabyGolemPattern(bodyBlock).getPalmLength(); ++i1) {
	          for(int j1 = 0; j1 < getBabyGolemPattern(bodyBlock).getThumbLength(); ++j1) {
	             CachedBlockInfo cachedblockinfo1 = blockpattern$patternhelper.translateOffset(i1, j1, 0);
	             worldIn.notifyNeighbors(cachedblockinfo1.getPos(), Blocks.AIR);
	          }
	       }
	    }
	}

	private static BlockPattern getBabyGolemPattern(Block blockForBody) {
		return BlockPatternBuilder.start().aisle("~~~", "~^~", "~#~").where('^', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(BlockList.golem_head))).where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(blockForBody))).where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
	}

	private static BlockPattern getGolemPattern(Block blockForBody) {
		return BlockPatternBuilder.start().aisle("~^~", "###", "~#~").where('^', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(BlockList.golem_head))).where('#', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(blockForBody))).where('~', CachedBlockInfo.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
	}
	
}
