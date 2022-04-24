// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.ibm.icu.text.ArabicShaping
 *  com.ibm.icu.text.ArabicShapingException
 *  com.ibm.icu.text.Bidi
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 *  org.lwjgl.opengl.GL11
 */
package com.domcer.uview.module.impl.misc;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;

public class UViewFontRenderer {
    private static final ResourceLocation[] unicodePageLocations = new ResourceLocation[256];
    protected int[] charWidth = new int[256];
    public int FONT_HEIGHT = 9;
    public Random fontRandom = new Random();
    protected byte[] glyphWidth = new byte[65536];
    private int[] colorCode = new int[32];
    protected final ResourceLocation locationFontTexture;
    private final TextureManager renderEngine;
    protected float posX;
    protected float posY;
    private boolean unicodeFlag;
    private boolean bidiFlag;
    private float red;
    private float blue;
    private float green;
    private float alpha;
    private int textColor;
    private boolean randomStyle;
    private boolean boldStyle;
    private boolean italicStyle;
    private boolean underlineStyle;
    private boolean strikethroughStyle;

    public UViewFontRenderer(GameSettings gameSettingsIn, ResourceLocation location, TextureManager textureManagerIn, boolean unicode) {
        this.locationFontTexture = location;
        this.renderEngine = textureManagerIn;
        this.unicodeFlag = unicode;
        this.bindTexture(this.locationFontTexture);
        for (int i = 0; i < 32; ++i) {
            int j = (i >> 3 & 1) * 85;
            int k = (i >> 2 & 1) * 170 + j;
            int l = (i >> 1 & 1) * 170 + j;
            int i1 = (i >> 0 & 1) * 170 + j;
            if (i == 6) {
                k += 85;
            }
            if (gameSettingsIn.anaglyph) {
                int j1 = (k * 30 + l * 59 + i1 * 11) / 100;
                int k1 = (k * 30 + l * 70) / 100;
                int l1 = (k * 30 + i1 * 70) / 100;
                k = j1;
                l = k1;
                i1 = l1;
            }
            if (i >= 16) {
                k /= 4;
                l /= 4;
                i1 /= 4;
            }
            this.colorCode[i] = (k & 0xFF) << 16 | (l & 0xFF) << 8 | i1 & 0xFF;
        }
        this.readGlyphSizes();
    }

    public void onResourceManagerReload(IResourceManager p_onResourceManagerReload_1_) {
        this.readFontTexture();
        this.readGlyphSizes();
    }

    private void readFontTexture() {
        BufferedImage bufferedimage;
        try {
            bufferedimage = TextureUtil.readBufferedImage((InputStream)this.getResourceInputStream(this.locationFontTexture));
        }
        catch (IOException var17) {
            throw new RuntimeException(var17);
        }
        int i = bufferedimage.getWidth();
        int j = bufferedimage.getHeight();
        int[] aint = new int[i * j];
        bufferedimage.getRGB(0, 0, i, j, aint, 0, i);
        int k = j / 16;
        int l = i / 16;
        int i1 = 1;
        float f = 8.0f / (float)l;
        for (int j1 = 0; j1 < 256; ++j1) {
            int i2;
            int k1 = j1 % 16;
            int l1 = j1 / 16;
            if (j1 == 32) {
                this.charWidth[j1] = 3 + i1;
            }
            for (i2 = l - 1; i2 >= 0; --i2) {
                int j2 = k1 * l + i2;
                boolean flag = true;
                for (int k2 = 0; k2 < k && flag; ++k2) {
                    int l2 = (l1 * l + k2) * i;
                    if ((aint[j2 + l2] >> 24 & 0xFF) == 0) continue;
                    flag = false;
                }
                if (!flag) break;
            }
            this.charWidth[j1] = (int)(0.5 + (double)((float)(++i2) * f)) + i1;
        }
    }

