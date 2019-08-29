package WGHxPERNAxBEAST.BeastlyCustomization.proxies;

import WGHxPERNAxBEAST.BeastlyCustomization.client.gui.ChickenFactoryGui;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ContainerList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(ContainerList.chicken_factory, ChickenFactoryGui::new);
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