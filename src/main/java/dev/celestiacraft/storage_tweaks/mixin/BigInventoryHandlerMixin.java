package dev.celestiacraft.storage_tweaks.mixin;

import com.buuz135.functionalstorage.FunctionalStorage;
import com.buuz135.functionalstorage.inventory.BigInventoryHandler;
import dev.celestiacraft.storage_tweaks.compat.storage.StackUpgradeHelper;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = BigInventoryHandler.class, remap = false)
public abstract class BigInventoryHandlerMixin {
	@Shadow(remap = false)
	@Final
	private FunctionalStorage.DrawerType type;

	@Shadow(remap = false)
	public abstract List<BigInventoryHandler.BigStack> getStoredStacks();

	@Shadow(remap = false)
	public abstract boolean hasDowngrade();

	@Shadow(remap = false)
	public abstract boolean isCreative();

	@Inject(method = "getSlotLimit", at = @At("HEAD"), cancellable = true, remap = false)
	private void storageTweaks$noStackUpgrade(int slot, CallbackInfoReturnable<Integer> cir) {
		if (isCreative()) {
			return;
		}
		if (slot >= type.getSlots()) {
			return;
		}

		ItemStack stored = getStoredStacks().get(slot).getStack();
		if (StackUpgradeHelper.isNoStorageStack(stored)) {
			cir.setReturnValue(1);
			return;
		}
		if (!StackUpgradeHelper.isNoStackUpgrade(stored)) {
			return;
		}

		double stackSize = stored.getMaxStackSize() / 64D;
		int slotAmount = hasDowngrade() ? 64 : type.getSlotAmount();
		cir.setReturnValue((int) Math.floor(slotAmount * stackSize));
	}
}