    private void readGlyphSizes() {
        InputStream inputstream = null;
        try {
            inputstream = this.getResourceInputStream(new ResourceLocation("font/glyph_sizes.bin"));
            inputstream.read(this.glyphWidth);
        }
        catch (IOException var6) {
            try {
                throw new RuntimeException(var6);
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(inputstream);
                throw throwable;
            }
        }
        IOUtils.closeQuietly((InputStream)inputstream);
    }

    private float func_181559_a(char p_181559_1_, boolean p_181559_2_) {
        if (p_181559_1_ == ' ') {
            return 4.0f;
        }
        int i = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(p_181559_1_);
        return i != -1 && !this.unicodeFlag ? this.renderDefaultChar(i, p_181559_2_) : this.renderUnicodeChar(p_181559_1_, p_181559_2_);
    }

    protected float renderDefaultChar(int p_renderDefaultChar_1_, boolean p_renderDefaultChar_2_) {
        int i = p_renderDefaultChar_1_ % 16 * 8;
        int j = p_renderDefaultChar_1_ / 16 * 8;
        boolean k = p_renderDefaultChar_2_;
        this.bindTexture(this.locationFontTexture);
        int l = this.charWidth[p_renderDefaultChar_1_];
        float f = (float)l - 0.01f;
        GL11.glBegin((int)5);
        GL11.glTexCoord2f((float)((float)i / 128.0f), (float)((float)j / 128.0f));
        GL11.glTexCoord2f((float)((float)i / 128.0f), (float)(((float)j + 7.99f) / 128.0f));
        GL11.glTexCoord2f((float)(((float)i + f - 1.0f) / 128.0f), (float)((float)j / 128.0f));
        GL11.glTexCoord2f((float)(((float)i + f - 1.0f) / 128.0f), (float)(((float)j + 7.99f) / 128.0f));
        GL11.glEnd();
        return l;
    }

    private ResourceLocation getUnicodePageLocation(int p_getUnicodePageLocation_1_) {
        if (unicodePageLocations[p_getUnicodePageLocation_1_] == null) {
            UViewFontRenderer.unicodePageLocations[p_getUnicodePageLocation_1_] = new ResourceLocation(String.format("textures/font/unicode_page_%02x.png", p_getUnicodePageLocation_1_));
        }
        return unicodePageLocations[p_getUnicodePageLocation_1_];
    }

    private void loadGlyphTexture(int p_loadGlyphTexture_1_) {
        this.bindTexture(this.getUnicodePageLocation(p_loadGlyphTexture_1_));
    }

    protected float renderUnicodeChar(char p_renderUnicodeChar_1_, boolean p_renderUnicodeChar_2_) {
        if (this.glyphWidth[p_renderUnicodeChar_1_] == 0) {
            return 0.0f;
        }
        int i = p_renderUnicodeChar_1_ / 256;
        this.loadGlyphTexture(i);
        int j = this.glyphWidth[p_renderUnicodeChar_1_] >>> 4;
        int k = this.glyphWidth[p_renderUnicodeChar_1_] & 0xF;
        float f = j;
        float f1 = k + 1;
        float f2 = (float)(p_renderUnicodeChar_1_ % 16 * 16) + f;
        float f3 = (p_renderUnicodeChar_1_ & 0xFF) / 16 * 16;
        float f4 = f1 - f - 0.02f;
        float f5 = p_renderUnicodeChar_2_ ? 1.0f : 0.0f;
        GL11.glBegin((int)5);
        GL11.glTexCoord2f((float)(f2 / 256.0f), (float)(f3 / 256.0f));
        GL11.glVertex3f((float)(this.posX + f5), (float)this.posY, (float)0.0f);
        GL11.glTexCoord2f((float)(f2 / 256.0f), (float)((f3 + 15.98f) / 256.0f));
        GL11.glVertex3f((float)(this.posX - f5), (float)(this.posY + 7.99f), (float)0.0f);
        GL11.glTexCoord2f((float)((f2 + f4) / 256.0f), (float)(f3 / 256.0f));
        GL11.glVertex3f((float)(this.posX + f4 / 2.0f + f5), (float)this.posY, (float)0.0f);
        GL11.glTexCoord2f((float)((f2 + f4) / 256.0f), (float)((f3 + 15.98f) / 256.0f));
        GL11.glVertex3f((float)(this.posX + f4 / 2.0f - f5), (float)(this.posY + 7.99f), (float)0.0f);
        GL11.glEnd();
        return (f1 - f) / 2.0f + 1.0f;
    }

