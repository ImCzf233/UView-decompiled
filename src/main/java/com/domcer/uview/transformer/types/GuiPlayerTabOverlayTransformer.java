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
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class GuiPlayerTabOverlayTransformer {

    @TransformerManager.TransformTarget(className="net.minecraft.client.gui.GuiPlayerTabOverlay", methodNames={"drawScoreboardValues", "drawScoreboardValues"}, desc="(Lnet/minecraft/scoreboard/ScoreObjective;ILjava/lang/String;IILnet/minecraft/client/network/NetworkPlayerInfo;)V")
    public static class drawScoreboardValuesTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            for (AbstractInsnNode node : il.toArray()) {
                if (!(node instanceof FieldInsnNode) || node.getOpcode() != 178) continue;
                FieldInsnNode fn = (FieldInsnNode)node;
                if (!"net/minecraft/util/EnumChatFormatting".equals(fn.owner) || !"YELLOW".equals(fn.name) || !"Lnet/minecraft/util/EnumChatFormatting;".equals(fn.desc)) continue;
                InsnList list = new InsnList();
                list.add((AbstractInsnNode)new VarInsnNode(25, 6));
                list.add((AbstractInsnNode)new VarInsnNode(21, 7));
                list.add((AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/module/impl/misc/TabHealthColor", "getColor", "(Lnet/minecraft/client/network/NetworkPlayerInfo;I)Lnet/minecraft/util/EnumChatFormatting;", false));
                mn.instructions.insertBefore(node, list);
                mn.instructions.remove(node);
            }
        }
    }

    @TransformerManager.TransformTarget(className="net.minecraft.client.gui.GuiPlayerTabOverlay", methodNames={"renderPlayerlist", "renderPlayerlist"}, desc="(ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreObjective;)V")
    public static class renderPlayerlistTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            for (AbstractInsnNode node : il.toArray()) {
                IntInsnNode in;
                if (!(node instanceof IntInsnNode) || (in = (IntInsnNode)node).getOpcode() != 16) continue;
                if (in.operand == 20) {
                    in.operand = 25;
                }
                if (in.operand != 80) continue;
                in.operand = 100;
            }
        }
    }
}

