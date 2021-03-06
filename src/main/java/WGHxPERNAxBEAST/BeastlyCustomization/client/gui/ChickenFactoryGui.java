package WGHxPERNAxBEAST.BeastlyCustomization.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;

import WGHxPERNAxBEAST.BeastlyCustomization.BeastlyCustomizationMain;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.ChickenFactoryContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChickenFactoryGui extends ContainerScreen<ChickenFactoryContainer> {

    private ResourceLocation GUI = new ResourceLocation(BeastlyCustomizationMain.modid, "textures/gui/chick_fact_gui.png");

    public ChickenFactoryGui(ChickenFactoryContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    	drawCenteredString(Minecraft.getInstance().fontRenderer, "Organic Chicken Factory", 91, 3, 0xffffff);
        drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), 100, 12, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
        //energy bar
        int e = container.getEnergyScaled(43);
        this.blit(this.guiLeft + 10, this.guiTop + 18 + 44 - e, 181, 0, 14, e);
        //left to right arrow
        int c = container.getCounterScaled(17);
        this.blit(this.guiLeft + 82, this.guiTop + 24, 196, 0, c, 17);
    }
}