    public int drawStringWithShadow(String p_drawStringWithShadow_1_, float p_drawStringWithShadow_2_, float p_drawStringWithShadow_3_, int p_drawStringWithShadow_4_) {
        return this.drawString(p_drawStringWithShadow_1_, p_drawStringWithShadow_2_, p_drawStringWithShadow_3_, p_drawStringWithShadow_4_, true);
    }

    public int drawString(String p_drawString_1_, int p_drawString_2_, int p_drawString_3_, int p_drawString_4_) {
        return this.drawString(p_drawString_1_, p_drawString_2_, p_drawString_3_, p_drawString_4_, false);
    }

    public int drawString(String p_drawString_1_, float p_drawString_2_, float p_drawString_3_, int p_drawString_4_, boolean p_drawString_5_) {
        int i;
        this.enableAlpha();
        this.resetStyles();
        if (p_drawString_5_) {
            i = this.renderString(p_drawString_1_, p_drawString_2_ + 1.0f, p_drawString_3_ + 1.0f, p_drawString_4_, true);
            i = Math.max(i, this.renderString(p_drawString_1_, p_drawString_2_, p_drawString_3_, p_drawString_4_, false));
        } else {
            i = this.renderString(p_drawString_1_, p_drawString_2_, p_drawString_3_, p_drawString_4_, false);
        }
        return i;
    }

    private String bidiReorder(String p_bidiReorder_1_) {
        try {
            Bidi bidi = new Bidi(new ArabicShaping(8).shape(p_bidiReorder_1_), 127);
            bidi.setReorderingMode(0);
            return bidi.writeReordered(2);
        }
        catch (ArabicShapingException var3) {
            return p_bidiReorder_1_;
        }
    }

    private void resetStyles() {
        this.randomStyle = false;
        this.boldStyle = false;
        this.italicStyle = false;
        this.underlineStyle = false;
        this.strikethroughStyle = false;
    }

    private void renderStringAtPos(String p_renderStringAtPos_1_, boolean p_renderStringAtPos_2_) {
        for (int i = 0; i < p_renderStringAtPos_1_.length(); ++i) {
            boolean flag;
            int j1;
            int i1;
            char c0 = p_renderStringAtPos_1_.charAt(i);
            if (c0 == '\u00a7' && i + 1 < p_renderStringAtPos_1_.length()) {
                i1 = "0123456789abcdefklmnor".indexOf(p_renderStringAtPos_1_.toLowerCase(Locale.ENGLISH).charAt(i + 1));
                if (i1 < 16) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    if (i1 < 0 || i1 > 15) {
                        i1 = 15;
                    }
                    if (p_renderStringAtPos_2_) {
                        i1 += 16;
                    }
                    this.textColor = j1 = this.colorCode[i1];
                    this.setColor((float)(j1 >> 16) / 255.0f, (float)(j1 >> 8 & 0xFF) / 255.0f, (float)(j1 & 0xFF) / 255.0f, this.alpha);
                } else if (i1 == 16) {
                    this.randomStyle = true;
                } else if (i1 == 17) {
                    this.boldStyle = true;
                } else if (i1 == 18) {
                    this.strikethroughStyle = true;
                } else if (i1 == 19) {
                    this.underlineStyle = true;
                } else if (i1 == 20) {
                    this.italicStyle = true;
                } else if (i1 == 21) {
                    this.randomStyle = false;
                    this.boldStyle = false;
                    this.strikethroughStyle = false;
                    this.underlineStyle = false;
                    this.italicStyle = false;
                    this.setColor(this.red, this.blue, this.green, this.alpha);
                }
                ++i;
                continue;
            }
            i1 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(c0);
            if (this.randomStyle && i1 != -1) {
                char c1;
                j1 = this.getCharWidth(c0);
                while (j1 != this.getCharWidth(c1 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".charAt(i1 = this.fontRandom.nextInt("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".length())))) {
                }
                c0 = c1;
            }
            if (this.randomStyle && i1 == -1) {
                c0 = "\u554a\u54e6\u5443\u54a6\u5514\u55bb\u54ce\u8bf6\u5582\u55f7\u5455\u54df\u5414\u54d5\u5532\u54b9\u55ef\u541f\u543b\u6600\u663b\u6069\u5624\u55e1\u5624".charAt(this.fontRandom.nextInt(25));
            }
            float f1 = i1 != -1 && !this.unicodeFlag ? 1.0f : 0.5f;
            boolean bl = flag = (c0 == '\u0000' || i1 == -1 || this.unicodeFlag) && p_renderStringAtPos_2_;
            if (flag) {
                this.posX -= f1;
                this.posY -= f1;
            }
            float f = this.func_181559_a(c0, this.italicStyle);
            if (flag) {
                this.posX += f1;
                this.posY += f1;
            }
            if (this.boldStyle) {
                this.posX += f1;
                if (flag) {
                    this.posX -= f1;
                    this.posY -= f1;
                }
                this.func_181559_a(c0, this.italicStyle);
                this.posX -= f1;
                if (flag) {
                    this.posX += f1;
                    this.posY += f1;
                }
                f += 1.0f;
            }
            this.doDraw(f);
        }
    }

