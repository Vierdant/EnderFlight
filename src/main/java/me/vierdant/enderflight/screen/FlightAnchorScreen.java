package me.vierdant.enderflight.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import me.vierdant.enderflight.EnderFlight;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FlightAnchorScreen extends HandledScreen<FlightAnchorScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(EnderFlight.MOD_ID, "textures/gui/flight_anchor_gui.png");

    public FlightAnchorScreen(FlightAnchorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        playerInventoryTitleY = playerInventoryTitleY - 15;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderBurnMeter(context, x, y);
    }

    private void renderBurnMeter(DrawContext context, int x, int y) {
        if (handler.isActive()) {
            context.drawTexture(TEXTURE, x + 81, y + 34, 178, 2, 14, 18);
            context.drawTexture(TEXTURE, x + 81, y + 34, 178, 20, 14, handler.getScaledBurnMeter());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
