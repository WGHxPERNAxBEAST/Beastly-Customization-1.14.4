package WGHxPERNAxBEAST.BeastlyCustomization.client.models;

import WGHxPERNAxBEAST.BeastlyCustomization.Entities.MetalSkelleton;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class MetalSkellyModel extends EntityModel<MetalSkelleton> {
	
	public RendererModel bipedHead;
	   public RendererModel bipedHeadwear;
	   public RendererModel bipedBody;
	   public RendererModel bipedRightArm;
	   public RendererModel bipedLeftArm;
	   public RendererModel bipedRightLeg;
	   public RendererModel bipedLeftLeg;
	   public BipedModel.ArmPose leftArmPose = BipedModel.ArmPose.EMPTY;
	   public BipedModel.ArmPose rightArmPose = BipedModel.ArmPose.EMPTY;
	   public boolean isSneak;
	   public float swimAnimation;
	
	public MetalSkellyModel() {
	      this(0.0F, false);
	   }

	   public MetalSkellyModel(float modelSize, boolean p_i46303_2_) {
	      super();
	      if (!p_i46303_2_) {
	         this.bipedRightArm = new RendererModel(this, 40, 16);
	         this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
	         this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
	         this.bipedLeftArm = new RendererModel(this, 40, 16);
	         this.bipedLeftArm.mirror = true;
	         this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
	         this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
	         this.bipedRightLeg = new RendererModel(this, 0, 16);
	         this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
	         this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
	         this.bipedLeftLeg = new RendererModel(this, 0, 16);
	         this.bipedLeftLeg.mirror = true;
	         this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
	         this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
	      }

	   }

	   public void setLivingAnimations(MetalSkelleton entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
	      this.rightArmPose = BipedModel.ArmPose.EMPTY;
	      this.leftArmPose = BipedModel.ArmPose.EMPTY;
	      ItemStack itemstack = entityIn.getHeldItem(Hand.MAIN_HAND);
	      if (itemstack.getItem() instanceof net.minecraft.item.BowItem && entityIn.isAggressive()) {
	         if (entityIn.getPrimaryHand() == HandSide.RIGHT) {
	            this.rightArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
	         } else {
	            this.leftArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
	         }
	      }

	      super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
	   }

	   public void setRotationAngles(MetalSkelleton entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
	      super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	      ItemStack itemstack = entityIn.getHeldItemMainhand();
	      if (entityIn.isAggressive() && (itemstack.isEmpty() || !(itemstack.getItem() instanceof net.minecraft.item.BowItem))) {
	         float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
	         float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
	         this.bipedRightArm.rotateAngleZ = 0.0F;
	         this.bipedLeftArm.rotateAngleZ = 0.0F;
	         this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
	         this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
	         this.bipedRightArm.rotateAngleX = (-(float)Math.PI / 2F);
	         this.bipedLeftArm.rotateAngleX = (-(float)Math.PI / 2F);
	         this.bipedRightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
	         this.bipedLeftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
	         this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
	         this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
	         this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	         this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	      }

	   }

	   public void postRenderArm(float scale, HandSide side) {
	      float f = side == HandSide.RIGHT ? 1.0F : -1.0F;
	      RendererModel renderermodel = this.getArmForSide(side);
	      renderermodel.rotationPointX += f;
	      renderermodel.postRender(scale);
	      renderermodel.rotationPointX -= f;
	   }
	   
	   protected RendererModel getArmForSide(HandSide side) {
		      return side == HandSide.LEFT ? this.bipedLeftArm : this.bipedRightArm;
		   }
	}