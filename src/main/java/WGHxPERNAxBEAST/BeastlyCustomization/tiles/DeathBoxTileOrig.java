package WGHxPERNAxBEAST.BeastlyCustomization.tiles;

import WGHxPERNAxBEAST.BeastlyCustomization.blocks.DeathBoxBlock;
import WGHxPERNAxBEAST.BeastlyCustomization.containers.DeathBoxContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.TileList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DeathBoxTileOrig extends LockableLootTileEntity implements IChestLid, ITickableTileEntity {
	   private NonNullList<ItemStack> chestContents = NonNullList.withSize(45, ItemStack.EMPTY);
	   protected float lidAngle;
	   protected float prevLidAngle;
	   protected int numPlayersUsing;
	   private int ticksSinceSync;
	   private int ticksCount = 0;
	   private static int playerXP;
	   private static PlayerEntity owner;
	   private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;
	   
	   public DeathBoxTileOrig() {
	      super(TileList.death_box);
	   }

	   /**
	    * Returns the number of slots in the inventory.
	    */
	   public int getSizeInventory() {
	      return 45;
	   }

	   public boolean isEmpty() {
	      for(ItemStack itemstack : this.chestContents) {
	         if (!itemstack.isEmpty()) {
	            return false;
	         }
	      }

	      return true;
	   }

	   protected ITextComponent getDefaultName() {
	      return new TranslationTextComponent("container.chest");
	   }

	   public void read(CompoundNBT compound) {
	      super.read(compound);
	      this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
	      if (!this.checkLootAndRead(compound)) {
	         ItemStackHelper.loadAllItems(compound, this.chestContents);
	      }

	   }

	   public CompoundNBT write(CompoundNBT compound) {
	      super.write(compound);
	      if (!this.checkLootAndWrite(compound)) {
	         ItemStackHelper.saveAllItems(compound, this.chestContents);
	      }

	      return compound;
	   }

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

	   public static int func_213977_a(World p_213977_0_, LockableTileEntity p_213977_1_, int p_213977_2_, int p_213977_3_, int p_213977_4_, int p_213977_5_, int p_213977_6_) {
	      if (!p_213977_0_.isRemote && p_213977_6_ != 0 && (p_213977_2_ + p_213977_3_ + p_213977_4_ + p_213977_5_) % 200 == 0) {
	         p_213977_6_ = func_213976_a(p_213977_0_, p_213977_1_, p_213977_3_, p_213977_4_, p_213977_5_);
	      }

	      return p_213977_6_;
	   }

	   public static int func_213976_a(World p_213976_0_, LockableTileEntity p_213976_1_, int p_213976_2_, int p_213976_3_, int p_213976_4_) {
	      int i = 0;
	      @SuppressWarnings("unused")
		float f = 5.0F;

	      for(PlayerEntity playerentity : p_213976_0_.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((double)((float)p_213976_2_ - 5.0F), (double)((float)p_213976_3_ - 5.0F), (double)((float)p_213976_4_ - 5.0F), (double)((float)(p_213976_2_ + 1) + 5.0F), (double)((float)(p_213976_3_ + 1) + 5.0F), (double)((float)(p_213976_4_ + 1) + 5.0F)))) {
	         if (playerentity.openContainer instanceof DeathBoxContainer) {
	            IInventory iinventory = ((DeathBoxContainer)playerentity.openContainer).getLowerChestInventory();
	            if (iinventory == p_213976_1_ || iinventory instanceof DoubleSidedInventory && ((DoubleSidedInventory)iinventory).isPartOfLargeChest(p_213976_1_)) {
	               ++i;
	            }
	         }
	      }

	      return i;
	   }

	   /**
	    * See {@link Block#eventReceived} for more information. This must return true serverside before it is called
	    * clientside.
	    */
	   public boolean receiveClientEvent(int id, int type) {
	      if (id == 1) {
	         this.numPlayersUsing = type;
	         return true;
	      } else {
	         return super.receiveClientEvent(id, type);
	      }
	   }

	   public void openInventory(PlayerEntity player) {
	      if (!player.isSpectator()) {
	         if (this.numPlayersUsing < 0) {
	            this.numPlayersUsing = 0;
	         }

	         ++this.numPlayersUsing;
	         this.onOpenOrClose();
	      }

	   }

	   public void closeInventory(PlayerEntity player) {
	      if (!player.isSpectator()) {
	         --this.numPlayersUsing;
	         this.onOpenOrClose();
	      }

	   }

	   protected void onOpenOrClose() {
	      Block block = this.getBlockState().getBlock();
	      if (block instanceof DeathBoxBlock) {
	         this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
	         this.world.notifyNeighborsOfStateChange(this.pos, block);
	      }

	   }

	   protected NonNullList<ItemStack> getItems() {
	      return this.chestContents;
	   }

	   protected void setItems(NonNullList<ItemStack> itemsIn) {
	      this.chestContents = itemsIn;
	   }

	   public static int getPlayersUsing(IBlockReader reader, BlockPos posIn) {
	      BlockState blockstate = reader.getBlockState(posIn);
	      if (blockstate.hasTileEntity()) {
	         TileEntity tileentity = reader.getTileEntity(posIn);
	         if (tileentity instanceof DeathBoxTileOrig) {
	            return ((DeathBoxTileOrig)tileentity).numPlayersUsing;
	         }
	      }

	      return 0;
	   }

	   public static void swapContents(DeathBoxTileOrig chest, DeathBoxTileOrig otherDeathBox) {
	      NonNullList<ItemStack> nonnulllist = chest.getItems();
	      chest.setItems(otherDeathBox.getItems());
	      otherDeathBox.setItems(nonnulllist);
	   }

	   protected Container createMenu(int id, PlayerInventory player) {
	      return DeathBoxContainer.createGeneric9X5(id, player, this);
	   }

	   @Override
	   public void updateContainingBlockInfo() {
	      super.updateContainingBlockInfo();
	      if (this.chestHandler != null) {
	         this.chestHandler.invalidate();
	         this.chestHandler = null;
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

	   /**
	    * invalidates a tile entity
	    */
	   @Override
	   public void remove() {
	      super.remove();
	      if (chestHandler != null)
	        chestHandler.invalidate();
	   }

		@OnlyIn(Dist.CLIENT)
		public float getLidAngle(float partialTicks) {
			return 0;
		}
	}