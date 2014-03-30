package org.jet.optimizations.analysis;

import org.jet.optimizations.analysis.expressions.ArgumentSizes;
import org.jet.optimizations.analysis.expressions.YieldSizes;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Stack;

public class StackHeightTracker implements Opcodes {

    // Calculate the change in stack height after an instruction
    private static int calculateChange(AbstractInsnNode insn) {
        int args = ArgumentSizes.instructions[insn.getOpcode()];
        int yield = YieldSizes.instructions[insn.getOpcode()];
        if (insn instanceof MethodInsnNode) {
            MethodInsnNode min = (MethodInsnNode)insn;
            args = Type.getArgumentTypes(min.desc).length;
            if (min.getOpcode() == INVOKEVIRTUAL)
                args++; // object reference
            yield = ((MethodInsnNode)insn).desc.endsWith("V") ? 0 : 1;
        }
        return yield - args;
    }

    private static AbstractInsnNode jump(AbstractInsnNode label, MethodNode mn) {
        try {
            return mn.instructions.get(mn.instructions.indexOf(label) + 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private static AbstractInsnNode getNext(AbstractInsnNode ain) {
        AbstractInsnNode next = ain.getNext();
        if (ain.getOpcode() == -1) {
            next = next.getNext();
        }
        return next;
    }

    // Properly trace the code to our target instruction, get stack height after it's been executed
    public static int getHeight(AbstractInsnNode insn, MethodNode method) {
        Stack<AbstractInsnNode> branches = new Stack<AbstractInsnNode>();
        int height = 0;
        branches.push(method.instructions.get(0));
        outie: while (branches.size() > 0) {
            AbstractInsnNode current = branches.pop();
            if (current == null)
                continue;
            height += calculateChange(current);
            AbstractInsnNode next = getNext(current);
            do {
                height += calculateChange(next);
                if (next.equals(insn)) {
                    break outie;
                }
                if (next instanceof JumpInsnNode) {
                    branches.push(getNext(next));
                    branches.push(jump(((JumpInsnNode) next).label, method));
                    break;
                }
            } while ((next = getNext(next)) != null);
        }
        return height;
    }
}
