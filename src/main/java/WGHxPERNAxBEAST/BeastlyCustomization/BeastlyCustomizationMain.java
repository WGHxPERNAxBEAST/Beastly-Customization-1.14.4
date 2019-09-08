package WGHxPERNAxBEAST.BeastlyCustomization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import WGHxPERNAxBEAST.BeastlyCustomization.blocks.BatteryBlock;
import WGHxPERNAxBEAST.BeastlyCustomization.blocks.CarbonDustGeneratorBlock;
import WGHxPERNAxBEAST.BeastlyCustomization.blocks.ChickenFactoryBlock;
import WGHxPERNAxBEAST.BeastlyCustomization.blocks.GolemHead;
import WGHxPERNAxBEAST.BeastlyCustomization.client.render.bcRenderRegistry;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.BatteryContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.CarbonDustGeneratorContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.ChickenFactoryContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.items.ItemCustomAxe;
import WGHxPERNAxBEAST.BeastlyCustomization.items.ItemCustomPickaxe;
import WGHxPERNAxBEAST.BeastlyCustomization.items.ItemMegaTool;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ArmorMatList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.BlockList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.EntitiesList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ToolMatList;
import WGHxPERNAxBEAST.BeastlyCustomization.proxies.ClientProxy;
import WGHxPERNAxBEAST.BeastlyCustomization.proxies.IProxy;
import WGHxPERNAxBEAST.BeastlyCustomization.proxies.ServerProxy;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.BatteryTile;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.CarbonDustGeneratorTile;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.ChickenFactoryTile;
import WGHxPERNAxBEAST.BeastlyCustomization.utils.EventHandler;
import WGHxPERNAxBEAST.BeastlyCustomization.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("beastly_customization")
public class BeastlyCustomizationMain {
	
	public static BeastlyCustomizationMain instance;
	public static final String modid = "beastly_customization";
	public static final Logger logger = LogManager.getLogger(modid);
	
	public EventHandler eventHandler;
	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
	
	public static final ItemGroup bcItemGroup = new GroupClass("beastly_customization_inv_group");
	
	public BeastlyCustomizationMain() {
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	//pre-init
	private void setup(final FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register((this.eventHandler = new EventHandler()));
		proxy.init();
		OreGeneration.setupOreGeneration();
		logger.info("Setup method registered.");
	}
	
	//client
	private void clientRegistries(final FMLClientSetupEvent event) {
		bcRenderRegistry.registerEntityRenders();
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
					ItemList.pop_ingot = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_ingot")),
					ItemList.pop_nug = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_nug")),
					ItemList.pop_dust = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_dust")),
					ItemList.pop_stick = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_stick")),
					ItemList.rds_ingot = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_ingot")),
					ItemList.rds_nug = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_nug")),
					ItemList.rds_dust = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_dust")),
					ItemList.rds_stick = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_stick")),
					ItemList.pps_ingot = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_ingot")),
					ItemList.pps_nug = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_nug")),
					ItemList.pps_dust = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_dust")),
					ItemList.pps_stick = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_stick")),
							
					ItemList.carbon_dust = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("carbon_dust")),
					ItemList.brain = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("brain")),
					ItemList.brain_fragment = new Item(new Item.Properties().group(bcItemGroup)).setRegistryName(location("brain_frag")),
					//ItemList.lazer_pow_cell = new LazerPowerCell(new Item.Properties().group(bcItemGroup)).setRegistryName(location("lazer_pow_cell")),
									
