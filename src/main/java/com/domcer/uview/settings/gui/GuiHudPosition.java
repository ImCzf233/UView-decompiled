// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.input.Keyboard
 */
package com.domcer.uview.settings.gui;

import com.domcer.uview.module.impl.game.Bossbar;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.gui.button.RendererButton;
import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

public class GuiHudPosition
extends GuiScreen {
    private final RendererButton[] rendererButtons;
    private final Minecraft mc;
    public RendererButton selectedRenderer;
    private int prevX;
    private int prevY;
    private Float[] lines;
    private static final float SNAP_PULL = 0.5f;

    public GuiHudPosition(RendererButton[] rendererButtons) {
        this.rendererButtons = rendererButtons;
        for (RendererButton rendererButton : rendererButtons) {
            rendererButton.parentGui = this;
        }
        this.mc = Minecraft.getMinecraft();
    }

    protected void keyTyped(char c, int key) {
        if (key == 1) {
            UViewSettings.saveConfig();
            Bossbar.restore();
            this.mc.displayGuiScreen(null);
        }
        if (this.selectedRenderer != null) {
            if (key == 30 || key == 203) {
                this.selectedRenderer.getRenderer().getX().setValue(Float.valueOf(this.selectedRenderer.getRenderer().getX().value().floatValue() - (Keyboard.isKeyDown((int)42) ? 0.01f : 0.002f)));
            } else if (key == 17 || key == 200) {
                this.selectedRenderer.getRenderer().getY().setValue(Float.valueOf(this.selectedRenderer.getRenderer().getY().value().floatValue() - (Keyboard.isKeyDown((int)42) ? 0.01f : 0.002f)));
            } else if (key == 32 || key == 205) {
                this.selectedRenderer.getRenderer().getX().setValue(Float.valueOf(this.selectedRenderer.getRenderer().getX().value().floatValue() + (Keyboard.isKeyDown((int)42) ? 0.01f : 0.002f)));
            } else if (key == 31 || key == 208) {
                this.selectedRenderer.getRenderer().getY().setValue(Float.valueOf(this.selectedRenderer.getRenderer().getY().value().floatValue() + (Keyboard.isKeyDown((int)42) ? 0.01f : 0.002f)));
            } else if (key == 44 && Keyboard.isKeyDown((int)29)) {
                this.selectedRenderer.restorePosition(new ScaledResolution(this.mc));
            }
            this.checkBounds(this.selectedRenderer, new ScaledResolution(this.mc));
        }
    }

    public void drawScreen(int x, int y, float partialTicks) {
        float zBackup = this.zLevel;
        this.zLevel = 200.0f;
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        if (this.lines != null) {
            for (int i = 0; i < 2; ++i) {
                if (this.lines[i] == null) continue;
                if (i == 0) {
                    this.drawVerticalLine(this.lines[i].intValue(), 0, scaledResolution.getScaledHeight(), -60269);
                    continue;
                }
                this.drawHorizontalLine(0, scaledResolution.getScaledWidth(), this.lines[i].intValue(), -60269);
            }
        }
        if (this.selectedRenderer != null) {
            int ax = (int)(this.selectedRenderer.getRenderer().getX().value().floatValue() * (float)scaledResolution.getScaledWidth());
            int ay = (int)(this.selectedRenderer.getRenderer().getY().value().floatValue() * (float)scaledResolution.getScaledHeight());
            GuiHudPosition.drawRect((int)(ax - 1), (int)(ay - 1), (int)(ax + this.selectedRenderer.getRenderer().getWidth() + 1), (int)(ay + this.selectedRenderer.getRenderer().getHeight() + 1), (int)-16711809);
            GuiHudPosition.drawRect((int)ax, (int)ay, (int)(ax + this.selectedRenderer.getRenderer().getWidth()), (int)(ay + this.selectedRenderer.getRenderer().getHeight()), (int)-578302214);
        }
        for (RendererButton rendererButton : this.rendererButtons) {
            rendererButton.drawButton(this.mc, scaledResolution);
        }
        this.zLevel = zBackup;
    }

    public Float[] checkLines(ScaledResolution sr) {
        try {
            if (this.selectedRenderer != null) {
                Float horizontalLine = null;
                Float verticalLine = null;
                for (RendererButton otherButton : this.rendererButtons) {
                    if (otherButton == this.selectedRenderer) continue;
                    for (Edge otherEdge : Edge.getHorizontalEdges()) {
                        for (Edge thisEdge : Edge.getHorizontalEdges()) {
                            float delta = otherEdge.getCoordinate(otherButton, sr) - thisEdge.getCoordinate(this.selectedRenderer, sr);
                            if (!(Math.abs(delta) <= 0.5f) || verticalLine != null) continue;
                            verticalLine = Float.valueOf(otherEdge.getCoordinate(otherButton, sr));
                        }
                    }
                    for (Edge otherEdge : Edge.getVerticalEdges()) {
                        for (Edge thisEdge : Edge.getVerticalEdges()) {
                            float delt = otherEdge.getCoordinate(otherButton, sr) - thisEdge.getCoordinate(this.selectedRenderer, sr);
                            if (!(Math.abs(delt) <= 0.5f) || horizontalLine != null) continue;
                            horizontalLine = Float.valueOf(otherEdge.getCoordinate(otherButton, sr));
                        }
                    }
                }
                return new Float[]{horizontalLine, verticalLine};
            }
            return null;
        }catch (NullPointerException ex){
            return null;
        }
    }

    protected void mouseClicked(int x, int y, int button) {
        this.prevX = x;
        this.prevY = y;
        this.clicked(x, y);
    }

    protected void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {
        if (this.selectedRenderer != null && this.selectedRenderer.clickedCorner) {
            this.selectedRenderer.clickedCorner = false;
        }
        if (this.selectedRenderer != null) {
            this.lines = null;
        }
    }

    protected void mouseClickMove(int x, int y, int button, long time) {
        if (this.selectedRenderer != null) {
            ScaledResolution scaledResolution = new ScaledResolution(this.mc);
            this.lines = this.checkLines(scaledResolution);
            if (this.selectedRenderer.clickedCorner) {
                this.selectedRenderer.handleDragCorner(x, y, scaledResolution);
            } else {
                int ax = (int)(this.selectedRenderer.getRenderer().getX().value().floatValue() * (float)scaledResolution.getScaledWidth()) + x - this.prevX;
                int ay = (int)(this.selectedRenderer.getRenderer().getY().value().floatValue() * (float)scaledResolution.getScaledHeight()) + y - this.prevY;
                this.selectedRenderer.getRenderer().getX().setValue(Float.valueOf((float)ax * 1.0f / (float)scaledResolution.getScaledWidth()));
                this.selectedRenderer.getRenderer().getY().setValue(Float.valueOf((float)ay * 1.0f / (float)scaledResolution.getScaledHeight()));
            }
            this.checkBounds(this.selectedRenderer, scaledResolution);
        }
        this.prevX = x;
        this.prevY = y;
    }

    private void checkBounds(RendererButton rendererButton, ScaledResolution scaledResolution) {
        int screenWidth = scaledResolution.getScaledWidth();
        int screenHeight = scaledResolution.getScaledHeight();
        int x = Math.max(0, Math.min((int)(rendererButton.getRenderer().getX().value().floatValue() * (float)scaledResolution.getScaledWidth()), Math.max(screenWidth - rendererButton.getRenderer().getWidth(), 0)));
        int y = Math.max(0, Math.min((int)(rendererButton.getRenderer().getY().value().floatValue() * (float)scaledResolution.getScaledHeight()), Math.max(screenHeight - rendererButton.getRenderer().getHeight(), 0)));
        rendererButton.getRenderer().getX().setValue(Float.valueOf((float)x * 1.0f / (float)screenWidth));
        rendererButton.getRenderer().getY().setValue(Float.valueOf((float)y * 1.0f / (float)screenHeight));
    }

    private void clicked(int x, int y) {
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        if (this.selectedRenderer != null && this.selectedRenderer.isClickedCorner(x, y, scaledResolution)) {
            this.selectedRenderer.clickedCorner = true;
            this.selectedRenderer.prevWidth = this.selectedRenderer.getRenderer().getWidth();
            this.selectedRenderer.prevHeight = this.selectedRenderer.getRenderer().getHeight();
            this.selectedRenderer.prevMouseX = x;
            this.selectedRenderer.prevMouseY = y;
            return;
        }
        this.selectedRenderer = null;
        for (RendererButton rendererButton : this.rendererButtons) {
            int absoluteX = (int)(rendererButton.getRenderer().getX().value().floatValue() * (float)scaledResolution.getScaledWidth());
            int absoluteY = (int)(rendererButton.getRenderer().getY().value().floatValue() * (float)scaledResolution.getScaledHeight());
            if (x < absoluteX || x > absoluteX + rendererButton.getRenderer().getWidth() || y < absoluteY || y > absoluteY + rendererButton.getRenderer().getHeight()) continue;
            this.selectedRenderer = rendererButton;
            this.selectedRenderer.originX = this.selectedRenderer.getRenderer().getX().value().floatValue();
            this.selectedRenderer.originY = this.selectedRenderer.getRenderer().getY().value().floatValue();
            this.selectedRenderer.originScale = this.selectedRenderer.getRenderer().getScale().value().floatValue();
            break;
        }
    }

    public void onGuiClosed() {
        UViewSettings.saveConfig();
        Bossbar.restore();
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public static enum Edge {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        HORIZONTAL_MIDDLE,
        VERTICAL_MIDDLE;

        private static final Set<Edge> verticalEdges;
        private static final Set<Edge> horizontalEdges;

        public float getCoordinate(RendererButton button, ScaledResolution sr) {
            switch (this) {
                case LEFT: {
                    return button.getRenderer().getX().value().floatValue() * (float)sr.getScaledWidth();
                }
                case TOP: {
                    return button.getRenderer().getY().value().floatValue() * (float)sr.getScaledHeight();
                }
                case RIGHT: {
                    return LEFT.getCoordinate(button, sr) + (float)button.getRenderer().getWidth();
                }
                case BOTTOM: {
                    return TOP.getCoordinate(button, sr) + (float)button.getRenderer().getHeight();
                }
                case VERTICAL_MIDDLE: {
                    return LEFT.getCoordinate(button, sr) + (float)button.getRenderer().getWidth() / 2.0f;
                }
                case HORIZONTAL_MIDDLE: {
                    return TOP.getCoordinate(button, sr) + (float)button.getRenderer().getHeight() / 2.0f;
                }
            }
            return 0.0f;
        }

        public static Set<Edge> getVerticalEdges() {
            return verticalEdges;
        }

        public static Set<Edge> getHorizontalEdges() {
            return horizontalEdges;
        }

        static {
            verticalEdges = null;
            horizontalEdges = null;
        }
    }
}

