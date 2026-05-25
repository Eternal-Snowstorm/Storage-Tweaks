package dev.celestiacraft.storage_tweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.celestiacraft.storage_tweaks.compat.storage.StackUpgradeHelper;
import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = StorageContainerMenuBase.class, remap = false)
public abstract class StorageContainerMenuBaseMixin {
	@ModifyExpressionValue(
			method = "mergeItemStack(Lnet/minecraft/world/item/ItemStack;IIZZZ)Lnet/minecraft/world/item/ItemStack;",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/inventory/Slot;getMaxStackSize()I",
					remap = true
			)
	)
	private int storageTweaks$capSlotLimitForNoStorageStack(int original, @Local(argsOnly = true, ordinal = 0) ItemStack quickMovedStack) {
		if (StackUpgradeHelper.isNoStorageStack(quickMovedStack)) {
			return Math.min(original, 1);
		}
		return original;
	}
}
