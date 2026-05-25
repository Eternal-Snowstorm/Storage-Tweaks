package dev.celestiacraft.storage_tweaks.tags;

import dev.celestiacraft.storage_tweaks.StorageTweaks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class StorageTweaksItemTags {
	public static final TagKey<Item> NO_STACK_UPGRADE = create("no_stack_upgrade");
	public static final TagKey<Item> NO_STORAGE_STACK = create("no_storage_stack");

	private static TagKey<Item> create(String path) {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(StorageTweaks.MODID, path));
	}
}
