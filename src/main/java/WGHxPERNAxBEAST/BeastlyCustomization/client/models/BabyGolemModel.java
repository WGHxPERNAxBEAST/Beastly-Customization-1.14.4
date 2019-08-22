package WGHxPERNAxBEAST.BeastlyCustomization.client.models;

import com.mojang.blaze3d.platform.GlStateManager;

import WGHxPERNAxBEAST.BeastlyCustomization.Entities.BabyGolem;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class BabyGolemModel extends EntityModel<BabyGolem> {
    public RendererModel Chest;
    public RendererModel Waist;
    public RendererModel LeftArm;
    public RendererModel RightArm;
    public RendererModel RightLeg;
    public RendererModel Head;
    public RendererModel Nose;
    public RendererModel LeftLeg;

    public BabyGolemModel() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Waist = new RendererModel(this, 0, 70);
        this.Waist.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.Waist.addBox(-4.5F, 40.5F, -3.0F, 9, 5, 6, 0.5F);
        this.RightLeg = new RendererModel(this, 37, 0);
        this.RightLeg.setRotationPoint(-3.7F, 11.0F, 0.0F);
        this.RightLeg.addBox(0.0F, 10.0F, -3.0F, 6, 16, 5, 0.0F);
        this.Head = new RendererModel(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -7.0F, -2.0F);
        this.Head.addBox(-4.0F, 18.0F, -4.0F, 8, 10, 8, 0.0F);
        this.Chest = new RendererModel(this, 0, 40);
        this.Chest.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.Chest.addBox(-9.0F, 28.0F, -6.0F, 18, 12, 11, 0.0F);
        this.Nose = new RendererModel(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -7.0F, -2.0F);
        this.Nose.addBox(-1.0F, 25.0F, -6.1F, 2, 4, 2, 0.0F);
        this.RightArm = new RendererModel(this, 60, 21);
        this.RightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.RightArm.addBox(-13.0F, 28.0F, -3.0F, 4, 30, 6, 0.0F);
        this.LeftArm = new RendererModel(this, 60, 58);
        this.LeftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.LeftArm.addBox(9.0F, 28.0F, -3.0F, 4, 30, 6, 0.0F);
        this.LeftLeg = new RendererModel(this, 60, 0);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(5.0F, 11.0F, 0.0F);
        this.LeftLeg.addBox(-8.2F, 10.0F, -3.0F, 6, 16, 5, 0.0F);
    }

    @Override
    public void render(BabyGolem entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Waist.offsetX, this.Waist.offsetY, this.Waist.offsetZ);
        GlStateManager.translatef(this.Waist.rotationPointX * f5, this.Waist.rotationPointY * f5, this.Waist.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Waist.offsetX, -this.Waist.offsetY, -this.Waist.offsetZ);
        GlStateManager.translatef(-this.Waist.rotationPointX * f5, -this.Waist.rotationPointY * f5, -this.Waist.rotationPointZ * f5);
        this.Waist.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.RightLeg.offsetX, this.RightLeg.offsetY, this.RightLeg.offsetZ);
        GlStateManager.translatef(this.RightLeg.rotationPointX * f5, this.RightLeg.rotationPointY * f5, this.RightLeg.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.RightLeg.offsetX, -this.RightLeg.offsetY, -this.RightLeg.offsetZ);
        GlStateManager.translatef(-this.RightLeg.rotationPointX * f5, -this.RightLeg.rotationPointY * f5, -this.RightLeg.rotationPointZ * f5);
        this.RightLeg.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Head.offsetX, this.Head.offsetY, this.Head.offsetZ);
        GlStateManager.translatef(this.Head.rotationPointX * f5, this.Head.rotationPointY * f5, this.Head.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Head.offsetX, -this.Head.offsetY, -this.Head.offsetZ);
        GlStateManager.translatef(-this.Head.rotationPointX * f5, -this.Head.rotationPointY * f5, -this.Head.rotationPointZ * f5);
        this.Head.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Chest.offsetX, this.Chest.offsetY, this.Chest.offsetZ);
        GlStateManager.translatef(this.Chest.rotationPointX * f5, this.Chest.rotationPointY * f5, this.Chest.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Chest.offsetX, -this.Chest.offsetY, -this.Chest.offsetZ);
        GlStateManager.translatef(-this.Chest.rotationPointX * f5, -this.Chest.rotationPointY * f5, -this.Chest.rotationPointZ * f5);
        this.Chest.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Nose.offsetX, this.Nose.offsetY, this.Nose.offsetZ);
        GlStateManager.translatef(this.Nose.rotationPointX * f5, this.Nose.rotationPointY * f5, this.Nose.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Nose.offsetX, -this.Nose.offsetY, -this.Nose.offsetZ);
        GlStateManager.translatef(-this.Nose.rotationPointX * f5, -this.Nose.rotationPointY * f5, -this.Nose.rotationPointZ * f5);
        this.Nose.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.RightArm.offsetX, this.RightArm.offsetY, this.RightArm.offsetZ);
        GlStateManager.translatef(this.RightArm.rotationPointX * f5, this.RightArm.rotationPointY * f5, this.RightArm.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.RightArm.offsetX, -this.RightArm.offsetY, -this.RightArm.offsetZ);
        GlStateManager.translatef(-this.RightArm.rotationPointX * f5, -this.RightArm.rotationPointY * f5, -this.RightArm.rotationPointZ * f5);
        this.RightArm.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.LeftArm.offsetX, this.LeftArm.offsetY, this.LeftArm.offsetZ);
        GlStateManager.translatef(this.LeftArm.rotationPointX * f5, this.LeftArm.rotationPointY * f5, this.LeftArm.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.LeftArm.offsetX, -this.LeftArm.offsetY, -this.LeftArm.offsetZ);
        GlStateManager.translatef(-this.LeftArm.rotationPointX * f5, -this.LeftArm.rotationPointY * f5, -this.LeftArm.rotationPointZ * f5);
        this.LeftArm.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.LeftLeg.offsetX, this.LeftLeg.offsetY, this.LeftLeg.offsetZ);
        GlStateManager.translatef(this.LeftLeg.rotationPointX * f5, this.LeftLeg.rotationPointY * f5, this.LeftLeg.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.LeftLeg.offsetX, -this.LeftLeg.offsetY, -this.LeftLeg.offsetZ);
        GlStateManager.translatef(-this.LeftLeg.rotationPointX * f5, -this.LeftLeg.rotationPointY * f5, -this.LeftLeg.rotationPointZ * f5);
        this.LeftLeg.render(f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}


