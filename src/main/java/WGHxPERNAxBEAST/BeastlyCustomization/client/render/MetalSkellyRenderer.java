package WGHxPERNAxBEAST.BeastlyCustomization.client.render;

import WGHxPERNAxBEAST.BeastlyCustomization.Entities.MetalSkelleton;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class MetalSkellyRenderer extends LivingRenderer<MetalSkelleton, SkeletonModel<MetalSkelleton>>{
	
	public MetalSkellyRenderer(EntityRendererManager manager) {
		super(manager, new SkeletonModel<MetalSkelleton>(), 0f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(MetalSkelleton entity) {
		return entity.getTextureLoc();
	}
	
	public static class RenderFactory implements IRenderFactory<MetalSkelleton>{

		@Override
		public EntityRenderer<? super MetalSkelleton> createRenderFor(EntityRendererManager manager) {
			return new MetalSkellyRenderer(manager);
		}
		
	}

}
