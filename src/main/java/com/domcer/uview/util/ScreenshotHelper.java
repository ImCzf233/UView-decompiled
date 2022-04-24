// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.event.ClickEvent
 *  net.minecraft.event.ClickEvent$Action
 *  net.minecraft.event.HoverEvent
 *  net.minecraft.event.HoverEvent$Action
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package com.domcer.uview.util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ScreenshotHelper {
    private static final Logger logger = LogManager.getLogger();
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    private static IntBuffer pixelBuffer;
    private static int[] pixelValues;

    public static void saveScreenshot(File dir, int width, int height, Framebuffer framebuffer) {
        File screenshotsDir = new File(dir, "screenshots");
        screenshotsDir.mkdir();
        if (OpenGlHelper.isFramebufferEnabled()) {
            width = framebuffer.framebufferTextureWidth;
            height = framebuffer.framebufferTextureHeight;
        }
        int size = width * height;
        if (pixelBuffer == null || pixelBuffer.capacity() < size) {
            pixelBuffer = BufferUtils.createIntBuffer((int)size);
            pixelValues = new int[size];
        }
        GL11.glPixelStorei((int)3333, (int)1);
        GL11.glPixelStorei((int)3317, (int)1);
        pixelBuffer.clear();
        if (OpenGlHelper.isFramebufferEnabled()) {
            GlStateManager.bindTexture((int)framebuffer.framebufferTexture);
            GL11.glGetTexImage((int)3553, (int)0, (int)32993, (int)33639, (IntBuffer)pixelBuffer);
        } else {
            GL11.glReadPixels((int)0, (int)0, (int)width, (int)height, (int)32993, (int)33639, (IntBuffer)pixelBuffer);
        }
        int finalWidth = width;
        int finalHeight = height;
        new Thread(() -> {
            pixelBuffer.get(pixelValues);
            try {
                BufferedImage bufferedImage;
                TextureUtil.processPixelValues((int[])pixelValues, (int)finalWidth, (int)finalHeight);
                if (OpenGlHelper.isFramebufferEnabled()) {
                    int k;
                    bufferedImage = new BufferedImage(framebuffer.framebufferWidth, framebuffer.framebufferHeight, 1);
                    for (int i = k = framebuffer.framebufferTextureHeight - framebuffer.framebufferHeight; i < framebuffer.framebufferTextureHeight; ++i) {
                        for (int j = 0; j < framebuffer.framebufferWidth; ++j) {
                            bufferedImage.setRGB(j, i - k, pixelValues[i * framebuffer.framebufferTextureWidth + j]);
                        }
                    }
                } else {
                    bufferedImage = new BufferedImage(finalWidth, finalHeight, 1);
                    bufferedImage.setRGB(0, 0, finalWidth, finalHeight, pixelValues, 0, finalWidth);
                }
                File file = ScreenshotHelper.getTimestampedPNGFileForDirectory(screenshotsDir);
                ImageIO.write((RenderedImage)bufferedImage, "png", file);
                ChatComponentText chatComponent = new ChatComponentText(file.getName());
                chatComponent.getChatStyle().setUnderlined(Boolean.valueOf(true));
                ChatComponentText openFile = new ChatComponentText("\u00a7a\u00a7l[\u6253\u5f00]");
                openFile.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, file.getAbsolutePath()));
                openFile.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("\u00a7e\u70b9\u51fb\u6253\u5f00\uff01")));
                ChatComponentText blank = new ChatComponentText(" ");
                blank.getChatStyle().setUnderlined(Boolean.valueOf(false));
                chatComponent.appendSibling((IChatComponent)blank);
                chatComponent.appendSibling((IChatComponent)openFile);
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentTranslation("screenshot.success", new Object[]{chatComponent}));
            }
            catch (Exception ex) {
                logger.warn("Couldn't save screenshot", (Throwable)ex);
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentTranslation("screenshot.failure", new Object[]{ex.getMessage()}));
            }
        }).start();
    }

    private static File getTimestampedPNGFileForDirectory(File screenshotsDir) {
        String name = dateFormat.format(new Date());
        int idx = 1;
        File file;
        while ((file = new File(screenshotsDir, name + (idx == 1 ? "" : "_" + idx) + ".png")).exists()) {
            ++idx;
        }
        return file;
    }
}

