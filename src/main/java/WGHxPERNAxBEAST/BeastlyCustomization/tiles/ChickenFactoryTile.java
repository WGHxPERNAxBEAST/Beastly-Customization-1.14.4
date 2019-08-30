package WGHxPERNAxBEAST.BeastlyCustomization.tiles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import WGHxPERNAxBEAST.BeastlyCustomization.containers.ChickenFactoryContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.TileList;
import WGHxPERNAxBEAST.BeastlyCustomization.utils.CustomEnergyStorage;
import WGHxPERNAxBEAST.BeastlyCustomization.utils.CustomEnergyTransferer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class ChickenFactoryTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider{
	
	private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
	
	private int counter = 1000;
	private int counterMax = 90;
	private int maxEnStorage = 500;
	
	private boolean canConsumeEnergy = false;
	private boolean canConsumeEgg = false;

	public ChickenFactoryTile() {
		super(TileList.chicken_factory);
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
		if (world.isRemote) {
			return;
		}
		if (this.counter >= counterMax) {
			
			energy.ifPresent(e -> {
        		CustomEnergyStorage e1 = (CustomEnergyStorage) e;
        		if (e1.getEnergyStored() >=  25 && canConsumeEnergy == false) {
	        		canConsumeEnergy = true;
	        	}
        		e = e1;
        	});
			handler.ifPresent(h -> {
	        	ItemStack eggStack = h.getStackInSlot(0);
	        	if (eggStack.getItem() == Items.EGG && (h.getStackInSlot(1).getCount() < h.getStackInSlot(1).getMaxStackSize() || h.getStackInSlot(1) == null) && canConsumeEgg == false) {
	        		canConsumeEgg = true;
	        	}
	        });
			if (canConsumeEgg == true && canConsumeEnergy == true) {
				this.counter = 0;
				canConsumeEnergy = false;
				canConsumeEgg = false;
			}
        }
        
		if (this.counter < counterMax) {
			this.counter++;
	        if (this.counter >= counterMax) {
	        	energy.ifPresent(e -> ((CustomEnergyStorage) e).consumeEnergy(25));
	        	markDirty();
	        	handler.ifPresent(h -> {
		        	ItemStack chickStack = new ItemStack(Items.COOKED_CHICKEN);
		        	chickStack.setCount(1);
		        	chickStack.setDisplayName(new StringTextComponent("Organic Chicken!"));
		        	h.insertItem(1, chickStack, false);
		        	h.extractItem(0, 1, false);
		        	markDirty();
		        });
	        }
	        markDirty();
		}
		BlockState blockState = world.getBlockState(pos);
        if (blockState.get(BlockStateProperties.POWERED) != (this.counter > 0 && this.counter < counterMax + 10)) {
            world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, (this.counter > 0 && this.counter < counterMax + 10)), 3);
        }
        energy = CustomEnergyTransferer.takeInPower(energy, world, pos, 40);
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
        return new ItemStackHandler(2) {

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0) {
                	return stack.getItem() == Items.EGG;
                } else if (slot == 1) {
                	return stack.getItem() == Items.COOKED_CHICKEN;
                }
                else {
                	return false;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            	if (slot == 0) {
	            	if (stack.getItem() != Items.EGG) {
	                    return stack;
	                }
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
        return new ChickenFactoryContainer(i, world, pos, playerInventory, playerEntity);
    }
}
