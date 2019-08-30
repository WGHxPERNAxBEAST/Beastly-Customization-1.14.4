package WGHxPERNAxBEAST.BeastlyCustomization.tiles;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import WGHxPERNAxBEAST.BeastlyCustomization.containers.BatteryContainer;
import WGHxPERNAxBEAST.BeastlyCustomization.lists.TileList;
import WGHxPERNAxBEAST.BeastlyCustomization.utils.CustomEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
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

public class BatteryTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider{
	
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
	
	private final int maxEnStorage = 25000;
	private final int maxTransferRate = 60;
	
	private boolean hasEnergy = false;
	
	public BatteryTile() {
		super(TileList.battery);
	}
	
	@Override
    public void tick() {
        if (world.isRemote) {
            return;
        }
        energy.ifPresent(e -> {
    		CustomEnergyStorage e1 = (CustomEnergyStorage) e;
    		if (e1.getEnergyStored() >  0) {
        		hasEnergy = true;
        	}
    		e = e1;
    	});
        BlockState blockState = world.getBlockState(pos);
        if (blockState.get(BlockStateProperties.POWERED) != hasEnergy) {
            world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, hasEnergy), 3);
        }

        sendOutPower();
        takeInPower();
    }

    private void sendOutPower() {
        energy.ifPresent(energy -> {
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() > 0) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                    if (handler.canReceive()) {
                                        int received = handler.receiveEnergy(Math.min(capacity.get(), maxTransferRate), false);
                                        capacity.addAndGet(-received);
                                        ((CustomEnergyStorage) energy).consumeEnergy(received);
                                        markDirty();
                                        return capacity.get() > 0;
                                    } else {
                                        return true;
                                    }
                                }
                        ).orElse(true);
                        if (!doContinue) {
                            return;
                        }
                    }
                }
            }
        });
    }
    
    private void takeInPower() {
        energy.ifPresent(energy -> {
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() < energy.getMaxEnergyStored()) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                    if (handler.canExtract()) {
                                        int sent = handler.extractEnergy(maxTransferRate, false);
                                        capacity.addAndGet(-sent);
                                        ((CustomEnergyStorage) energy).addEnergy(sent);
                                        markDirty();
                                        return capacity.get() < energy.getMaxEnergyStored();
                                    } else {
                                        return true;
                                    }
                                }
                        ).orElse(true);
                        if (!doContinue) {
                            return;
                        }
                    }
                }
            }
        });
    }

	public int getMaxEnergy() {
		return maxEnStorage;
	}
	
	@SuppressWarnings("unchecked")
	@Override
    public void read(CompoundNBT tag) {
        CompoundNBT energyTag = tag.getCompound("energy");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));
        
        super.read(tag);
    }

    @SuppressWarnings("unchecked")
	@Override
    public CompoundNBT write(CompoundNBT tag) {
        energy.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            tag.put("energy", compound);
        });

        return super.write(tag);
    }
	
	private IEnergyStorage createEnergy() {
        return new CustomEnergyStorage(maxEnStorage, maxTransferRate);
    }
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
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
        return new BatteryContainer(i, world, pos, playerInventory, playerEntity);
    }
}
