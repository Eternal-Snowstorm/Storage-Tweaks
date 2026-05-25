package dev.celestiacraft.storage_tweaks.mixin;

import com.buuz135.functionalstorage.FunctionalStorage;
import com.buuz135.functionalstorage.inventory.BigInventoryHandler;
import com.buuz135.functionalstorage.inventory.item.DrawerStackItemHandler;
import dev.celestiacraft.storage_tweaks.compat.storage.StackUpgradeHelper;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = DrawerStackItemHandler.class, remap = false)
public abstract class DrawerStackItemHandlerMixin {
	@Shadow(remap = false)
	private FunctionalStorage.DrawerType type;

	@Shadow(remap = false)
	private boolean downgrade;

	@Shadow(remap = false)
	public abstract List<BigInventoryHandler.BigStack> getStoredStacks();

	@Inject(method = "getSlotLimit", at = @At("HEAD"), cancellable = true, remap = false)
	private void storageTweaks$noStackUpgrade(int slot, CallbackInfoReturnable<Integer> cir) {
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

		int slotAmount = downgrade ? 64 : type.getSlotAmount();
		cir.setReturnValue((int) Math.min(Integer.MAX_VALUE, slotAmount));
	}
}