    protected void doDraw(float p_doDraw_1_) {
        WorldRenderer worldrenderer1;
        Tessellator tessellator1;
        if (this.strikethroughStyle) {
            tessellator1 = Tessellator.getInstance();
            worldrenderer1 = tessellator1.getWorldRenderer();
            GlStateManager.disableTexture2D();
            worldrenderer1.begin(7, DefaultVertexFormats.POSITION);
            worldrenderer1.pos((double)this.posX, (double)(this.posY + (float)(this.FONT_HEIGHT / 2)), 0.0).endVertex();
            worldrenderer1.pos((double)(this.posX + p_doDraw_1_), (double)(this.posY + (float)(this.FONT_HEIGHT / 2)), 0.0).endVertex();
            worldrenderer1.pos((double)(this.posX + p_doDraw_1_), (double)(this.posY + (float)(this.FONT_HEIGHT / 2) - 1.0f), 0.0).endVertex();
            worldrenderer1.pos((double)this.posX, (double)(this.posY + (float)(this.FONT_HEIGHT / 2) - 1.0f), 0.0).endVertex();
            tessellator1.draw();
            GlStateManager.enableTexture2D();
        }
        if (this.underlineStyle) {
            tessellator1 = Tessellator.getInstance();
            worldrenderer1 = tessellator1.getWorldRenderer();
            GlStateManager.disableTexture2D();
            worldrenderer1.begin(7, DefaultVertexFormats.POSITION);
            int l = this.underlineStyle ? -1 : 0;
            worldrenderer1.pos((double)(this.posX + (float)l), (double)(this.posY + (float)this.FONT_HEIGHT), 0.0).endVertex();
            worldrenderer1.pos((double)(this.posX + p_doDraw_1_), (double)(this.posY + (float)this.FONT_HEIGHT), 0.0).endVertex();
            worldrenderer1.pos((double)(this.posX + p_doDraw_1_), (double)(this.posY + (float)this.FONT_HEIGHT - 1.0f), 0.0).endVertex();
            worldrenderer1.pos((double)(this.posX + (float)l), (double)(this.posY + (float)this.FONT_HEIGHT - 1.0f), 0.0).endVertex();
            tessellator1.draw();
            GlStateManager.enableTexture2D();
        }
        this.posX += (float)((int)p_doDraw_1_);
    }

