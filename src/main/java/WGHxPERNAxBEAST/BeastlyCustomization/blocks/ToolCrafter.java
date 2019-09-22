package WGHxPERNAxBEAST.BeastlyCustomization.blocks;

import WGHxPERNAxBEAST.BeastlyCustomization.containers.ToolCrafterContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ToolCrafter extends Block{
	private static final ITextComponent field_220271_a = new TranslationTextComponent("container.crafting");
	
	public ToolCrafter(Properties properties) {
		super(properties);
	}
	
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	      player.openContainer(state.getContainer(worldIn, pos));
	      player.addStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
	      return true;
	   }

	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
	      return new SimpleNamedContainerProvider((windowId, inv, player) -> {
            return new ToolCrafterContainer(windowId, worldIn, pos, inv, player);
	      }, field_220271_a);
	}

}
