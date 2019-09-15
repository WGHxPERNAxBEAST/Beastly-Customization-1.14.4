package WGHxPERNAxBEAST.BeastlyCustomization.blocks;

import WGHxPERNAxBEAST.BeastlyCustomization.tiles.DeathBoxTile;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class DeathBoxBlock extends ChestBlock {
	 
	   public DeathBoxBlock(Properties p_i48430_1_) {
		   super(p_i48430_1_);
	   }

	   private static PlayerEntity owner;
	   private static int playerXP;

	   /**
	    * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	    */
	   @Override
	   public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
	      if (stack.hasDisplayName()) {
	         TileEntity tileentity = worldIn.getTileEntity(pos);
	         if (tileentity instanceof DeathBoxTile) {
	            ((DeathBoxTile)tileentity).setCustomName(stack.getDisplayName());
	         }
	      }
	      if (placer instanceof PlayerEntity) {
	    	  setBoxOwner((PlayerEntity)placer, false);
	      }
	   }
	   @Override
	   public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
	      if (worldIn.isRemote) {
	         return true;
	      } else {
	    	  if (player.isCreative()) {
	    		     INamedContainerProvider inamedcontainerprovider = this.getContainer(state, worldIn, pos);
			         if (inamedcontainerprovider != null) {
			            player.openContainer(inamedcontainerprovider);
			            player.addStat(this.getOpenStat());
			         }
	    	  } else {
	    		  /*
	    		  if (owner != null) {
	    			  BeastlyCustomizationMain.logger.log(Level.INFO, "\"{} == {}\" evaluates to: {}", player.getName().getString(), owner.getName().getString(), (player.getName().getString().matches(owner.getName().getString())));
	    		  }
	    		  */
	    		  if (owner == null) {
		    		    //BeastlyCustomizationMain.logger.log(Level.INFO, "Death Box @{} has no owner", pos);
		    	  } else if (player.getName().getString().matches(owner.getName().getString())) {
			  			if (player.isSneaking()) {
			    		    //BeastlyCustomizationMain.logger.log(Level.INFO, "Transfering items to {}'s inventory", owner.getName().getString());
				  			player.sendMessage(new StringTextComponent("Transfering items to your inventory."));
				  			transferItemsToPlayer(state, worldIn, pos, player);
			  			}
				        INamedContainerProvider inamedcontainerprovider = this.getContainer(state, worldIn, pos);
				        if (inamedcontainerprovider != null) {
				           player.openContainer(inamedcontainerprovider);
				           player.addStat(this.getOpenStat());
				        }
			  	  } else {
			  			//BeastlyCustomizationMain.logger.log(Level.INFO, "{} can not access {}'s items", player.getName().getString(), owner.getName().getString());
			  			player.sendMessage(new StringTextComponent("You can not access this player's items. They are not yours."));
			  	  }	 
	    	 }
	         return true;
	      }
	   }
	   
	@Override
	public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
		if (owner != null) {
			if (player.isCreative()) {
				return true;
			} else {
				if (player.getName().getString().matches(owner.getName().getString())) {
					return super.canHarvestBlock(state, world, pos, player);
				} else {
					return false;
				}
			}
		} else {
			return super.canHarvestBlock(state, world, pos, player);
		}
	}
	
	@Override
	public boolean canEntityDestroy(BlockState state, IBlockReader world, BlockPos pos, Entity entity) {
		return false;
	}
	
	@Override
	public void dropXpOnBlockBreak(World worldIn, BlockPos pos, int amount) {
		super.dropXpOnBlockBreak(worldIn, pos, playerXP);
		playerXP = 0;
	}
	   
	public static void transferItemsToPlayer(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn) {
		PlayerInventory playerInvIn = playerIn.inventory;
		DeathBoxTile dbInv = (DeathBoxTile) worldIn.getBlockState(pos).getContainer(worldIn, pos);
		setInventoryContense(dbInv, playerInvIn);
		playerIn.giveExperiencePoints(playerXP);
		playerXP = 0;
	}
	
	public static void setBoxContense(DeathBoxTile dbInv, PlayerInventory playerInv) {
		for(int i = 0; i < playerInv.getSizeInventory(); /*- 1;*//*36;*/ i++) {
			ItemStack stack = playerInv.getStackInSlot(i);
			dbInv.setInventorySlotContents(i, stack);
			if (stack != null) {
				playerInv.deleteStack(stack);
			}
		}
	}
	
	private static void setInventoryContense(DeathBoxTile dbInv, PlayerInventory currentInv) {
		for(int i = 0; i < dbInv.getSizeInventory(); /*- 1;*//*36;*/ i++) {
			ItemStack stack = dbInv.getStackInSlot(i);
			ItemStack stack1 = currentInv.getStackInSlot(i);
			currentInv.setInventorySlotContents(i, stack);
			dbInv.setInventorySlotContents(i, stack1);
		}
	}
	
	public void setBoxOwner(PlayerEntity playerIn, Boolean shouldSaveLevels) {
		owner = playerIn;
		if (shouldSaveLevels) {
			int xp = (int)(playerIn.experienceTotal / 1.25);
			if (xp > 0) {
				playerXP = xp;
			} else {
				playerXP = 1;
			}
		}
	}	
	
	public PlayerEntity getOwner() {
		return owner;
	}
	
	public int getXP() {
		return playerXP;
	}
	
	public void updateOwner(PlayerEntity playerIn, int xpIn) {
		owner = playerIn;
		playerXP = xpIn;
	}
	   
}