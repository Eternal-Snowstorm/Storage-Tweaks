package dev.celestiacraft.storage_tweaks.compat;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.LoadingModList;

public final class ModsLoaded {
	public static final String SOPHISTICATED_CORE = "sophisticatedcore";
	public static final String SOPHISTICATED_STORAGE = "sophisticatedstorage";
	public static final String SOPHISTICATED_BACKPACKS = "sophisticatedbackpacks";

	private ModsLoaded() {
	}

	public static boolean isSophisticatedCoreLoaded() {
		return isModLoaded(SOPHISTICATED_CORE);
	}

	public static boolean isSophisticatedStorageLoaded() {
		return isModLoaded(SOPHISTICATED_STORAGE);
	}

	public static boolean isSophisticatedBackpacksLoaded() {
		return isModLoaded(SOPHISTICATED_BACKPACKS);
	}

	public static boolean isModLoaded(String modId) {
		ModList modList = ModList.get();
		if (modList != null) {
			return modList.isLoaded(modId);
		}
		LoadingModList loading = LoadingModList.get();
		return loading != null && loading.getModFileById(modId) != null;
	}
}
