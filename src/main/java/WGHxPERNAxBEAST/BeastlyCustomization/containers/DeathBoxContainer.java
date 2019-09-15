package WGHxPERNAxBEAST.BeastlyCustomization.containers;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.ContainerList;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.ContainerType;

public class DeathBoxContainer extends ChestContainer {

	   private DeathBoxContainer(ContainerType<?> type, int id, PlayerInventory player, int rows) {
	      this(type, id, player, new Inventory(9 * rows), rows);
	   }

	   public static DeathBoxContainer createGeneric9X5(int id, PlayerInventory player) {
	      return new DeathBoxContainer(ContainerType.GENERIC_9X5, id, player, 5);
	   }
	   
	   public static DeathBoxContainer createGeneric9X10(int id, PlayerInventory player) {
		  return new DeathBoxContainer(ContainerList.GENERIC_9X10, id, player, 10);
	   }

	   public static DeathBoxContainer createGeneric9X5(int id, PlayerInventory player, IInventory blockEntity) {
	      return new DeathBoxContainer(ContainerType.GENERIC_9X5, id, player, blockEntity, 5);
	   }

	   public static DeathBoxContainer createGeneric9X10(int id, PlayerInventory player, IInventory blockEntity) {
	      return new DeathBoxContainer(ContainerList.GENERIC_9X10, id, player, blockEntity, 10);
	   }

	   public DeathBoxContainer(ContainerType<?> type, int id, PlayerInventory playerInventoryIn, IInventory p_i50092_4_, int rows) {
	      super(type, id, playerInventoryIn, p_i50092_4_, rows);
	   }
}