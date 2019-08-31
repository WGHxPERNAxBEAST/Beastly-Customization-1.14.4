package WGHxPERNAxBEAST.BeastlyCustomization.tiles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import WGHxPERNAxBEAST.BeastlyCustomization.containers.CarbonDustGeneratorContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.TileList;
import WGHxPERNAxBEAST.BeastlyCustomization.utils.CustomEnergyStorage;
import WGHxPERNAxBEAST.BeastlyCustomization.utils.CustomEnergyTransferer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
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

public class CarbonDustGeneratorTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider{
	
	private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
	
	private int counter = 1000;
	private int counterMax = 80;
	private int maxEnStorage = 4800;
	
	private boolean canAddEnergy = false;
	private boolean canAddTakeDust = false;

	public CarbonDustGeneratorTile() {
		super(TileList.cd_pow_gener);
		energy.ifPresent(e -> {
    		CustomEnergyStorage e1 = (CustomEnergyStorage) e;
    		e1.setSendPriority(2);
    		e1.setTakePriority(0);
    		maxEnStorage = e1.getMaxEnergyStored();
    		e = e1;
    	});
	}
	
	@Override
    public void tick() {
        if (world.isRemote) {
            return;
        }
        if (this.counter >= counterMax) {
        	energy.ifPresent(e -> {
        		CustomEnergyStorage e1 = (CustomEnergyStorage) e;
        		if (e1.getMaxEnergyStored() >= e1.getEnergyStored() + 30) {
        			canAddEnergy = true;
	        	} 
        		e = e1;
        	});
        	handler.ifPresent(h -> {
        		ItemStack stack = h.getStackInSlot(0);
        		if (stack.getItem() == ItemList.carbon_dust) {
        			canAddTakeDust = true;
        		}
        	});
        	if (canAddEnergy == true && canAddTakeDust == true) {
        		this.counter = 0;
        		canAddEnergy = false;
        		canAddTakeDust = false;
        	}
        }
        
        if (this.counter < counterMax) {
        	this.counter++;
	        if (this.counter >= counterMax) {
	        		energy.ifPresent(e -> {
		        		CustomEnergyStorage e1 = (CustomEnergyStorage) e;
		        		if (e1.getMaxEnergyStored() >= e1.getEnergyStored() + 30) {
			        		e1.addEnergy(30);
			        	} else if ((e1.getMaxEnergyStored() > e1.getEnergyStored())) {
			        		e1.addEnergy(e1.getMaxEnergyStored() - e1.getEnergyStored());
		        		}
		        		markDirty();
		        		e = e1;
		        	});
	        		handler.ifPresent(h -> {
	            		h.extractItem(0, 1, false);
	            		markDirty();
	            	});
	        		this.counter++;
	        }
	        markDirty();
		}

        BlockState blockState = world.getBlockState(pos);
        if (blockState.get(BlockStateProperties.POWERED) != (this.counter > 0 && this.counter < counterMax)) {
            world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, (this.counter > 0 && this.counter < counterMax)), 3);
        }

        energy = CustomEnergyTransferer.sendOutPower(energy, world, pos, 40);
    }
	
    public int getTrueCounter() {
		return this.counter;
	}
	
	public int getCounter() {
		if (this.counter > counterMax || this.counter < 0) {
			return 0;
		} else {
			return this.counter;
		}
	}
	
	public void setCounter(int counterIn) {
		this.counter = counterIn;
	}
	
	public int getMaxEnergy() {
		return maxEnStorage;
	}
	
	public int getCounterMax() {
		return counterMax;
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
        CompoundNBT energyTag = tag.getCompound("energy");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));

        this.counter = tag.getInt("counter");
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

        tag.putInt("counter", this.counter);
        return super.write(tag);
    }
	
    private IItemHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() == ItemList.carbon_dust;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (stack.getItem() != ItemList.carbon_dust) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
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
        return new CarbonDustGeneratorContainer(i, world, pos, playerInventory, playerEntity);
    }
}
