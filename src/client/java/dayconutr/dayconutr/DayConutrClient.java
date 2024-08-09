package dayconutr.dayconutr;

import dayconutr.dayconutr.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayConutrClient implements ClientModInitializer {
	// Logger thing
	public static final Logger LOGGER = LoggerFactory.getLogger("day-countr");

	// Calls this on start of the mod 
	@Override
	public void onInitializeClient() {
		// Logs some info
		LOGGER.info("[DayConutr]:Hello Fabric world!");
		LOGGER.info("[DayConutr]:Welcome Day Countr to the Mods!");

		// Register HudRender
		HudRenderCallback.EVENT.register((drawContext, tickDelta) -> onHudRender(drawContext));

		// Register AutoConfig
		// AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

		// Read the config
		// ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	}

	// We register this from up there ^
	private void onHudRender(DrawContext drawContext) {
		MinecraftClient MC = MinecraftClient.getInstance();
		if (MC.player != null && MC.world != null) {
			// Get current day and add one to it
			long day = MC.world.getTimeOfDay() / 24000L + 1;

			// Get screen width and height
			int screenWidth = MC.getWindow().getScaledWidth();
			int screenHeight = MC.getWindow().getScaledHeight();

			// Put on left bottom corner
			int x = 12;
			int y = screenHeight - 15;

			// Render the text with the Day on it
			drawContext.drawTextWithShadow(MC.textRenderer, "Day: " + day, x, y, 0xFFFFFF);

		}
	}
}
