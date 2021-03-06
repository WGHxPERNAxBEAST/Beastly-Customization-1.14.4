package WGHxPERNAxBEAST.BeastlyCustomization.containers;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.BlockList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ContainerList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import WGHxPERNAxBEAST.BeastlyCustomization.tiles.CarbonDustGeneratorTile;
import WGHxPERNAxBEAST.BeastlyCustomization.utils.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CarbonDustGeneratorContainer extends Container{
	
	private CarbonDustGeneratorTile tileEntity;
    private PlayerEntity playerEntity;
	private IItemHandler playerInventory;

	public CarbonDustGeneratorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ContainerList.cd_pow_gener, windowId);
        tileEntity = (CarbonDustGeneratorTile) world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 82, 26));
        });
        layoutPlayerInventorySlots(10, 70);

        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return getEnergy();
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((CustomEnergyStorage)h).setEnergy(value));
            }
        });
        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return getCounter();
            }

			@Override
			public void set(int counter) {
				tileEntity.setCounter(counter);
			}
        });
    }

	public int getEnergy() {
        return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }
	
	public int getCounter() {
        return tileEntity.getCounter();
    }
	
	public void setCounter(int count) {
        tileEntity.setCounter(count);
    }
	
	public int getCounterScaled(int pixels) {
        int i = tileEntity.getCounter();
        int c = tileEntity.getCounterMax();
        return c != 0 && i != 0 ? i * pixels / c : 0;
    }
	
	public int getEnergyScaled(int pixels) {
        int i = tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
        int c = tileEntity.getMaxEnergy();
        return c != 0 && i != 0 ? i * pixels / c : 0;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, BlockList.cd_pow_gener);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.mergeItemStack(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else {
            	if (stack.getItem() == ItemList.carbon_dust) {
                    if (!this.mergeItemStack(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) {
                    if (!this.mergeItemStack(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }



    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

}
