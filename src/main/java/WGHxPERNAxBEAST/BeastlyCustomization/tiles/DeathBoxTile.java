package WGHxPERNAxBEAST.BeastlyCustomization.tiles;

import WGHxPERNAxBEAST.BeastlyCustomization.blocks.DeathBoxBlock;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.DeathBoxContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.TileList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class DeathBoxTile extends ChestTileEntity {
	   @SuppressWarnings("unused")
	private NonNullList<ItemStack> chestContents = NonNullList.withSize(45, ItemStack.EMPTY);
	   private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;
	   private int ticksSinceSync;
	   private int ticksCount = 0;
	   private static int playerXP;
	   private static PlayerEntity owner;
	   
	   public DeathBoxTile() {
	      super(TileList.death_box);
	   }

	   /**
	    * Returns the number of slots in the inventory.
	    */
	   @Override
	   public int getSizeInventory() {
	      return 45;
	   }
	   
	   @Override
	   public ITextComponent getDisplayName() {
			return new StringTextComponent(getType().getRegistryName().getPath());
	   }
	   
	   @SuppressWarnings("unused")
	@Override
	   public void tick() {
		   DeathBoxBlock deathBox = (DeathBoxBlock) this.world.getBlockState(this.pos).getBlock();
		   ticksCount++;
		   if (owner == null) {
			   owner = deathBox.getOwner();
		   }
		   if (playerXP <= 0) {
			   playerXP = deathBox.getXP();
		   }
		      int i = this.pos.getX();
		      int j = this.pos.getY();
		      int k = this.pos.getZ();
		      ++this.ticksSinceSync;
		      this.numPlayersUsing = func_213977_a(this.world, this, this.ticksSinceSync, i, j, k, this.numPlayersUsing);
		      this.prevLidAngle = this.lidAngle;
		      float f = 0.1F;
		      if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
		         this.playSound(SoundEvents.BLOCK_CHEST_OPEN);
		      }

		      if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
		         float f1 = this.lidAngle;
		         if (this.numPlayersUsing > 0) {
		            this.lidAngle += 0.1F;
		         } else {
		            this.lidAngle -= 0.1F;
		         }

		         if (this.lidAngle > 1.0F) {
		            this.lidAngle = 1.0F;
		         }

		         float f2 = 0.5F;
		         if (this.lidAngle < 0.5F && f1 >= 0.5F) {
		            this.playSound(SoundEvents.BLOCK_CHEST_CLOSE);
		         }

		         if (this.lidAngle < 0.0F) {
		            this.lidAngle = 0.0F;
		         }
		      }
		    //after a delay for the inventory to be set, delete the block if its empty and closed.
		      if (ticksCount >= 90) {
		    	  if (deathBox.getOwner() == null) {
		    		  deathBox.updateOwner(owner, playerXP);
		    	  }
		    	  if (this.isEmpty() && this.numPlayersUsing == 0) {
			    	  this.world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
			      }
		    	  ticksCount = 0;
		      }

		   }
	   
	   @Override
	   public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
	       if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	          if (this.chestHandler == null) {
	             this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
	          }
	          return this.chestHandler.cast();
	       }
	       return super.getCapability(cap, side);
	   }

	   private net.minecraftforge.items.IItemHandlerModifiable createHandler() {
	      BlockState state = this.getBlockState();
	      if (!(state.getBlock() instanceof DeathBoxBlock)) {
	         return new net.minecraftforge.items.wrapper.InvWrapper(this);
	      }
	      ChestType type = state.get(DeathBoxBlock.TYPE);
	      if (type != ChestType.SINGLE) {
	         BlockPos opos = this.getPos().offset(DeathBoxBlock.getDirectionToAttached(state));
	         BlockState ostate = this.getWorld().getBlockState(opos);
	         if (state.getBlock() == ostate.getBlock()) {
	            ChestType otype = ostate.get(DeathBoxBlock.TYPE);
	            if (otype != ChestType.SINGLE && type != otype && state.get(DeathBoxBlock.FACING) == ostate.get(DeathBoxBlock.FACING)) {
	               TileEntity ote = this.getWorld().getTileEntity(opos);
	               if (ote instanceof DeathBoxTileOrig) {
	                  IInventory top    = type == ChestType.RIGHT ? this : (IInventory)ote;
	                  IInventory bottom = type == ChestType.RIGHT ? (IInventory)ote : this;
	                  return new net.minecraftforge.items.wrapper.CombinedInvWrapper(
	                     new net.minecraftforge.items.wrapper.InvWrapper(top),
	                     new net.minecraftforge.items.wrapper.InvWrapper(bottom));
	               }
	            }
	         }
	      }
	      return new net.minecraftforge.items.wrapper.InvWrapper(this);
	   }
	   
	   private void playSound(SoundEvent soundIn) {
		      ChestType chesttype = this.getBlockState().get(DeathBoxBlock.TYPE);
		      if (chesttype != ChestType.LEFT) {
		         double d0 = (double)this.pos.getX() + 0.5D;
		         double d1 = (double)this.pos.getY() + 0.5D;
		         double d2 = (double)this.pos.getZ() + 0.5D;
		         if (chesttype == ChestType.RIGHT) {
		            Direction direction = DeathBoxBlock.getDirectionToAttached(this.getBlockState());
		            d0 += (double)direction.getXOffset() * 0.5D;
		            d2 += (double)direction.getZOffset() * 0.5D;
		         }

		         this.world.playSound((PlayerEntity)null, d0, d1, d2, soundIn, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
		      }
		   }

	   @Override
	   protected Container createMenu(int id, PlayerInventory player) {
	      return DeathBoxContainer.createGeneric9X5(id, player, this);
	   }

	}