    private int renderStringAligned(String p_renderStringAligned_1_, int p_renderStringAligned_2_, int p_renderStringAligned_3_, int p_renderStringAligned_4_, int p_renderStringAligned_5_, boolean p_renderStringAligned_6_) {
        if (this.bidiFlag) {
            int i = this.getStringWidth(this.bidiReorder(p_renderStringAligned_1_));
            p_renderStringAligned_2_ = p_renderStringAligned_2_ + p_renderStringAligned_4_ - i;
        }
        return this.renderString(p_renderStringAligned_1_, p_renderStringAligned_2_, p_renderStringAligned_3_, p_renderStringAligned_5_, p_renderStringAligned_6_);
    }

    private int renderString(String p_renderString_1_, float p_renderString_2_, float p_renderString_3_, int p_renderString_4_, boolean p_renderString_5_) {
        if (p_renderString_1_ == null) {
            return 0;
        }
        if (this.bidiFlag) {
            p_renderString_1_ = this.bidiReorder(p_renderString_1_);
        }
        if ((p_renderString_4_ & 0xFC000000) == 0) {
            p_renderString_4_ |= 0xFF000000;
        }
        if (p_renderString_5_) {
            p_renderString_4_ = (p_renderString_4_ & 0xFCFCFC) >> 2 | p_renderString_4_ & 0xFF000000;
        }
        this.red = (float)(p_renderString_4_ >> 16 & 0xFF) / 255.0f;
        this.blue = (float)(p_renderString_4_ >> 8 & 0xFF) / 255.0f;
        this.green = (float)(p_renderString_4_ & 0xFF) / 255.0f;
        this.alpha = (float)(p_renderString_4_ >> 24 & 0xFF) / 255.0f;
        this.setColor(this.red, this.blue, this.green, this.alpha);
        this.posX = p_renderString_2_;
        this.posY = p_renderString_3_;
        this.renderStringAtPos(p_renderString_1_, p_renderString_5_);
        return (int)this.posX;
    }

    public int getStringWidth(String p_getStringWidth_1_) {
        if (p_getStringWidth_1_ == null) {
            return 0;
        }
        int i = 0;
        boolean flag = false;
        for (int j = 0; j < p_getStringWidth_1_.length(); ++j) {
            char c0 = p_getStringWidth_1_.charAt(j);
            int k = this.getCharWidth(c0);
            if (k < 0 && j < p_getStringWidth_1_.length() - 1) {
                if ((c0 = p_getStringWidth_1_.charAt(++j)) != 'l' && c0 != 'L') {
                    if (c0 == 'r' || c0 == 'R') {
                        flag = false;
                    }
                } else {
                    flag = true;
                }
                k = 0;
            }
            i += k;
            if (!flag || k <= 0) continue;
            ++i;
        }
        return i;
    }

    public int getCharWidth(char p_getCharWidth_1_) {
        if (p_getCharWidth_1_ == '\u00a7') {
            return -1;
        }
        if (p_getCharWidth_1_ == ' ') {
            return 4;
        }
        int i = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(p_getCharWidth_1_);
        if (p_getCharWidth_1_ > '\u0000' && i != -1 && !this.unicodeFlag) {
            return this.charWidth[i];
        }
        if (this.glyphWidth[p_getCharWidth_1_] != 0) {
            int j = this.glyphWidth[p_getCharWidth_1_] >>> 4;
            int k = this.glyphWidth[p_getCharWidth_1_] & 0xF;
            return (++k - j) / 2 + 1;
        }
        return 0;
    }

    public String trimStringToWidth(String p_trimStringToWidth_1_, int p_trimStringToWidth_2_) {
        return this.trimStringToWidth(p_trimStringToWidth_1_, p_trimStringToWidth_2_, false);
    }

