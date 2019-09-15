package WGHxPERNAxBEAST.BeastlyCustomization.proxies;

import WGHxPERNAxBEAST.BeastlyCustomization.client.gui.BatteryGui;
import WGHxPERNAxBEAST.BeastlyCustomization.client.gui.CarbonDustGeneratorGui;
import WGHxPERNAxBEAST.BeastlyCustomization.client.gui.ChickenFactoryGui;
import WGHxPERNAxBEAST.BeastlyCustomization.client.render.tiles.BcTileEntityRenderer;
import WGHxPERNAxBEAST.BeastlyCustomization.client.render.tiles.BcTileEntityRendererDispatcher;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ContainerList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(ContainerList.chicken_factory, ChickenFactoryGui::new);
        ScreenManager.registerFactory(ContainerList.cd_pow_gener, CarbonDustGeneratorGui::new);
        ScreenManager.registerFactory(ContainerList.battery, BatteryGui::new);
    }
    
    public static synchronized <T extends TileEntity> void bindTileEntitySpecialRenderer(Class<T> tileEntityClass, BcTileEntityRenderer<? super T> specialRenderer)
    {
        BcTileEntityRendererDispatcher.instance.setSpecialRenderer(tileEntityClass, specialRenderer);
        specialRenderer.setRendererDispatcher(BcTileEntityRendererDispatcher.instance);
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}