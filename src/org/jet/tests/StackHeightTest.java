package org.jet.tests;

import org.jet.optimizations.analysis.StackHeightTracker;
import org.jet.tests.debug.InstructionNames;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class StackHeightTest implements Opcodes {

    private MethodNode method;

    public MethodNode getMethod() {
        return method;
    }

    public void initMethod() {
        method = new MethodNode();
        LabelNode ln1 = new LabelNode();
        method.instructions.add(new InsnNode(ICONST_0));
        method.instructions.add(new InsnNode(ICONST_1));
        method.instructions.add(new InsnNode(IADD));
        method.instructions.add(new IntInsnNode(BIPUSH, 30));
        method.instructions.add(new InsnNode(IMUL));
        method.instructions.add(new IntInsnNode(BIPUSH, 30));
        method.instructions.add(new JumpInsnNode(IF_ICMPEQ, ln1));
        method.instructions.add(new InsnNode(ICONST_1));
        method.instructions.add(new InsnNode(ICONST_2));
        method.instructions.add(new JumpInsnNode(IF_ICMPEQ, ln1));
        method.instructions.add(new InsnNode(ICONST_0));
        method.instructions.add(new InsnNode(POP));
        method.instructions.add(ln1);
    }

    public static void main(String[] args) {
        StackHeightTest test = new StackHeightTest();
        test.initMethod();
        System.out.println(StackHeightTracker.getHeight(test.getMethod().instructions.get(11), test.getMethod()));
    }
}
