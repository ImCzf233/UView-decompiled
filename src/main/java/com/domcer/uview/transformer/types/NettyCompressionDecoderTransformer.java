/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.MethodNode
 */
package com.domcer.uview.transformer.types;

import com.domcer.uview.transformer.TransformerManager;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class NettyCompressionDecoderTransformer {

    @TransformerManager.TransformTarget(className="ei", methodNames={"decode"}, desc="(Lio/netty/channel/ChannelHandlerContext;Lio/netty/buffer/ByteBuf;Ljava/util/List;)V")
    public static class eidecodeTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            for (AbstractInsnNode node : il.toArray()) {
                if (!(node instanceof LdcInsnNode)) continue;
                LdcInsnNode ln = (LdcInsnNode)node;
                if (!(ln.cst instanceof Integer) || (Integer)ln.cst != 0x200000) continue;
                ln.cst = 0x1000000;
            }
        }
    }

    @TransformerManager.TransformTarget(className="net.minecraft.network.NettyCompressionDecoder", methodNames={"decode"}, desc="(Lio/netty/channel/ChannelHandlerContext;Lio/netty/buffer/ByteBuf;Ljava/util/List;)V")
    public static class decodeTransformer
    implements TransformerManager.IMethodTransformer {
        @Override
        public void transform(ClassNode cn, MethodNode mn) {
            InsnList il = mn.instructions;
            for (AbstractInsnNode node : il.toArray()) {
                if (!(node instanceof LdcInsnNode)) continue;
                LdcInsnNode ln = (LdcInsnNode)node;
                if (!(ln.cst instanceof Integer) || (Integer)ln.cst != 0x200000) continue;
                ln.cst = 0x1000000;
            }
        }
    }
}

