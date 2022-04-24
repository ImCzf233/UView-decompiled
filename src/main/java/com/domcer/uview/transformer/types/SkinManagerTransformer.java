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
 *  org.objectweb.asm.tree.JumpInsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package com.domcer.uview.transformer.types;

import com.domcer.uview.transformer.TransformerManager;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class SkinManagerTransformer {

    @TransformerManager.TransformTarget(className="com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService", methodNames={"getTextures"}, desc="(Lcom/mojang/authlib/GameProfile;Z)Ljava/util/Map;")
    public static class getTexturesTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            MethodInsnNode mi;
            boolean flag = false;
            for (AbstractInsnNode node : mn.instructions.toArray()) {
                if (!(node instanceof MethodInsnNode)) continue;
                mi = (MethodInsnNode)node;
                if (!"getTexturesWrapper".equals(mi.name)) continue;
                flag = true;
                break;
            }
            if (flag) {
                for (AbstractInsnNode node : mn.instructions.toArray()) {
                    if (!(node instanceof MethodInsnNode)) continue;
                    mi = (MethodInsnNode)node;
                    if (!"getTexturesWrapper".equals(mi.name)) continue;
                    mi.owner = "com/domcer/uview/skin/SkinManager";
                    break;
                }
                return;
            }
            for (AbstractInsnNode node : mn.instructions.toArray()) {
                if (!(node instanceof LabelNode)) continue;
                InsnList il = new InsnList();
                il.add((AbstractInsnNode)new VarInsnNode(25, 1));
                il.add((AbstractInsnNode)new VarInsnNode(21, 2));
                il.add((AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/skin/SkinManager", "getTexturesWrapper", "(Lcom/mojang/authlib/GameProfile;Z)Ljava/util/Map;", false));
                il.add((AbstractInsnNode)new VarInsnNode(58, 3));
                il.add((AbstractInsnNode)new VarInsnNode(25, 3));
                il.add((AbstractInsnNode)new JumpInsnNode(198, (LabelNode)node));
                il.add((AbstractInsnNode)new VarInsnNode(25, 3));
                il.add((AbstractInsnNode)new InsnNode(176));
                mn.instructions.insertBefore(mn.instructions.getFirst(), il);
                break;
            }
        }
    }

    @TransformerManager.TransformTarget(className="net.minecraft.client.resources.SkinManager", methodNames={"loadSkinFromCache", "loadSkinFromCache"}, desc="(Lcom/mojang/authlib/GameProfile;)Ljava/util/Map;")
    public static class loadSkinFromCacheTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            MethodInsnNode mi;
            boolean flag = false;
            for (AbstractInsnNode node : mn.instructions.toArray()) {
                if (!(node instanceof MethodInsnNode)) continue;
                mi = (MethodInsnNode)node;
                if (!"loadSkinFromCacheWrapper".equals(mi.name)) continue;
                flag = true;
                break;
            }
            if (flag) {
                for (AbstractInsnNode node : mn.instructions.toArray()) {
                    if (!(node instanceof MethodInsnNode)) continue;
                    mi = (MethodInsnNode)node;
                    if (!"loadSkinFromCacheWrapper".equals(mi.name)) continue;
                    mi.owner = "com/domcer/uview/skin/SkinManager";
                    break;
                }
                return;
            }
            for (AbstractInsnNode node : mn.instructions.toArray()) {
                if (!(node instanceof LabelNode)) continue;
                InsnList il = new InsnList();
                il.add((AbstractInsnNode)new VarInsnNode(25, 1));
                il.add((AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/skin/SkinManager", "loadSkinFromCacheWrapper", "(Lcom/mojang/authlib/GameProfile;)Ljava/util/Map;", false));
                il.add((AbstractInsnNode)new VarInsnNode(58, 2));
                il.add((AbstractInsnNode)new VarInsnNode(25, 2));
                il.add((AbstractInsnNode)new JumpInsnNode(198, (LabelNode)node));
                il.add((AbstractInsnNode)new VarInsnNode(25, 2));
                il.add((AbstractInsnNode)new InsnNode(176));
                mn.instructions.insertBefore(mn.instructions.getFirst(), il);
                break;
            }
        }
    }
}

