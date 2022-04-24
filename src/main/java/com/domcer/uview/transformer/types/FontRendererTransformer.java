// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.FrameNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.IntInsnNode
 *  org.objectweb.asm.tree.JumpInsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package com.domcer.uview.transformer.types;

import com.domcer.uview.transformer.TransformerManager;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class FontRendererTransformer {

    @TransformerManager.TransformTarget(className="net.minecraft.client.gui.FontRenderer", methodNames={"renderStringAtPos", "renderStringAtPos"}, desc="(Ljava/lang/String;Z)V")
    public static class renderStringAtPosTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            LabelNode labelNode = null;
            for (AbstractInsnNode node : il.toArray()) {
                if (node instanceof FieldInsnNode) {
                    FieldInsnNode fn = (FieldInsnNode)node;
                    if (fn.getOpcode() != 180 || !"net/minecraft/client/gui/FontRenderer".equals(fn.owner) || !"unicodeFlag".equals(fn.name) && !"unicodeFlag".equals(fn.name) || !"Z".equals(fn.desc)) continue;
                    boolean flag = "unicodeFlag".equals(fn.name);
                    InsnList list = new InsnList();
                    list.add((AbstractInsnNode)new FrameNode(2, 1, null, 0, null));
                    list.add((AbstractInsnNode)new VarInsnNode(25, 0));
                    list.add((AbstractInsnNode)new FieldInsnNode(180, "net/minecraft/client/gui/FontRenderer", flag ? "randomStyle" : "randomStyle", "Z"));
                    list.add((AbstractInsnNode)new JumpInsnNode(153, labelNode));
                    list.add((AbstractInsnNode)new VarInsnNode(21, 5));
                    list.add((AbstractInsnNode)new InsnNode(2));
                    list.add((AbstractInsnNode)new JumpInsnNode(160, labelNode));
                    list.add((AbstractInsnNode)new LdcInsnNode((Object)"\u554a\u5427\u624d\u7684\u997f\u98de\u4e2a\u597d\u5c31\u770b\u4e86\u5417\u4f60\u54e6\u5e73\u53bb\u4eba\u662f\u4ed6\u6211\u60f3\u4e00\u5728\u54c7\u560e\u54c8\u4e2a\u563b\u770b\u5566\u6728\u5e93\u7684\u5f97\u5bb6\u62a4\u5f20\u5efa\u4fe9\u9524\u7cca\u8865\u52aa\u5c2c\u5f00"));
                    list.add((AbstractInsnNode)new VarInsnNode(25, 0));
                    list.add((AbstractInsnNode)new FieldInsnNode(180, "net/minecraft/client/gui/FontRenderer", flag ? "fontRandom" : "fontRandom", "Ljava/util/Random;"));
                    list.add((AbstractInsnNode)new IntInsnNode(16, "\u554a\u5427\u624d\u7684\u997f\u98de\u4e2a\u597d\u5c31\u770b\u4e86\u5417\u4f60\u54e6\u5e73\u53bb\u4eba\u662f\u4ed6\u6211\u60f3\u4e00\u5728\u54c7\u560e\u54c8\u4e2a\u563b\u770b\u5566\u6728\u5e93\u7684\u5f97\u5bb6\u62a4\u5f20\u5efa\u4fe9\u9524\u7cca\u8865\u52aa\u5c2c\u5f00".length()));
                    list.add((AbstractInsnNode)new MethodInsnNode(182, "java/util/Random", "nextInt", "(I)I"));
                    list.add((AbstractInsnNode)new MethodInsnNode(182, "java/lang/String", "charAt", "(I)C"));
                    list.add((AbstractInsnNode)new VarInsnNode(54, 4));
                    mn.instructions.insertBefore((AbstractInsnNode)labelNode, list);
                    continue;
                }
                if (!(node instanceof LabelNode)) continue;
                labelNode = (LabelNode)node;
            }
        }
    }
}

