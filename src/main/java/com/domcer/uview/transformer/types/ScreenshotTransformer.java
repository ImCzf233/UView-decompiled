// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 */
package com.domcer.uview.transformer.types;

import com.domcer.uview.transformer.TransformerManager;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ScreenshotTransformer {

    @TransformerManager.TransformTarget(className="net.minecraft.client.Minecraft", methodNames={"dispatchKeypresses", "dispatchKeypresses"}, desc="()V")
    public static class saveScreenshotTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            for (AbstractInsnNode ain : il.toArray()) {
                if (!(ain instanceof MethodInsnNode) || ain.getOpcode() != 184 || !"saveScreenshot".equals(((MethodInsnNode)ain).name) && !"saveScreenshot".equals(((MethodInsnNode)ain).name)) continue;
                MethodInsnNode node = new MethodInsnNode(184, "com/domcer/uview/util/ScreenshotHelper", "saveScreenshot", "(Ljava/io/File;IILnet/minecraft/client/shader/Framebuffer;)V", false);
                il.set(ain, (AbstractInsnNode)node);
                il.insert((AbstractInsnNode)node, (AbstractInsnNode)new InsnNode(177));
                break;
            }
        }
    }
}

