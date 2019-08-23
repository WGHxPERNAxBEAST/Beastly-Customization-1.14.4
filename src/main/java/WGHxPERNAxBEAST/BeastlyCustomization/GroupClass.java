package WGHxPERNAxBEAST.BeastlyCustomization;

import WGHxPERNAxBEAST.BeastlyCustomization.lists.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GroupClass extends ItemGroup{
	
	public GroupClass(String label) {
		super(label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ItemList.golem_head);
	}
	
}
