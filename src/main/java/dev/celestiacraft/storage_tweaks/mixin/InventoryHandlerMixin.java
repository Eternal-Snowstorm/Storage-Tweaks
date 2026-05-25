package dev.celestiacraft.storage_tweaks.mixin;

import dev.celestiacraft.storage_tweaks.compat.storage.StackUpgradeHelper;
import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = InventoryHandler.class, remap = false)
public abstract class InventoryHandlerMixin {
	@Inject(method = "getBaseStackLimit", at = @At("HEAD"), cancellable = true, remap = false)
	private void storageTweaks$forceStackOneBase(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
		if (StackUpgradeHelper.isNoStorageStack(stack)) {
			cir.setReturnValue(1);
		}
	}

	@Inject(method = "getStackLimit", at = @At("HEAD"), cancellable = true, remap = false)
	private void storageTweaks$forceStackOneSlot(int slot, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
		if (StackUpgradeHelper.isNoStorageStack(stack)) {
			cir.setReturnValue(1);
		}
	}
}
