package WGHxPERNAxBEAST.BeastlyCustomization.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OreGenConfig {

	public static ForgeConfigSpec.BooleanValue gen_carbon_rock;
	public static ForgeConfigSpec.IntValue carbon_rock_chance;
	
	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		server.comment("Ore-Gen Config");
		gen_carbon_rock = server
				.comment("If false, Rich Carbonic Rock will not spawn.")
				.define("OreGenConfig.gen_carbon_rock", true);
		carbon_rock_chance = server
				.comment("Max number of ore veins of Rich Carbonic Rock which can spawn per chunk in the overworld.")
				.defineInRange("OreGenConfig.carbon_rock_chance", 4, 1, 15);
	}
}