    public String trimStringToWidth(String p_trimStringToWidth_1_, int p_trimStringToWidth_2_, boolean p_trimStringToWidth_3_) {
        StringBuilder stringbuilder = new StringBuilder();
        int i = 0;
        int j = p_trimStringToWidth_3_ ? p_trimStringToWidth_1_.length() - 1 : 0;
        int k = p_trimStringToWidth_3_ ? -1 : 1;
        boolean flag = false;
        boolean flag1 = false;
        for (int l = j; l >= 0 && l < p_trimStringToWidth_1_.length() && i < p_trimStringToWidth_2_; l += k) {
            char c0 = p_trimStringToWidth_1_.charAt(l);
            int i1 = this.getCharWidth(c0);
            if (flag) {
                flag = false;
                if (c0 != 'l' && c0 != 'L') {
                    if (c0 == 'r' || c0 == 'R') {
                        flag1 = false;
                    }
                } else {
                    flag1 = true;
                }
            } else if (i1 < 0) {
                flag = true;
            } else {
                i += i1;
                if (flag1) {
                    ++i;
                }
            }
            if (i > p_trimStringToWidth_2_) break;
            if (p_trimStringToWidth_3_) {
                stringbuilder.insert(0, c0);
                continue;
            }
            stringbuilder.append(c0);
        }
        return stringbuilder.toString();
    }

    private String trimStringNewline(String p_trimStringNewline_1_) {
        while (p_trimStringNewline_1_ != null && p_trimStringNewline_1_.endsWith("\n")) {
            p_trimStringNewline_1_ = p_trimStringNewline_1_.substring(0, p_trimStringNewline_1_.length() - 1);
        }
        return p_trimStringNewline_1_;
    }

    public void drawSplitString(String p_drawSplitString_1_, int p_drawSplitString_2_, int p_drawSplitString_3_, int p_drawSplitString_4_, int p_drawSplitString_5_) {
        this.resetStyles();
        this.textColor = p_drawSplitString_5_;
        p_drawSplitString_1_ = this.trimStringNewline(p_drawSplitString_1_);
        this.renderSplitString(p_drawSplitString_1_, p_drawSplitString_2_, p_drawSplitString_3_, p_drawSplitString_4_, false);
    }

    private void renderSplitString(String p_renderSplitString_1_, int p_renderSplitString_2_, int p_renderSplitString_3_, int p_renderSplitString_4_, boolean p_renderSplitString_5_) {
        for (String s : this.listFormattedStringToWidth(p_renderSplitString_1_, p_renderSplitString_4_)) {
            this.renderStringAligned(s, p_renderSplitString_2_, p_renderSplitString_3_, p_renderSplitString_4_, this.textColor, p_renderSplitString_5_);
            p_renderSplitString_3_ += this.FONT_HEIGHT;
        }
    }

    public int splitStringWidth(String p_splitStringWidth_1_, int p_splitStringWidth_2_) {
        return this.FONT_HEIGHT * this.listFormattedStringToWidth(p_splitStringWidth_1_, p_splitStringWidth_2_).size();
    }

    public void setUnicodeFlag(boolean p_setUnicodeFlag_1_) {
        this.unicodeFlag = p_setUnicodeFlag_1_;
    }

    public boolean getUnicodeFlag() {
        return this.unicodeFlag;
    }

    public void setBidiFlag(boolean p_setBidiFlag_1_) {
        this.bidiFlag = p_setBidiFlag_1_;
    }

    public List<String> listFormattedStringToWidth(String p_listFormattedStringToWidth_1_, int p_listFormattedStringToWidth_2_) {
        return Arrays.asList(this.wrapFormattedStringToWidth(p_listFormattedStringToWidth_1_, p_listFormattedStringToWidth_2_).split("\n"));
    }

    String wrapFormattedStringToWidth(String p_wrapFormattedStringToWidth_1_, int p_wrapFormattedStringToWidth_2_) {
        int i = this.sizeStringToWidth(p_wrapFormattedStringToWidth_1_, p_wrapFormattedStringToWidth_2_);
        if (p_wrapFormattedStringToWidth_1_.length() <= i) {
            return p_wrapFormattedStringToWidth_1_;
        }
        String s = p_wrapFormattedStringToWidth_1_.substring(0, i);
        char c0 = p_wrapFormattedStringToWidth_1_.charAt(i);
        boolean flag = c0 == ' ' || c0 == '\n';
        String s1 = UViewFontRenderer.getFormatFromString(s) + p_wrapFormattedStringToWidth_1_.substring(i + (flag ? 1 : 0));
        return s + "\n" + this.wrapFormattedStringToWidth(s1, p_wrapFormattedStringToWidth_2_);
    }

