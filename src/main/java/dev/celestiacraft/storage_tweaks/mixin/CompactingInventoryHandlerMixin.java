package dev.celestiacraft.storage_tweaks.mixin;

import com.buuz135.functionalstorage.inventory.CompactingInventoryHandler;
import com.buuz135.functionalstorage.util.CompactingUtil;
import dev.celestiacraft.storage_tweaks.compat.storage.StackUpgradeHelper;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = CompactingInventoryHandler.class, remap = false)
public abstract class CompactingInventoryHandlerMixin {
	@Shadow(remap = false)
	public abstract List<CompactingUtil.Result> getResultList();

	@Shadow(remap = false)
	public abstract int getSlotLimitBase(int slot);

	@Shadow(remap = false)
	public abstract boolean isCreative();

	@Inject(method = "getSlotLimit", at = @At("HEAD"), cancellable = true, remap = false)
	private void storageTweaks$noStackUpgrade(int slot, CallbackInfoReturnable<Integer> cir) {
		if (isCreative()) {
			return;
		}

		List<CompactingUtil.Result> results = getResultList();
		if (slot >= results.size()) {
			return;
		}

		ItemStack result = results.get(slot).getResult();
		if (StackUpgradeHelper.isNoStorageStack(result)) {
			cir.setReturnValue(1);
			return;
		}
		if (!StackUpgradeHelper.isNoStackUpgrade(result)) {
			return;
		}

		cir.setReturnValue(getSlotLimitBase(slot));
	}
}
