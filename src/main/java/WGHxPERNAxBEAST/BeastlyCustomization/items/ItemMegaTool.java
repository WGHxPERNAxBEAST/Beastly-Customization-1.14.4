package WGHxPERNAxBEAST.BeastlyCustomization.items;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ItemMegaTool extends ToolItem {

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE, Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER);
	protected static final Map<Block, Block> BLOCK_STRIPPING_MAP = (new Builder<Block, Block>()).put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).build();
	protected static final Map<Block, BlockState> BLOCK_PATHING_MAP = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.getDefaultState()));

	
	public ItemMegaTool(IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder
				.addToolType(ToolType.PICKAXE, tier.getHarvestLevel())
				.addToolType(ToolType.AXE, tier.getHarvestLevel())
				.addToolType(ToolType.SHOVEL, tier.getHarvestLevel())
				);
	}
	
	public boolean canHarvestBlock(BlockState blockIn) {
		int i = this.getTier().getHarvestLevel();
		if (blockIn.getHarvestTool() == net.minecraftforge.common.ToolType.PICKAXE) {
			return i >= blockIn.getHarvestLevel();
		}
		Material material = blockIn.getMaterial();
		return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL || material == Material.SAND || material == Material.CLAY || material == Material.EARTH || material == Material.BAMBOO || material == Material.CACTUS || material == Material.GOURD || material == Material.PISTON || material == Material.SHULKER || material == Material.SNOW || material == Material.SNOW_BLOCK || material == Material.SPONGE || material == Material.TNT || material == Material.WOOD || material == Material.PLANTS || material == Material.TALL_PLANTS;
	}
	
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.ROCK && material != Material.IRON && material != Material.ANVIL && material != Material.SAND && material != Material.CLAY && material != Material.EARTH && material != Material.BAMBOO && material != Material.CACTUS && material != Material.GOURD && material != Material.PISTON && material != Material.SHULKER && material != Material.SNOW && material != Material.SNOW_BLOCK && material != Material.SPONGE && material != Material.TNT && material != Material.WOOD && material != Material.PLANTS && material != Material.TALL_PLANTS ? super.getDestroySpeed(stack, state) : this.efficiency;
	}
	
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		BlockState blockstate = world.getBlockState(blockpos);
		Block block = BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
		BlockState blockstate1 = BLOCK_PATHING_MAP.get(world.getBlockState(blockpos).getBlock());
		PlayerEntity playerentity = context.getPlayer();
		if (!context.isPlacerSneaking()) {
			if (block != null) {
				world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote) {
					world.setBlockState(blockpos, block.getDefaultState().with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)), 11);
					if (playerentity != null) {
						context.getItem().damageItem(1, playerentity, (p_220040_1_) -> {
							p_220040_1_.sendBreakAnimation(context.getHand());
						});
					}
				}
				return ActionResultType.SUCCESS;
			} else if (context.getFace() != Direction.DOWN && world.getBlockState(blockpos.up()).isAir(world, blockpos.up()) && blockstate1 != null) {
				world.playSound(playerentity, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote) {
					world.setBlockState(blockpos, blockstate1, 11);
					if (playerentity != null) {
						context.getItem().damageItem(1, playerentity, (p_220041_1_) -> {
							p_220041_1_.sendBreakAnimation(context.getHand());
						});
					}
					return ActionResultType.SUCCESS;
				} else {
					return ActionResultType.PASS;
				}
			} else {
				return ActionResultType.PASS;
			}
		} else {
			return ActionResultType.PASS;
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		if (playerIn.isSneaking()) {
			ItemStack stack = playerIn.getHeldItem(handIn);
			CompoundNBT nbt = stack.getTag(); 
			if(nbt == null) nbt = new CompoundNBT();
			if(nbt.contains("active"))
			{
				if(!nbt.getBoolean("active")) {nbt.putBoolean("active", true); nbt.putString("target", "");}
				else nbt.putBoolean("active", false);
			}
			else
			{
				nbt.putBoolean("active", true);
				nbt.putString("target", "");
			}
			stack.setTag(nbt);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		if(!worldIn.isRemote)
		{
			
			CompoundNBT nbt = stack.getTag();
			if(nbt == null) nbt = new CompoundNBT();
			if(nbt.contains("active") && nbt.contains("target") && nbt.contains("xTarget") && nbt.contains("yTarget") && nbt.contains("zTarget"))
			{
				if(nbt.getBoolean("active") && nbt.getString("target") != "")
				{
					BlockPos.MutableBlockPos pos1 = new BlockPos.MutableBlockPos(0, 0, 0);
					
					ArrayList<Integer> xP = new ArrayList<>();
					ArrayList<Integer> yP = new ArrayList<>();
					ArrayList<Integer> zP = new ArrayList<>();
					
					int[] xT = nbt.getIntArray("xTarget");
					int[] yT = nbt.getIntArray("yTarget");
					int[] zT = nbt.getIntArray("zTarget");
					
					if(xT.length == yT.length && xT.length == zT.length)
					{
						for(int i = 0; i < xT.length; i++)
						{
							pos1.setPos(xT[i], yT[i], zT[i]);
							if(worldIn.getBlockState(pos1).getBlock().getTranslationKey() == nbt.getString("target"))
							{
								if(entityIn instanceof PlayerEntity)
								{
									PlayerEntity playerIn = (PlayerEntity) entityIn;
									if(playerIn.canHarvestBlock(worldIn.getBlockState(pos1)))
									{
										worldIn.destroyBlock(pos1, true);
									}
									else
									{
										worldIn.setBlockState(pos1, Blocks.AIR.getDefaultState(), 3);
									}
								}
								else
								{
									worldIn.setBlockState(pos1, Blocks.AIR.getDefaultState(), 3);
								}
								if(stack.getMaxDamage() - stack.getDamage() < 0)
								{
									if(entityIn instanceof LivingEntity)
									{
									LivingEntity LE = (LivingEntity) entityIn;
								      stack.damageItem(2, LE, (p_220039_0_) -> {
								          p_220039_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
								       });
									}
									else
									{
										stack.shrink(1);
									}
								}
								else
								{
									stack.attemptDamageItem(1, new Random(), null);
								}
								
								BlockPos[] tbps = {pos1.add(1, 0, 0), pos1.add(1, 1, 0), pos1.add(1, 1, 1), pos1.add(-1, 0, 0), pos1.add(-1, -1, 0), pos1.add(-1, -1, -1), pos1.add(0, 1, 0), pos1.add(0, -1, 0), pos1.add(0, 0, 1), pos1.add(0, 0, -1)};
								for(int j = 0; j < tbps.length; j++)
								{
									if(nbt.contains("oXT") && nbt.contains("oYT") && nbt.contains("oZT"))
									{
										if(worldIn.getBlockState(tbps[j]).getBlock().getTranslationKey() == nbt.getString("target"))
										{
											if(	(nbt.getInt("oXT") - tbps[j].getX() <= 16 && nbt.getInt("oXT") - tbps[j].getX() >= -16) && (nbt.getInt("oZT") - tbps[j].getZ() <= 16 && nbt.getInt("oZT") - tbps[j].getZ() >= -16))
											{
												xP.add(tbps[j].getX());
												yP.add(tbps[j].getY());
												zP.add(tbps[j].getZ());
											}
										}
									}
								}
							}
						}
					}
					
					nbt.putIntArray("xTarget", xP);
					nbt.putIntArray("yTarget", yP);
					nbt.putIntArray("zTarget", zP);
					if(xP.size() == 0) nbt.putString("target", "");
					stack.setTag(nbt);
				}
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) 
	{
		if(entityLiving instanceof PlayerEntity)
		{
			PlayerEntity playerIn = (PlayerEntity) entityLiving;
			if(playerIn.canHarvestBlock(state))
			{
				CompoundNBT nbt = stack.getTag();
				if(nbt == null) nbt = new CompoundNBT();
				if(nbt.contains("active"))
				{
					if(nbt.getBoolean("active"))
					{
						nbt.putString("target", state.getBlock().getTranslationKey());
						nbt.putInt("oXT", pos.getX());
						nbt.putInt("oYT", pos.getY());
						nbt.putInt("oZT", pos.getZ());
						
						ArrayList<Integer> xP = new ArrayList<>();
						ArrayList<Integer> yP = new ArrayList<>();
						ArrayList<Integer> zP = new ArrayList<>();
						
						BlockPos[] tbps = {pos.add(1, 0, 0), pos.add(1, 1, 0), pos.add(1, 1, 1), pos.add(-1, 0, 0), pos.add(-1, -1, 0), pos.add(-1, -1, -1), pos.add(0, 1, 0), pos.add(0, -1, 0), pos.add(0, 0, 1), pos.add(0, 0, -1)};
						for(int j = 0; j < tbps.length; j++)
						{
							if(worldIn.getBlockState(tbps[j]).getBlock().getTranslationKey() == nbt.getString("target"))
							{
								xP.add(tbps[j].getX());
								yP.add(tbps[j].getY());
								zP.add(tbps[j].getZ());
							}
						}
						
						nbt.putIntArray("xTarget", xP);
						nbt.putIntArray("yTarget", yP);
						nbt.putIntArray("zTarget", zP);
					}
				}
				stack.setTag(nbt);
			}
		}
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}
	
	
	
}
