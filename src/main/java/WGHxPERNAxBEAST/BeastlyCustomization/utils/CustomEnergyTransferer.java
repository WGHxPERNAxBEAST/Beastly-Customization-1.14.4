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
	public static void takeInPower(LazyOptional<IEnergyStorage> energyIn, World world, BlockPos pos) {
        energyIn.ifPresent(energy -> {
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() < energy.getMaxEnergyStored()) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                    if (handler.canExtract()) {
                                        int sent = handler.extractEnergy(40, false);
                                        capacity.addAndGet(-sent);
                                        ((CustomEnergyStorage) energy).addEnergy(sent);
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
	public static void sendOutPower(LazyOptional<IEnergyStorage> energyIn, World world, BlockPos pos) {
        energyIn.ifPresent(energy -> {
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() > 0) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                    if (handler.canReceive()) {
                                        int received = handler.receiveEnergy(Math.min(capacity.get(), 40), false);
                                        capacity.addAndGet(-received);
                                        ((CustomEnergyStorage) energy).consumeEnergy(received);
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
}
