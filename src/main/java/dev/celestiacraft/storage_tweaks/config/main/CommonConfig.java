package dev.celestiacraft.storage_tweaks.config.main;

import dev.celestiacraft.storage_tweaks.config.common.DrawerUpgradeConfig;
import dev.celestiacraft.storage_tweaks.config.common.StackStorageConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final StackStorageConfig STACK_STORAGE;
	public static final DrawerUpgradeConfig DRAWER_UPGRADE;

	static {
		BUILDER.comment("All settings below will only take effect after restarting the server or client.")
				.push("general");

		STACK_STORAGE = new StackStorageConfig(BUILDER);
		DRAWER_UPGRADE = new DrawerUpgradeConfig(BUILDER);

		BUILDER.pop();

		SPEC = BUILDER.build();
	}
}