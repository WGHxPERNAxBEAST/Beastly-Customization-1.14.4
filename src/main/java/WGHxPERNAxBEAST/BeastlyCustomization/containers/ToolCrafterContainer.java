package WGHxPERNAxBEAST.BeastlyCustomization.containers;

import java.util.Optional;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.BlockList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ContainerList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.IRecipeTypeList;
import WGHxPERNAxBEAST.BeastlyCustomization.utils.IToolCrafterRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ToolCrafterContainer extends RecipeBookContainer<CraftingInventory> {
	   private final CraftingInventory mainInv = new CraftingInventory(this, 4, 5);
	   private final CraftResultInventory outputInv = new CraftResultInventory();
	   private final IWorldPosCallable iWorldPos;
	   private final PlayerEntity player;
	   
	   public ToolCrafterContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
	      super(ContainerList.tool_crafter, windowId);
	      this.iWorldPos = IWorldPosCallable.of(world, pos);
	      this.player = player;
	      this.addSlot(new CraftingResultSlot(player, this.mainInv, this.outputInv, 0, 124, 35));

	      for(int i = 0; i < 5; ++i) {
	         for(int j = 0; j < 4; ++j) {
	            this.addSlot(new Slot(this.mainInv, j + i * 3, 16 + j * 18, 7 + i * 18));
	         }
	      }

	      for(int k = 0; k < 3; ++k) {
	         for(int i1 = 0; i1 < 9; ++i1) {
	            this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
	         }
	      }

	      for(int l = 0; l < 9; ++l) {
	         this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
	      }

	   }

	   protected static void craft(int windowIdIn, World worldIn, PlayerEntity playerIn, CraftingInventory craftInvIn, CraftResultInventory craftResultInvIn) {
	      if (!worldIn.isRemote) {
	         ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)playerIn;
	         ItemStack itemstack = ItemStack.EMPTY;
	         Optional<IToolCrafterRecipe> optional = worldIn.getServer().getRecipeManager().getRecipe(IRecipeTypeList.TOOL_CRAFTING, craftInvIn, worldIn);
	         if (optional.isPresent()) {
	        	 IToolCrafterRecipe icraftingrecipe = optional.get();
	            if (craftResultInvIn.canUseRecipe(worldIn, serverplayerentity, icraftingrecipe)) {
	               itemstack = icraftingrecipe.getCraftingResult(craftInvIn);
	            }
	         }

	         craftResultInvIn.setInventorySlotContents(0, itemstack);
	         serverplayerentity.connection.sendPacket(new SSetSlotPacket(windowIdIn, 0, itemstack));
	      }
	   }

	   /**
	    * Callback for when the crafting matrix is changed.
	    */
	   public void onCraftMatrixChanged(IInventory inventoryIn) {
	      this.iWorldPos.consume((p_217069_1_, p_217069_2_) -> {
	    	  craft(this.windowId, p_217069_1_, this.player, this.mainInv, this.outputInv);
	      });
	   }

	   public void func_201771_a(RecipeItemHelper p_201771_1_) {
	      this.mainInv.fillStackedContents(p_201771_1_);
	   }

	   public void clear() {
	      this.mainInv.clear();
	      this.outputInv.clear();
	   }

	   public boolean matches(IRecipe<? super CraftingInventory> recipeIn) {
		      return recipeIn.matches(this.mainInv, this.player.world);
	   }

	   /**
	    * Called when the container is closed.
	    */
	   public void onContainerClosed(PlayerEntity playerIn) {
	      super.onContainerClosed(playerIn);
	      this.iWorldPos.consume((p_217068_2_, p_217068_3_) -> {
	         this.clearContainer(playerIn, p_217068_2_, this.mainInv);
	      });
	   }

	   /**
	    * Determines whether supplied player can use this container
	    */
	   @Override
	    public boolean canInteractWith(PlayerEntity playerIn) {
	        return isWithinUsableDistance(this.iWorldPos, this.player, BlockList.tool_crafter);
	    }

	   /**
	    * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	    * inventory and the other inventory(s).
	    */
	   public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
	      ItemStack itemstack = ItemStack.EMPTY;
	      Slot slot = this.inventorySlots.get(index);
	      if (slot != null && slot.getHasStack()) {
	         ItemStack itemstack1 = slot.getStack();
	         itemstack = itemstack1.copy();
	         if (index == 0) {
	            this.iWorldPos.consume((p_217067_2_, p_217067_3_) -> {
	               itemstack1.getItem().onCreated(itemstack1, p_217067_2_, playerIn);
	            });
	            if (!this.mergeItemStack(itemstack1, 21, 57, true)) {
	               return ItemStack.EMPTY;
	            }

	            slot.onSlotChange(itemstack1, itemstack);
	         } else if (index >= 21 && index < 37) {
	            if (!this.mergeItemStack(itemstack1, 37, 57, false)) {
	               return ItemStack.EMPTY;
	            }
	         } else if (index >= 37 && index < 57) {
	            if (!this.mergeItemStack(itemstack1, 21, 37, false)) {
	               return ItemStack.EMPTY;
	            }
	         } else if (!this.mergeItemStack(itemstack1, 21, 46, false)) {
	            return ItemStack.EMPTY;
	         }

	         if (itemstack1.isEmpty()) {
	            slot.putStack(ItemStack.EMPTY);
	         } else {
	            slot.onSlotChanged();
	         }

	         if (itemstack1.getCount() == itemstack.getCount()) {
	            return ItemStack.EMPTY;
	         }

	         ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
	         if (index == 0) {
	            playerIn.dropItem(itemstack2, false);
	         }
	      }

	      return itemstack;
	   }

	   /**
	    * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is
	    * null for the initial slot that was double-clicked.
	    */
	   public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
	      return slotIn.inventory != this.outputInv && super.canMergeSlot(stack, slotIn);
	   }

	   public int getOutputSlot() {
	      return 0;
	   }

	   public int getWidth() {
	      return this.mainInv.getWidth();
	   }

	   public int getHeight() {
	      return this.mainInv.getHeight();
	   }

	   @OnlyIn(Dist.CLIENT)
	   public int getSize() {
	      return 21;
	   }
	}