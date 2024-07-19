package dayconutr.dayconutr;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayConutrClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("day-countr");

	@Override
	public void onInitializeClient() {
		LOGGER.info("[DayConutr]:Hello Fabric world!");
		LOGGER.info("[DayConutr]:Welcome Day Countr to the Mods!");

		HudRenderCallback.EVENT.register((drawContext, tickDelta) -> onHudRender(drawContext));
	}

	private void onHudRender(DrawContext drawContext) {
		MinecraftClient MC = MinecraftClient.getInstance();
		if (MC.player != null && MC.world != null) {
			// Get current day
			long day = MC.world.getTimeOfDay() / 24000L + 1;

			// Get screen width and height
			int screenWidth = MC.getWindow().getScaledWidth();
			int screenHeight = MC.getWindow().getScaledHeight();

			// Put on left bottom corner
			int x = 10;
			int y = screenHeight - 35;

			// Render the text with the Day on it
			drawContext.drawTextWithShadow(MC.textRenderer, "Day: " + day, x, y, 0xFFFFFF);
		}
	}
}
