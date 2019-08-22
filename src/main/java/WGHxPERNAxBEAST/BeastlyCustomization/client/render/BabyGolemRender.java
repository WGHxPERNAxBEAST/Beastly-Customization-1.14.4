package WGHxPERNAxBEAST.BeastlyCustomization.client.render;

import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BabyGolem;
import WGHxPERNAxBEAST.BeastlyCustomization.client.models.BabyGolemModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BabyGolemRender extends LivingRenderer<BabyGolem, BabyGolemModel>{

	public BabyGolemRender(EntityRendererManager manager) {
		super(manager, new BabyGolemModel(), 0f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(BabyGolem entity) {
		return entity.getTextureLoc();
	}
	
	public static class RenderFactory implements IRenderFactory<BabyGolem>{

		@Override
		public EntityRenderer<? super BabyGolem> createRenderFor(EntityRendererManager manager) {
			return new BabyGolemRender(manager);
		}
		
	}

}
