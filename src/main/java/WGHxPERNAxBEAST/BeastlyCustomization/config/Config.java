package WGHxPERNAxBEAST.BeastlyCustomization.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {
	private static final ForgeConfigSpec.Builder server_builder = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.Builder client_builder = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec server_config;
	public static final ForgeConfigSpec client_config;
	
	static {
		OreGenConfig.init(server_builder, client_builder);
		
		server_config = server_builder.build();
		client_config = client_builder.build();
	}
	
	public static void loadConfig(ForgeConfigSpec config, String type, String path) {
		BeastlyCustomizationMain.logger.info("Loading " + type + " Config: " + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		BeastlyCustomizationMain.logger.info("Build " + type + " Config: " + path);
		file.load();
		BeastlyCustomizationMain.logger.info("Loaded " + type + " Config: " + path);
		config.setConfig(file);
	}
}