					ItemList.bs_axe = new ItemCustomAxe(ToolMatList.bs_mat, 7.5F, 1.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_axe")),
					ItemList.bs_hoe = new HoeItem(ToolMatList.bs_mat, 0.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_hoe")),
					ItemList.bs_pick = new ItemCustomPickaxe(ToolMatList.bs_mat, 2, 1.4F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_pick")),
					ItemList.bs_mtool = new ItemMegaTool(ToolMatList.bs_mat, 2.5F, 1.3F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_mtool")),
					ItemList.bs_shovel = new ShovelItem(ToolMatList.bs_mat, 2.25F, 1.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_shovel")),
					ItemList.bs_sword = new SwordItem(ToolMatList.bs_mat, 5, 1.2F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_sword")),
					//ItemList.bs_lazer = new ItemLazerGun(6000, 2.0F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_lazer")),
					ItemList.azr_axe = new ItemCustomAxe(ToolMatList.azr_mat, 5.0F, 0.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_axe")),
					ItemList.azr_hoe = new HoeItem(ToolMatList.azr_mat, 0.25F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_hoe")),
					ItemList.azr_pick = new ItemCustomPickaxe(ToolMatList.azr_mat, 1, 0.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_pick")),
					ItemList.azr_mtool = new ItemMegaTool(ToolMatList.azr_mat, 1.5F, 0.4F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_mtool")),
					ItemList.azr_shovel = new ShovelItem(ToolMatList.azr_mat, 1.5F, 0.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_shovel")),
					ItemList.azr_sword = new SwordItem(ToolMatList.azr_mat, 3, 0.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_sword")),
					ItemList.pop_axe = new ItemCustomAxe(ToolMatList.pop_mat, 5.0F, 0.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_axe")),
					ItemList.pop_hoe = new HoeItem(ToolMatList.pop_mat, 0.3F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_hoe")),
					ItemList.pop_pick = new ItemCustomPickaxe(ToolMatList.pop_mat, 1, 0.75F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_pick")),
					ItemList.pop_mtool = new ItemMegaTool(ToolMatList.pop_mat, 1.5F, 0.65F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_mtool")),
					ItemList.pop_shovel = new ShovelItem(ToolMatList.pop_mat, 1.5F, 0.75F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_shovel")),
					ItemList.pop_sword = new SwordItem(ToolMatList.pop_mat, 3, 0.75F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_sword")),
					ItemList.rds_axe = new ItemCustomAxe(ToolMatList.rds_mat, 7.5F, 1.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_axe")),
					ItemList.rds_hoe = new HoeItem(ToolMatList.rds_mat, 0.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_hoe")),
					ItemList.rds_pick = new ItemCustomPickaxe(ToolMatList.rds_mat, 2, 1.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_pick")),
					ItemList.rds_mtool = new ItemMegaTool(ToolMatList.rds_mat, 2.5F, 1.4F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_mtool")),
					ItemList.rds_shovel = new ShovelItem(ToolMatList.rds_mat, 2.25F, 1.5F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_shovel")),
					ItemList.rds_sword = new SwordItem(ToolMatList.rds_mat, 5, 1.2F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_sword")),
					ItemList.pps_axe = new ItemCustomAxe(ToolMatList.pps_mat, 11.25F, 1.2F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_axe")),
					ItemList.pps_hoe = new HoeItem(ToolMatList.pps_mat, 1.2F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_hoe")),
					ItemList.pps_pick = new ItemCustomPickaxe(ToolMatList.pps_mat, 3, 1.2F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_pick")),
					ItemList.pps_mtool = new ItemMegaTool(ToolMatList.pps_mat, 4.0F, 1.1F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_mtool")),
					ItemList.pps_shovel = new ShovelItem(ToolMatList.pps_mat, 3.375F, 1.2F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_shovel")),
					ItemList.pps_sword = new SwordItem(ToolMatList.pps_mat, 8, 2.3F, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_sword")),
																			
					ItemList.bs_helmet = new ArmorItem(ArmorMatList.bs_mat, EquipmentSlotType.HEAD, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_helmet")),
					ItemList.bs_chestplate = new ArmorItem(ArmorMatList.bs_mat, EquipmentSlotType.CHEST, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_chestplate")),
					ItemList.bs_leggings = new ArmorItem(ArmorMatList.bs_mat, EquipmentSlotType.LEGS, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_leggings")),
					ItemList.bs_boots = new ArmorItem(ArmorMatList.bs_mat, EquipmentSlotType.FEET, new Item.Properties().group(bcItemGroup)).setRegistryName(location("bs_boots")),		
					ItemList.azr_helmet = new ArmorItem(ArmorMatList.azr_mat, EquipmentSlotType.HEAD, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_helmet")),
					ItemList.azr_chestplate = new ArmorItem(ArmorMatList.azr_mat, EquipmentSlotType.CHEST, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_chestplate")),
					ItemList.azr_leggings = new ArmorItem(ArmorMatList.azr_mat, EquipmentSlotType.LEGS, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_leggings")),
					ItemList.azr_boots = new ArmorItem(ArmorMatList.azr_mat, EquipmentSlotType.FEET, new Item.Properties().group(bcItemGroup)).setRegistryName(location("azr_boots")),
					ItemList.pop_helmet = new ArmorItem(ArmorMatList.pop_mat, EquipmentSlotType.HEAD, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_helmet")),
					ItemList.pop_chestplate = new ArmorItem(ArmorMatList.pop_mat, EquipmentSlotType.CHEST, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_chestplate")),
					ItemList.pop_leggings = new ArmorItem(ArmorMatList.pop_mat, EquipmentSlotType.LEGS, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_leggings")),
					ItemList.pop_boots = new ArmorItem(ArmorMatList.pop_mat, EquipmentSlotType.FEET, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pop_boots")),
					ItemList.rds_helmet = new ArmorItem(ArmorMatList.rds_mat, EquipmentSlotType.HEAD, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_helmet")),
					ItemList.rds_chestplate = new ArmorItem(ArmorMatList.rds_mat, EquipmentSlotType.CHEST, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_chestplate")),
					ItemList.rds_leggings = new ArmorItem(ArmorMatList.rds_mat, EquipmentSlotType.LEGS, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_leggings")),
					ItemList.rds_boots = new ArmorItem(ArmorMatList.rds_mat, EquipmentSlotType.FEET, new Item.Properties().group(bcItemGroup)).setRegistryName(location("rds_boots")),		
					ItemList.pps_helmet = new ArmorItem(ArmorMatList.pps_mat, EquipmentSlotType.HEAD, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_helmet")),
					ItemList.pps_chestplate = new ArmorItem(ArmorMatList.pps_mat, EquipmentSlotType.CHEST, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_chestplate")),
					ItemList.pps_leggings = new ArmorItem(ArmorMatList.pps_mat, EquipmentSlotType.LEGS, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_leggings")),
					ItemList.pps_boots = new ArmorItem(ArmorMatList.pps_mat, EquipmentSlotType.FEET, new Item.Properties().group(bcItemGroup)).setRegistryName(location("pps_boots")),		
																	
					ItemList.blue_steel_block = new BlockItem(BlockList.blue_steel_block, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.blue_steel_block.getRegistryName()),
					ItemList.azr_block = new BlockItem(BlockList.azr_block, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.azr_block.getRegistryName()),
					ItemList.pop_block = new BlockItem(BlockList.pop_block, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.pop_block.getRegistryName()),
					ItemList.rds_block = new BlockItem(BlockList.rds_block, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.rds_block.getRegistryName()),
					ItemList.pps_block = new BlockItem(BlockList.pps_block, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.pps_block.getRegistryName()),
					ItemList.golem_head = new BlockItem(BlockList.golem_head, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.golem_head.getRegistryName()),
					ItemList.chicken_factory = new BlockItem(BlockList.chicken_factory, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.chicken_factory.getRegistryName()),
					ItemList.cd_pow_gener = new BlockItem(BlockList.cd_pow_gener, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.cd_pow_gener.getRegistryName()),
					ItemList.battery = new BlockItem(BlockList.battery, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.battery.getRegistryName()),
																							
					ItemList.carbon_rock = new BlockItem(BlockList.carbon_rock, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.carbon_rock.getRegistryName()),
					ItemList.azr_ore = new BlockItem(BlockList.azr_ore, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.azr_ore.getRegistryName()),
					ItemList.pop_ore = new BlockItem(BlockList.pop_ore, new Item.Properties().group(bcItemGroup)).setRegistryName(BlockList.pop_ore.getRegistryName())
					
			);
			
			EntitiesList.registerEntitySpawnEggs(event);
			
			logger.info("Items registered.");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			event.getRegistry().registerAll (
					
					BlockList.blue_steel_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0F, 7.0F).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("blue_steel_block")),
					BlockList.azr_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("azr_block")),
					BlockList.pop_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("pop_block")),
					BlockList.rds_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0F, 7.0F).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("rds_block")),
					BlockList.pps_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(6.5F, 7.5F).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("pps_block")),
					BlockList.golem_head = new GolemHead(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0F, 7.0F).lightValue(0).sound(SoundType.METAL)).setRegistryName(location("golem_head")),
					BlockList.chicken_factory = new ChickenFactoryBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0F, 7.0F).lightValue(14).sound(SoundType.METAL)).setRegistryName(location("chick_fact")),
					BlockList.cd_pow_gener = new CarbonDustGeneratorBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0F, 7.0F).lightValue(14).sound(SoundType.METAL)).setRegistryName(location("cd_pow_gener")),
					BlockList.battery = new BatteryBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(6.0F, 7.0F).lightValue(14).sound(SoundType.METAL)).setRegistryName(location("bs_battery")),
																								
					BlockList.carbon_rock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).lightValue(0).sound(SoundType.STONE)).setRegistryName(location("carbon_rock")),
					BlockList.azr_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).lightValue(0).sound(SoundType.STONE)).setRegistryName(location("azr_ore")),
					BlockList.pop_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).lightValue(0).sound(SoundType.STONE)).setRegistryName(location("pop_ore"))
					
			);
			logger.info("Blocks registered.");
		}
		
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
			event.getRegistry().registerAll(
					EntitiesList.BS_BABY_GOLEM,
					EntitiesList.BS_ADULT_GOLEM,
					EntitiesList.RDS_BABY_GOLEM,
					EntitiesList.RDS_ADULT_GOLEM,
					EntitiesList.PPS_BABY_GOLEM,
					EntitiesList.PPS_ADULT_GOLEM//,
					//EntitiesList.BS_SKELLY
			);
			EntitiesList.registerEntityWorldSpawns();
		}
		
		@SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().register(TileEntityType.Builder.create(ChickenFactoryTile::new, BlockList.chicken_factory).build(null).setRegistryName("chick_fact"));
            event.getRegistry().register(TileEntityType.Builder.create(CarbonDustGeneratorTile::new, BlockList.cd_pow_gener).build(null).setRegistryName("cd_pow_gener"));
            event.getRegistry().register(TileEntityType.Builder.create(BatteryTile::new, BlockList.battery).build(null).setRegistryName("bs_battery"));
        }

        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new ChickenFactoryContainer(windowId, proxy.getClientWorld(), pos, inv, proxy.getClientPlayer());
            }).setRegistryName("chick_fact"));
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new CarbonDustGeneratorContainer(windowId, proxy.getClientWorld(), pos, inv, proxy.getClientPlayer());
            }).setRegistryName("cd_pow_gener"));
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new BatteryContainer(windowId, proxy.getClientWorld(), pos, inv, proxy.getClientPlayer());
            }).setRegistryName("bs_battery"));
        }
		
	}
	
	public static ResourceLocation location(String name) {
		return new ResourceLocation(modid, name);
	}
}
