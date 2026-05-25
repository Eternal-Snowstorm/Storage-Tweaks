package dev.celestiacraft.storage_tweaks;

import dev.celestiacraft.storage_tweaks.config.main.CommonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(StorageTweaks.MODID)
public class StorageTweaks {
	public static final String MODID = "storage_tweaks";
	public static final String NAME = "Storage Tweaks";
	public static final Logger LOGGER = LogManager.getLogger(NAME);

	public static ResourceLocation loadResource(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}

	public StorageTweaks(FMLJavaModLoadingContext context) {
		context.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "nebula/storage_tweaks/common.toml");
	}
}
