package org.jet.optimizations.analysis.expressions;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

import java.util.Collections;
import java.util.List;

// This class identifies the group of instruction corresponding to each method argument
public class ArgumentReader {

    private AbstractInsnNode[][] argumentBlocks;

    // Could've gone static here but I feel like this will be convenient for encapsulation in the event that
    // I'll ever want just a specific argument insn block, instead of all of them.
    public ArgumentReader(MethodInsnNode target) {
        this.argumentBlocks = new AbstractInsnNode[Type.getArgumentTypes(target.desc).length][];
        AbstractInsnNode start = null;//Tools.getPrevious(target);
        for (int i = 0; i < argumentBlocks.length; i++) {
            List<AbstractInsnNode> instructions = ExpressionTracer.traceToPreviousExpression(start);
            // Might aswell reverse it now since I will need the instructions in insertion order at some point
            Collections.reverse(instructions);
            // The last instruction of the previous expression is the last instruction of this list
            start = instructions.remove(0);
            argumentBlocks[i] = instructions.toArray(new AbstractInsnNode[instructions.size()]);
        }
    }

    public AbstractInsnNode[] getBlock(int i) {
        return argumentBlocks[i];
    }


}
