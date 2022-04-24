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

public class BossbarTransformer {

    @TransformerManager.TransformTarget(className="net.minecraft.client.gui.GuiIngame", methodNames={"renderBossHealth", "renderBossHealth"}, desc="()V")
    public static class renderBossbarHealthTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            mn.instructions.clear();
            mn.localVariables.clear();
            InsnList list = new InsnList();
            list.add((AbstractInsnNode)new MethodInsnNode(184, "com/domcer/uview/module/impl/game/Bossbar", "renderBossbarHealth", "()V", false));
            list.add((AbstractInsnNode)new InsnNode(177));
            mn.instructions.insert(list);
        }
    }
}

