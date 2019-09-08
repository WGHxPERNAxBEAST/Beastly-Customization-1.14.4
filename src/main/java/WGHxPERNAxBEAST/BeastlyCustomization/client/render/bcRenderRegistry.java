package WGHxPERNAxBEAST.BeastlyCustomization.client.render;

import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BS_AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BS_BabyGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BS_Skelly;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.PPS_AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.PPS_BabyGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.RDS_AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.Entities.RDS_BabyGolem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class bcRenderRegistry {
	
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(BS_BabyGolem.class, new BabyGolemRender.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(BS_AdultGolem.class, new AdultGolemRender.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(RDS_BabyGolem.class, new BabyGolemRender.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(RDS_AdultGolem.class, new AdultGolemRender.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(PPS_BabyGolem.class, new BabyGolemRender.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(PPS_AdultGolem.class, new AdultGolemRender.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(BS_Skelly.class, new MetalSkellyRenderer.RenderFactory());
	}
}
