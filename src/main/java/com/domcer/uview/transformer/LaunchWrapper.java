/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 *  net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.MethodNode
 */
package com.domcer.uview.transformer;

import com.domcer.uview.transformer.TransformerManager;
import com.domcer.uview.transformer.types.BossbarTransformer;
import com.domcer.uview.transformer.types.FontRendererTransformer;
import com.domcer.uview.transformer.types.GuiNewChatTransformer;
import com.domcer.uview.transformer.types.GuiPlayerTabOverlayTransformer;
import com.domcer.uview.transformer.types.NettyCompressionDecoderTransformer;
import com.domcer.uview.transformer.types.NetworkPlayerInfoTransformer;
import com.domcer.uview.transformer.types.RenderEntityItemTransformer;
import com.domcer.uview.transformer.types.RenderTNTPrimedTransformer;
import com.domcer.uview.transformer.types.ScreenshotTransformer;
import com.domcer.uview.transformer.types.SkinManagerTransformer;
import com.domcer.uview.transformer.types.WorldInfoTransformer;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class LaunchWrapper
implements IClassTransformer {
    private static final TransformerManager.IClassTransformer[] CLASS_TRANSFORMERS = new TransformerManager.IClassTransformer[0];
    private static final TransformerManager.IMethodTransformer[] METHOD_TRANSFORMERS = new TransformerManager.IMethodTransformer[]{new ScreenshotTransformer.saveScreenshotTransformer(), new BossbarTransformer.renderBossbarHealthTransformer(), new RenderTNTPrimedTransformer.doRenderTransformer(), new GuiNewChatTransformer.drawChatTransformer(), new GuiNewChatTransformer.printChatMessageWithOptionalDeletionTransformer(), new GuiNewChatTransformer.getChatComponentTransformer(), new SkinManagerTransformer.loadSkinFromCacheTransformer(), new SkinManagerTransformer.getTexturesTransformer(), new RenderEntityItemTransformer.doRenderTransformer(), new WorldInfoTransformer.setWorldTimeTransformer(), new GuiPlayerTabOverlayTransformer.renderPlayerlistTransformer(), new GuiPlayerTabOverlayTransformer.drawScoreboardValuesTransformer(), new FontRendererTransformer.renderStringAtPosTransformer(), new NetworkPlayerInfoTransformer.getLocationSkinTransformer(), new NetworkPlayerInfoTransformer.getLocationCapeTransformer(), new NettyCompressionDecoderTransformer.decodeTransformer(), new NettyCompressionDecoderTransformer.eidecodeTransformer()};
    private final TransformerManager transformerManager = new TransformerManager(CLASS_TRANSFORMERS, METHOD_TRANSFORMERS);

    public byte[] transform(String obfClassName, String className, byte[] bytes) {
        if (!this.transformerManager.classMap.containsKey(className) && !this.transformerManager.map.containsKey(className)) {
            return bytes;
        }
        ClassNode cn = new ClassNode();
        if (bytes != null && bytes.length > 0) {
            ClassReader cr = new ClassReader(bytes);
            cr.accept((ClassVisitor)cn, 0);
        } else {
            cn.name = className.replace(".", "/");
            cn.version = 52;
            cn.superName = "java/lang/Object";
        }
        this.transformerManager.transform(cn);
        for (MethodNode mn : cn.methods) {
            String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(obfClassName, mn.name, mn.desc);
            String methodDesc = FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(mn.desc);
            this.transformerManager.transform(cn, mn, className, methodName, methodDesc);
        }
        ClassWriter cw = new ClassWriter(1);
        cn.accept((ClassVisitor)cw);
        return cw.toByteArray();
    }
}

