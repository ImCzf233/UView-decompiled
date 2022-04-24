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
 *  org.objectweb.asm.tree.VarInsnNode
 */
package com.domcer.uview.transformer.types;

import com.domcer.uview.transformer.TransformerManager;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class RenderEntityItemTransformer {

    @TransformerManager.TransformTarget(className="net.minecraft.client.renderer.entity.RenderEntityItem", methodNames={"doRender", "doRender"}, desc="(Lnet/minecraft/entity/item/EntityItem;DDDFF)V")
    public static class doRenderTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            for (AbstractInsnNode node : il.toArray()) {
                if (!(node instanceof MethodInsnNode)) continue;
                MethodInsnNode mi = (MethodInsnNode)node;
                if (node.getOpcode() != 184 || !"enableRescaleNormal".equals(mi.name) && !"enableRescaleNormal".equals(mi.name)) continue;
                InsnList list = new InsnList();
                list.add((AbstractInsnNode)new VarInsnNode(25, 0));
                list.add((AbstractInsnNode)new VarInsnNode(25, 1));
                list.add((AbstractInsnNode)new VarInsnNode(24, 2));
                list.add((AbstractInsnNode)new VarInsnNode(24, 4));
                list.add((AbstractInsnNode)new VarInsnNode(24, 6));
                list.add((AbstractInsnNode)new VarInsnNode(23, 8));
                list.add((AbstractInsnNode)new VarInsnNode(23, 9));
                list.add((AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/module/impl/game/ItemPhysics", "doRender", "(Lnet/minecraft/client/renderer/entity/RenderEntityItem;Lnet/minecraft/entity/Entity;DDDFF)V", false));
                list.add((AbstractInsnNode)new InsnNode(177));
                mn.instructions.insertBefore(node, list);
                break;
            }
        }
    }
}

