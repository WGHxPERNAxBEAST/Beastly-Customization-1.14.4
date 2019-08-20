package WGHxPERNAxBEAST.BeastlyCustomization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import WGHxPERNAxBEAST.BeastlyCustomization.items.ItemCustomAxe;
import WGHxPERNAxBEAST.BeastlyCustomization.items.ItemCustomPickaxe;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ArmorMatList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.BlockList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ToolMatList;
import WGHxPERNAxBEAST.BeastlyCustomization.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("beastly_customization")
public class BeastlyCustomizationMain {
	
	public static BeastlyCustomizationMain instance;
	public static final String modid = "beastly_customization";
	public static final Logger logger = LogManager.getLogger(modid);
	
	public static final ItemGroup bcItemGroup = new GroupClass("beastly_customization_inv_group", ItemList.carbon_dust);
	
	public BeastlyCustomizationMain() {
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	//pre-init
	private void setup(final FMLCommonSetupEvent event) {
		logger.info("Setup method registered.");
		OreGeneration.setupOreGeneration();
	}
	
	//client
	private void clientRegistries(final FMLClientSetupEvent event) {
		logger.info("clientRegistries method registered.");
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll (
					ItemList.blue_steel_ingot = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("blue_steel_ingot")),
					ItemList.blue_steel_nug = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("blue_steel_nug")),
					ItemList.bs_dust = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_dust")),
					ItemList.bs_stick = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_stick")),
					ItemList.azr_ingot = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_ingot")),
					ItemList.azr_nug = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_nug")),
					ItemList.azr_dust = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_dust")),
					ItemList.azr_stick = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_stick")),
					
					ItemList.carbon_dust = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("carbon_dust")),
					
					ItemList.bs_axe = new ItemCustomAxe(ToolMatList.bs_mat, 7.5F, -4.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_axe")),
					ItemList.bs_hoe = new HoeItem(ToolMatList.bs_mat, -0.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_hoe")),
					ItemList.bs_pick = new ItemCustomPickaxe(ToolMatList.bs_mat, 2, -4.2F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_pick")),
					ItemList.bs_shovel = new ShovelItem(ToolMatList.bs_mat, 2.25F, -4.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_shovel")),
					ItemList.bs_sword = new SwordItem(ToolMatList.bs_mat, 5, -3.6F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_sword")),
					ItemList.azr_axe = new ItemCustomAxe(ToolMatList.azr_mat, 5.0F, -3.0F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_axe")),
					ItemList.azr_hoe = new HoeItem(ToolMatList.azr_mat, 0.0F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_hoe")),
					ItemList.azr_pick = new ItemCustomPickaxe(ToolMatList.azr_mat, 1, -2.8F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_pick")),
					ItemList.azr_shovel = new ShovelItem(ToolMatList.azr_mat, 1.5F, -3.0F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_shovel")),
					ItemList.azr_sword = new SwordItem(ToolMatList.azr_mat, 3, -2.4F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_sword")),
									
					ItemList.bs_helmet = new ArmorItem(ArmorMatList.bs_mat, EquipmentSlotType.HEAD, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_helmet")),
					ItemList.bs_chestplate = new ArmorItem(ArmorMatList.bs_mat, EquipmentSlotType.CHEST, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_chestplate")),
					ItemList.bs_leggings = new ArmorItem(ArmorMatList.bs_mat, EquipmentSlotType.LEGS, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_leggings")),
					ItemList.bs_boots = new ArmorItem(ArmorMatList.bs_mat, EquipmentSlotType.FEET, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_boots")),		
					ItemList.azr_helmet = new ArmorItem(ArmorMatList.azr_mat, EquipmentSlotType.HEAD, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_helmet")),
					ItemList.azr_chestplate = new ArmorItem(ArmorMatList.azr_mat, EquipmentSlotType.CHEST, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_chestplate")),
					ItemList.azr_leggings = new ArmorItem(ArmorMatList.azr_mat, EquipmentSlotType.LEGS, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_leggings")),
					ItemList.azr_boots = new ArmorItem(ArmorMatList.azr_mat, EquipmentSlotType.FEET, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_boots")),
									
					ItemList.blue_steel_block = new BlockItem(BlockList.blue_steel_block, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.blue_steel_block.getRegistryName()),
					ItemList.azr_block = new BlockItem(BlockList.azr_block, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.azr_block.getRegistryName()),
							
					ItemList.carbon_rock = new BlockItem(BlockList.carbon_rock, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.carbon_rock.getRegistryName()),
					ItemList.azr_ore = new BlockItem(BlockList.azr_ore, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.azr_ore.getRegistryName())
					
			);
			
			logger.info("Items registered.");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			event.getRegistry().registerAll (
					
					BlockList.blue_steel_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("blue_steel_block")),
					BlockList.azr_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("azr_block")),
							
					BlockList.carbon_rock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).lightValue(0).sound(SoundType.STONE)).setRegistryName(location("carbon_rock")),
					BlockList.azr_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).lightValue(0).sound(SoundType.STONE)).setRegistryName(location("azr_ore"))
					
			);
			logger.info("Blocks registered.");
		}
		
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
			event.getRegistry().registerAll(
				
			);
		}
		
	}
	
	public static ResourceLocation location(String name) {
		return new ResourceLocation(modid, name);
	}
}
