package WGHxPERNAxBEAST.BeastlyCustomization.client.render;

import WGHxPERNAxBEAST.BeastlyCustomization.Entities.AdultGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.client.models.AdultGolemModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class AdultGolemRender extends LivingRenderer<AdultGolem, AdultGolemModel>{
	
	public AdultGolemRender(EntityRendererManager manager) {
		super(manager, new AdultGolemModel(), 0f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(AdultGolem entity) {
		return entity.getTextureLoc();
	}
	
	public static class RenderFactory implements IRenderFactory<AdultGolem>{

		@Override
		public EntityRenderer<? super AdultGolem> createRenderFor(EntityRendererManager manager) {
			return new AdultGolemRender(manager);
		}
		
	}

}
