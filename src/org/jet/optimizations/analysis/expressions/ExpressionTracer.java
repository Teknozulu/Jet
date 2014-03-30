package org.jet.optimizations.analysis.expressions;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

import java.util.ArrayList;
import java.util.List;


public class ExpressionTracer implements Opcodes {


    /*  This class traces up from a bytecode instruction until the "expression" is complete e.g


        iconst_0
        invokestatic (I)I
        bipush
        mul

        1. add 2 to stack from mul
        2. bipush doesnt require anything, 2-1=1
        3. the invoke needs another int, 1-1=0, 0+1=1
        4. iconst_0 requires nothing, 1-1=0, expression is complete
     */

    private static AbstractInsnNode getPrevious(AbstractInsnNode ain) {
        AbstractInsnNode prev = ain.getPrevious();
        if (prev.getOpcode() == -1)
            prev = prev.getPrevious();
        return prev;
    }

    // The last array element is the last instruction of the previous expression
    public static List<AbstractInsnNode> traceToPreviousExpression(AbstractInsnNode insn) {
        List<AbstractInsnNode> insns = new ArrayList();
        int length = ArgumentSizes.instructions[insn.getOpcode()];
        AbstractInsnNode prev = getPrevious(insn);
        insns.add(insn);
        insns.add(prev);
        while (length > 0) {
            length--;
            int add;
            if (prev instanceof MethodInsnNode) {
                MethodInsnNode min = (MethodInsnNode)prev;
                add = Type.getArgumentTypes(min.desc).length;
                if (min.getOpcode() == INVOKEVIRTUAL)
                    add++; // object reference
            } else {
                add = ArgumentSizes.instructions[prev.getOpcode()];
            }
            length += add;
            if (length == 0)
                break;
            // Let's have the list end at the first instruction of the entire expression, since we can't guarantee
            // there are more instructions up the method.
            prev = getPrevious(prev);
            insns.add(prev);
        }
        return insns;
    }
}
