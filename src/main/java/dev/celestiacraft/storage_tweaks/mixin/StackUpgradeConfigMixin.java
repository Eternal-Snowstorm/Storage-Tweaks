package dev.celestiacraft.storage_tweaks.mixin;

import dev.celestiacraft.storage_tweaks.compat.storage.StackUpgradeHelper;
import net.minecraft.world.item.Item;
import net.p3pp3rf1y.sophisticatedcore.upgrades.stack.StackUpgradeConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = StackUpgradeConfig.class, remap = false)
public class StackUpgradeConfigMixin {
	@Inject(method = "canStackItem", at = @At("HEAD"), cancellable = true)
	private void storageTweaks$noStackUpgradeOverride(Item item, CallbackInfoReturnable<Boolean> cir) {
		if (StackUpgradeHelper.isNoStackUpgrade(item)) {
			cir.setReturnValue(false);
		}
	}
}
