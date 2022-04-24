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
 *  org.objectweb.asm.tree.FieldNode
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
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class NetworkPlayerInfoTransformer {

    @TransformerManager.TransformTarget(className="net.minecraft.client.network.NetworkPlayerInfo", methodNames={"getLocationCape", "getLocationCape"}, desc="()Lnet/minecraft/util/ResourceLocation;")
    public static class getLocationCapeTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            boolean flag = false;
            for (FieldNode fd : cn.fields) {
                if (!"gameProfile".equals(fd.name) || !"Lcom/mojang/authlib/GameProfile;".equals(fd.desc)) continue;
                flag = true;
                break;
            }
            for (AbstractInsnNode node : mn.instructions.toArray()) {
                if (!(node instanceof LabelNode)) continue;
                InsnList il = new InsnList();
                il.add((AbstractInsnNode)new VarInsnNode(25, 0));
                il.add((AbstractInsnNode)new FieldInsnNode(180, "net/minecraft/client/network/NetworkPlayerInfo", flag ? "gameProfile" : "gameProfile", "Lcom/mojang/authlib/GameProfile;"));
                il.add((AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/skin/SkinManager", "getLocationCape", "(Lcom/mojang/authlib/GameProfile;)Lnet/minecraft/util/ResourceLocation;", false));
                il.add((AbstractInsnNode)new VarInsnNode(58, 1));
                il.add((AbstractInsnNode)new VarInsnNode(25, 1));
                il.add((AbstractInsnNode)new JumpInsnNode(198, (LabelNode)node));
                il.add((AbstractInsnNode)new VarInsnNode(25, 1));
                il.add((AbstractInsnNode)new InsnNode(176));
                mn.instructions.insertBefore(mn.instructions.getFirst(), il);
                break;
            }
        }
    }

    @TransformerManager.TransformTarget(className="net.minecraft.client.network.NetworkPlayerInfo", methodNames={"getLocationSkin", "getLocationSkin"}, desc="()Lnet/minecraft/util/ResourceLocation;")
    public static class getLocationSkinTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            boolean flag = false;
            for (FieldNode fd : cn.fields) {
                if (!"gameProfile".equals(fd.name) || !"Lcom/mojang/authlib/GameProfile;".equals(fd.desc)) continue;
                flag = true;
                break;
            }
            for (AbstractInsnNode node : mn.instructions.toArray()) {
                if (!(node instanceof LabelNode)) continue;
                InsnList il = new InsnList();
                il.add((AbstractInsnNode)new VarInsnNode(25, 0));
                il.add((AbstractInsnNode)new FieldInsnNode(180, "net/minecraft/client/network/NetworkPlayerInfo", flag ? "gameProfile" : "gameProfile", "Lcom/mojang/authlib/GameProfile;"));
                il.add((AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/skin/SkinManager", "getLocationSkin", "(Lcom/mojang/authlib/GameProfile;)Lnet/minecraft/util/ResourceLocation;", false));
                il.add((AbstractInsnNode)new VarInsnNode(58, 1));
                il.add((AbstractInsnNode)new VarInsnNode(25, 1));
                il.add((AbstractInsnNode)new JumpInsnNode(198, (LabelNode)node));
                il.add((AbstractInsnNode)new VarInsnNode(25, 1));
                il.add((AbstractInsnNode)new InsnNode(176));
                mn.instructions.insertBefore(mn.instructions.getFirst(), il);
                break;
            }
        }
    }
}

