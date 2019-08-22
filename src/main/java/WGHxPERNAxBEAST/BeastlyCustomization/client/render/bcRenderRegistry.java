package WGHxPERNAxBEAST.BeastlyCustomization.client.render;

import WGHxPERNAxBEAST.BeastlyCustomization.Entities.AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BabyGolem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class bcRenderRegistry {
	
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(BabyGolem.class, new BabyGolemRender.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(AdultGolem.class, new AdultGolemRender.RenderFactory());
	}
}
