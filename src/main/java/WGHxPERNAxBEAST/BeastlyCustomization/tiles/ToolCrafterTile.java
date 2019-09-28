package WGHxPERNAxBEAST.BeastlyCustomization.tiles;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import WGHxPERNAxBEAST.BeastlyCustomization.containers.ToolCrafterContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.TileList;
import WGHxPERNAxBEAST.BeastlyCustomization.utils.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ToolCrafterTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider{
	
	private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
	
	private static final Set<Item> AcceptableItemsOut = Sets.newHashSet(ItemList.azr_chestplate, ItemList.azr_helmet, ItemList.azr_hoe, ItemList.azr_axe, ItemList.azr_boots, ItemList.azr_leggings, ItemList.azr_pick, ItemList.azr_shovel, ItemList.azr_sword, ItemList.bs_chestplate, ItemList.bs_helmet, ItemList.bs_hoe, ItemList.bs_axe, ItemList.bs_boots, ItemList.bs_leggings, ItemList.bs_pick, ItemList.bs_shovel, ItemList.bs_sword, ItemList.rds_chestplate, ItemList.rds_helmet, ItemList.rds_hoe, ItemList.rds_axe, ItemList.rds_boots, ItemList.rds_leggings, ItemList.rds_pick, ItemList.rds_shovel, ItemList.rds_sword, ItemList.pop_chestplate, ItemList.pop_helmet, ItemList.pop_hoe, ItemList.pop_axe, ItemList.pop_boots, ItemList.pop_leggings, ItemList.pop_pick, ItemList.pop_shovel, ItemList.pop_sword, ItemList.pps_chestplate, ItemList.pps_helmet, ItemList.pps_hoe, ItemList.pps_axe, ItemList.pps_boots, ItemList.pps_leggings, ItemList.pps_pick, ItemList.pps_shovel, ItemList.pps_sword);
	
	private int maxEnStorage = 500;

	public ToolCrafterTile() {
		super(TileList.tool_crafter);
		energy.ifPresent(e -> {
    		CustomEnergyStorage e1 = (CustomEnergyStorage) e;
    		e1.setSendPriority(0);
    		e1.setTakePriority(2);
    		maxEnStorage = e1.getMaxEnergyStored();
    		e = e1;
    	});
	}
	
	@Override
	public void tick() {
		return;
	}
	
	public int getMaxEnergy() {
		return maxEnStorage;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
        CompoundNBT energyTag = tag.getCompound("energy");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));
        super.read(tag);
    }

    @SuppressWarnings("unchecked")
	@Override
    public CompoundNBT write(CompoundNBT tag) {
        handler.ifPresent(h -> {
			CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            tag.put("inv", compound);
        });
        energy.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            tag.put("energy", compound);
        });
        return super.write(tag);
    }
	
	private IItemHandler createHandler() {
        return new ItemStackHandler(21) {

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot < 21 && slot > 0) {
                	return true;
                } else if (slot == 0) {
                	for (Item item:AcceptableItemsOut) {
                		if (stack.getItem() == item) {
                			return true;
                		}
                	}
                	return false;
                } else {
                	return false;
                }
            }
        };
    }
	
	private IEnergyStorage createEnergy() {
        return new CustomEnergyStorage(maxEnStorage, 40);
    }
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return handler.cast();
		}
	    if (cap == CapabilityEnergy.ENERGY) {
	        return energy.cast();
	    }
	    return super.getCapability(cap, side);
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
	
	@Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ToolCrafterContainer(i, world, pos, playerInventory, playerEntity);
    }
}
