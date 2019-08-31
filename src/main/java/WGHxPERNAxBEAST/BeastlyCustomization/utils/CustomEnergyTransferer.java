package WGHxPERNAxBEAST.BeastlyCustomization.utils;

import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class CustomEnergyTransferer {
	public static LazyOptional<IEnergyStorage> takeInPower(LazyOptional<IEnergyStorage> energyIn, World world, BlockPos pos, int maxTransferRate) {
        energyIn.ifPresent(e1 -> {
        	CustomEnergyStorage energy = (CustomEnergyStorage) e1;
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() < energy.getMaxEnergyStored()) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(h -> {
                        	CustomEnergyStorage handler = (CustomEnergyStorage) h;
                            if (handler.canExtract() && energy.getTakePriority() >= handler.getTakePriority()) {
                            	if (energy.getMaxEnergyStored() > energy.getEnergyStored() + maxTransferRate) {
	                            	int sent = handler.extractEnergy(energy.getMaxEnergyStored() - energy.getEnergyStored(), false);
	                            	capacity.addAndGet(-sent);
	                            	energy.addEnergy(sent);
	                            	return capacity.get() < energy.getMaxEnergyStored();
                            	} else {
                            		int sent = handler.extractEnergy(maxTransferRate, false);
	                            	capacity.addAndGet(-sent);
	                            	energy.addEnergy(sent);
	                            	return capacity.get() < energy.getMaxEnergyStored();
                            	}
                            } else {
                            	return true;
                            }
                        }).orElse(true);
                        if (!doContinue) {
                            return;
                        }
                    }
                }
            }
            e1 = energy;
        });
        return energyIn;
    }
	public static LazyOptional<IEnergyStorage> sendOutPower(LazyOptional<IEnergyStorage> energyIn, World world, BlockPos pos, int maxTransferRate) {
		energyIn.ifPresent(e1 -> {
        	CustomEnergyStorage energy = (CustomEnergyStorage) e1;
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() > 0) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                        	if (handler.canReceive() && energy.getTakePriority() <= energy.getSendPriority()) {
                        		if (handler.getMaxEnergyStored() > handler.getEnergyStored() + maxTransferRate) {
	                            	int received = handler.receiveEnergy(Math.min(capacity.get(), handler.getMaxEnergyStored() - handler.getEnergyStored()), false);
                            		capacity.addAndGet(-received);
                            		energy.consumeEnergy(received);
                            		return capacity.get() > 0;
                            	} else {
                            		int received = handler.receiveEnergy(Math.min(capacity.get(), maxTransferRate), false);
                            		capacity.addAndGet(-received);
                            		energy.consumeEnergy(received);
                            		return capacity.get() > 0;
                            	}
                        	} else {
                        		return true;
                        	}
                        }).orElse(true);
                        if (!doContinue) {
                        	return;
                        }
                    }
                }
            }
            e1 = energy;
        });
		return energyIn;
    }
}
