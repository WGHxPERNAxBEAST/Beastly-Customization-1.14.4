package WGHxPERNAxBEAST.BeastlyCustomization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
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
	
	public BeastlyCustomizationMain() {
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	//pre-init
	private void setup(final FMLCommonSetupEvent event) {
		logger.info("Setup method registered.");
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
					
					
					
			);
			
			logger.info("Items registered.");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			event.getRegistry().registerAll (
			
			);
			logger.info("Blocks registered.");
		}
		
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
			event.getRegistry().registerAll(
				
			);
		}
		
		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
			event.getRegistry().registerAll(
					
			);
		}
		
	}
	
	public static ResourceLocation location(String name) {
		return new ResourceLocation(modid, name);
	}
}
