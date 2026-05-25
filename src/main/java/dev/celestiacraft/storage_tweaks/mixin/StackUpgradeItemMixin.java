package dev.celestiacraft.storage_tweaks.mixin;

import dev.celestiacraft.storage_tweaks.compat.storage.StackUpgradeHelper;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.stack.StackUpgradeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = StackUpgradeItem.class, remap = false)
public abstract class StackUpgradeItemMixin {
	@Inject(
			method = "getInventorySlotLimit(Lnet/p3pp3rf1y/sophisticatedcore/api/IStorageWrapper;D)I",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private static void storageTweaks$additiveStackUpgrade(IStorageWrapper storageWrapper, double skipMultiplier, CallbackInfoReturnable<Integer> cir) {
		if (!StackUpgradeHelper.isAdditiveStackUpgrade()) {
			return;
		}

		double base = storageWrapper.getBaseStackSizeMultiplier();
		double sum = 0;
		boolean multiplierSkipped = false;
		for (StackUpgradeItem.Wrapper stackWrapper : storageWrapper.getUpgradeHandler().getTypeWrappers(StackUpgradeItem.TYPE)) {
			if (!multiplierSkipped && stackWrapper.getStackSizeMultiplier() == skipMultiplier) {
				multiplierSkipped = true;
				continue;
			}
			sum += stackWrapper.getStackSizeMultiplier();
		}

		double multiplier = sum > 0 ? sum : base;
		if (Integer.MAX_VALUE / 64D < multiplier) {
			cir.setReturnValue(Integer.MAX_VALUE);
		} else {
			cir.setReturnValue((int) (multiplier * 64));
		}
	}
}