    private int sizeStringToWidth(String p_sizeStringToWidth_1_, int p_sizeStringToWidth_2_) {
        int k;
        int i = p_sizeStringToWidth_1_.length();
        int j = 0;
        int l = -1;
        boolean flag = false;
        for (k = 0; k < i; ++k) {
            char c0 = p_sizeStringToWidth_1_.charAt(k);
            switch (c0) {
                case '\n': {
                    --k;
                    break;
                }
                case ' ': {
                    l = k;
                }
                default: {
                    j += this.getCharWidth(c0);
                    if (!flag) break;
                    ++j;
                    break;
                }
                case '\u00a7': {
                    char c1;
                    if (k >= i - 1) break;
                    if ((c1 = p_sizeStringToWidth_1_.charAt(++k)) != 'l' && c1 != 'L') {
                        if (c1 != 'r' && c1 != 'R' && !UViewFontRenderer.isFormatColor(c1)) break;
                        flag = false;
                        break;
                    }
                    flag = true;
                }
            }
            if (c0 == '\n') {
                l = ++k;
                break;
            }
            if (j > p_sizeStringToWidth_2_) break;
        }
        return k != i && l != -1 && l < k ? l : k;
    }

    private static boolean isFormatColor(char p_isFormatColor_0_) {
        return p_isFormatColor_0_ >= '0' && p_isFormatColor_0_ <= '9' || p_isFormatColor_0_ >= 'a' && p_isFormatColor_0_ <= 'f' || p_isFormatColor_0_ >= 'A' && p_isFormatColor_0_ <= 'F';
    }

    private static boolean isFormatSpecial(char p_isFormatSpecial_0_) {
        return p_isFormatSpecial_0_ >= 'k' && p_isFormatSpecial_0_ <= 'o' || p_isFormatSpecial_0_ >= 'K' && p_isFormatSpecial_0_ <= 'O' || p_isFormatSpecial_0_ == 'r' || p_isFormatSpecial_0_ == 'R';
    }

    public static String getFormatFromString(String p_getFormatFromString_0_) {
        String s = "";
        int i = -1;
        int j = p_getFormatFromString_0_.length();
        while ((i = p_getFormatFromString_0_.indexOf(167, i + 1)) != -1) {
            if (i >= j - 1) continue;
            char c0 = p_getFormatFromString_0_.charAt(i + 1);
            if (UViewFontRenderer.isFormatColor(c0)) {
                s = "\u00a7" + c0;
                continue;
            }
            if (!UViewFontRenderer.isFormatSpecial(c0)) continue;
            s = s + "\u00a7" + c0;
        }
        return s;
    }

    public boolean getBidiFlag() {
        return this.bidiFlag;
    }

    protected void setColor(float p_setColor_1_, float p_setColor_2_, float p_setColor_3_, float p_setColor_4_) {
        GlStateManager.color((float)p_setColor_1_, (float)p_setColor_2_, (float)p_setColor_3_, (float)p_setColor_4_);
    }

    protected void enableAlpha() {
        GlStateManager.enableAlpha();
    }

    protected void bindTexture(ResourceLocation p_bindTexture_1_) {
        this.renderEngine.bindTexture(p_bindTexture_1_);
    }

    protected InputStream getResourceInputStream(ResourceLocation p_getResourceInputStream_1_) throws IOException {
        return Minecraft.getMinecraft().getResourceManager().getResource(p_getResourceInputStream_1_).getInputStream();
    }

    public int getColorCode(char p_getColorCode_1_) {
        return this.colorCode["0123456789abcdef".indexOf(p_getColorCode_1_)];
    }
}

