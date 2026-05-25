package dev.celestiacraft.storage_tweaks.compat.storage;

import dev.celestiacraft.storage_tweaks.config.common.StackStorageConfig;
import dev.celestiacraft.storage_tweaks.config.main.CommonConfig;
import dev.celestiacraft.storage_tweaks.tags.StorageTweaksItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

public final class StackUpgradeHelper {
	private StackUpgradeHelper() {
	}

	public static boolean isNoStackUpgrade(ItemStack stack) {
		return isEnabled(StackStorageConfig.NO_STACK_UPGRADE_ENABLED) && !stack.isEmpty() && stack.is(StorageTweaksItemTags.NO_STACK_UPGRADE);
	}

	public static boolean isNoStackUpgrade(Item item) {
		return isEnabled(StackStorageConfig.NO_STACK_UPGRADE_ENABLED) && hasTag(item, StorageTweaksItemTags.NO_STACK_UPGRADE);
	}

	public static boolean isNoStorageStack(ItemStack stack) {
		return isEnabled(StackStorageConfig.NO_STORAGE_STACK_ENABLED) && !stack.isEmpty() && stack.is(StorageTweaksItemTags.NO_STORAGE_STACK);
	}

	public static boolean isNoStorageStack(Item item) {
		return isEnabled(StackStorageConfig.NO_STORAGE_STACK_ENABLED) && hasTag(item, StorageTweaksItemTags.NO_STORAGE_STACK);
	}

	private static boolean isEnabled(ForgeConfigSpec.BooleanValue value) {
		return value == null || !CommonConfig.SPEC.isLoaded() || value.get();
	}

	private static boolean hasTag(Item item, TagKey<Item> tag) {
		return item != null && BuiltInRegistries.ITEM.wrapAsHolder(item).is(tag);
	}
}
