package WGHxPERNAxBEAST.BeastlyCustomization.blocks;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.EntitiesList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GolemHead extends CarvedPumpkinBlock{
	
	public GolemHead(Block.Properties properties) {
	      super(properties);
	      this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	   }
	
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState) {
	      if (oldState.getBlock() != state.getBlock()) {
	         EntitiesList.trySpawnGolems(worldIn, pos);
	      }
	   }
	
}
