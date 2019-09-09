package WGHxPERNAxBEAST.BeastlyCustomization.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DeathBoxContainer extends PlayerInventory{

	public DeathBoxContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(player);
    }
}
