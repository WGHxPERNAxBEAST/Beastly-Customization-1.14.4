package WGHxPERNAxBEAST.BeastlyCustomization.lists;

import java.util.Set;

import javax.annotation.Nullable;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BS_AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BS_BabyGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BS_Skelly;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BabyGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.PPS_AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.PPS_BabyGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.RDS_AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.RDS_BabyGolem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
	public static EntityType<?extends BS_BabyGolem> BS_BABY_GOLEM = (EntityType<? extends BS_BabyGolem>) EntityType.Builder.create(BS_BabyGolem::new, EntityClassification.CREATURE).build(BeastlyCustomizationMain.modid + ":bs_baby_golem").setRegistryName(BeastlyCustomizationMain.location("bs_baby_golem"));
	@SuppressWarnings("unchecked")
	public static EntityType<?extends BS_AdultGolem> BS_ADULT_GOLEM = (EntityType<? extends BS_AdultGolem>) EntityType.Builder.create(BS_AdultGolem::new, EntityClassification.CREATURE).build(BeastlyCustomizationMain.modid + ":bs_adult_golem").setRegistryName(BeastlyCustomizationMain.location("bs_adult_golem"));
	@SuppressWarnings("unchecked")
	public static EntityType<?extends RDS_BabyGolem> RDS_BABY_GOLEM = (EntityType<? extends RDS_BabyGolem>) EntityType.Builder.create(RDS_BabyGolem::new, EntityClassification.CREATURE).build(BeastlyCustomizationMain.modid + ":rds_baby_golem").setRegistryName(BeastlyCustomizationMain.location("rds_baby_golem"));
	@SuppressWarnings("unchecked")
	public static EntityType<?extends RDS_AdultGolem> RDS_ADULT_GOLEM = (EntityType<? extends RDS_AdultGolem>) EntityType.Builder.create(RDS_AdultGolem::new, EntityClassification.CREATURE).build(BeastlyCustomizationMain.modid + ":rds_adult_golem").setRegistryName(BeastlyCustomizationMain.location("rds_adult_golem"));
	@SuppressWarnings("unchecked")
	public static EntityType<?extends PPS_BabyGolem> PPS_BABY_GOLEM = (EntityType<? extends PPS_BabyGolem>) EntityType.Builder.create(PPS_BabyGolem::new, EntityClassification.CREATURE).build(BeastlyCustomizationMain.modid + ":pps_baby_golem").setRegistryName(BeastlyCustomizationMain.location("pps_baby_golem"));
	@SuppressWarnings("unchecked")
	public static EntityType<?extends PPS_AdultGolem> PPS_ADULT_GOLEM = (EntityType<? extends PPS_AdultGolem>) EntityType.Builder.create(PPS_AdultGolem::new, EntityClassification.CREATURE).build(BeastlyCustomizationMain.modid + ":pps_adult_golem").setRegistryName(BeastlyCustomizationMain.location("pps_adult_golem"));
	@SuppressWarnings("unchecked")
	public static EntityType<?extends BS_Skelly> BS_SKELLY = (EntityType<? extends BS_Skelly>) EntityType.Builder.create(BS_Skelly::new, EntityClassification.CREATURE).build(BeastlyCustomizationMain.modid + ":bs_skelly").setRegistryName(BeastlyCustomizationMain.location("bs_skelly"));
	
	public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				registerEntitySpawnEgg(BS_SKELLY, 0x2800db, 0x4f7ff7, BeastlyCustomizationMain.bcItemGroup, "bs_skelly_egg")
		);
	}
	
	public static void registerEntityWorldSpawns() {
		registerEntityWorldSpawn(BS_SKELLY, Biome.BIOMES);
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
		BlockState underBlockState = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()));
		if (underBlockState == BlockList.blue_steel_block.getDefaultState()) {
			trySpawnGolemSet(worldIn, pos, BS_ADULT_GOLEM, BS_BABY_GOLEM, BlockList.blue_steel_block, 150.0D, 0.35D, 1.5D, 35.0F, 10, "bs_golem");
		} else if (underBlockState == BlockList.rds_block.getDefaultState()) {
			trySpawnGolemSet(worldIn, pos, RDS_ADULT_GOLEM, RDS_BABY_GOLEM, BlockList.rds_block, 145.0D, 0.4D, 1.35D, 38.5F, 8, "rds_golem");
		} else if (underBlockState == BlockList.pps_block.getDefaultState()) {
			trySpawnGolemSet(worldIn, pos, PPS_ADULT_GOLEM, PPS_BABY_GOLEM, BlockList.pps_block, 187.5D, 0.6D, 1.8D, 49.25F, 12, "pps_golem");
		}
	}
	
	private static void trySpawnGolemSet(World worldIn, BlockPos pos, EntityType<? extends AdultGolem> AdultType, EntityType<? extends BabyGolem> babyType, Block bodyBlock, Double adultHealth, Double adultSpeed, Double adultKB_Resist, Float adultTargetingRange, int adultMinAttackDamage, String textureName){
		AdultGolemSpawner(worldIn, pos, AdultType.create(worldIn).setAttributes(AdultType, worldIn, adultHealth, adultSpeed, adultKB_Resist, adultTargetingRange, adultMinAttackDamage, textureName), bodyBlock);
		BabyGolemSpawner(worldIn, pos, babyType.create(worldIn).setAttributes(babyType, worldIn, adultHealth / 5.0D, adultSpeed * 1.175D, adultKB_Resist / 1.2D, adultTargetingRange / 1.5F, (int)(adultMinAttackDamage / 1.5), textureName), bodyBlock);
   }

	private static void BabyGolemSpawner(World worldIn, BlockPos pos, BabyGolem babyGolemEntity, Block bodyBlock) {
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
