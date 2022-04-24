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
 *  org.objectweb.asm.tree.IntInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package com.domcer.uview.transformer.types;

import com.domcer.uview.transformer.TransformerManager;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class GuiNewChatTransformer {

    @TransformerManager.TransformTarget(className="net.minecraft.client.gui.GuiNewChat", methodNames={"getChatComponent", "getChatComponent"}, desc="(II)Lnet/minecraft/util/IChatComponent;")
    public static class getChatComponentTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            for (AbstractInsnNode node : il.toArray()) {
                IntInsnNode in;
                if (!(node instanceof IntInsnNode) || (in = (IntInsnNode)node).getOpcode() != 16 || in.operand != 27) continue;
                il.set(node, (AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/module/impl/chat/BetterChat", "getHeight", "()I", false));
                break;
            }
        }
    }

    @TransformerManager.TransformTarget(className="net.minecraft.client.gui.GuiNewChat", methodNames={"printChatMessageWithOptionalDeletion", "printChatMessageWithOptionalDeletion"}, desc="(Lnet/minecraft/util/IChatComponent;I)V")
    public static class printChatMessageWithOptionalDeletionTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            il.insertBefore(il.getFirst(), (AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/module/impl/chat/BetterChat", "resetPercentComplete", "()V", false));
        }
    }

    @TransformerManager.TransformTarget(className="net.minecraft.client.gui.GuiNewChat", methodNames={"drawChat", "drawChat"}, desc="(I)V")
    public static class drawChatTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            il.insertBefore(il.getFirst(), (AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/module/impl/chat/BetterChat", "updatePercentage", "()V", false));
            for (AbstractInsnNode node : il.toArray()) {
                if (!(node instanceof MethodInsnNode)) continue;
                MethodInsnNode mi = (MethodInsnNode)node;
                if (node.getOpcode() == 184) {
                    if ("translate".equals(mi.name) || "translate".equals(mi.name)) {
                        mi.owner = "com/domcer/uview/module/impl/chat/BetterChat";
                        mi.name = "translate";
                        continue;
                    }
                    if (!"drawRect".equals(mi.name) && !"drawRect".equals(mi.name)) continue;
                    mi.owner = "com/domcer/uview/module/impl/chat/BetterChat";
                    mi.name = "drawRect";
                    continue;
                }
                if (node.getOpcode() != 182 || !"drawStringWithShadow".equals(mi.name) && !"drawStringWithShadow".equals(mi.name)) continue;
                ((MethodInsnNode)node).setOpcode(184);
                mi.owner = "com/domcer/uview/module/impl/chat/BetterChat";
                mi.name = "drawStringWithShadow";
                mi.desc = "(Ljava/lang/String;FFII)V";
                il.insertBefore(node, (AbstractInsnNode)new VarInsnNode(21, 9));
            }
        }
    }
}

