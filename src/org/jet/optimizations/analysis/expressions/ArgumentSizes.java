package org.jet.optimizations.analysis.expressions;

import org.objectweb.asm.Opcodes;

// Here is the list of the number of stack items each bytecode instruction takes as arguments
public class ArgumentSizes implements Opcodes {

    public static int[] instructions = new int[1000];

    static {
        instructions[AALOAD] = 2;
        instructions[AASTORE] = 3;
        instructions[ACONST_NULL] = 0;
        instructions[ALOAD] = 0;
        instructions[ANEWARRAY] = 2;
        instructions[ARETURN] = 1;
        instructions[ARRAYLENGTH] = 1;
        instructions[ASTORE] = 1;
        instructions[ATHROW] = 1;
        instructions[BALOAD] = 2;
        instructions[BASTORE] = 3;
        instructions[BIPUSH] = 0;
        instructions[CALOAD] = 2;
        instructions[CASTORE] = 3;
        instructions[CHECKCAST] = 1;
        instructions[D2F] = 1;
        instructions[D2I] = 1;
        instructions[D2L] = 1;
        instructions[DADD] = 2;
        instructions[DALOAD] = 2;
        instructions[DASTORE] = 3;
        instructions[DCMPG] = 2;
        instructions[DCMPL] = 2;
        instructions[DCONST_0] = 0;
        instructions[DCONST_1] = 0;
        instructions[DDIV] = 2;
        instructions[DLOAD] = 0;
        instructions[DMUL] = 2;
        instructions[DNEG] = 1;
        instructions[DREM] = 2;
        instructions[DRETURN] = 1;
        instructions[DSTORE] = 1;
        instructions[DSUB] = 2;
        instructions[DUP] = 1;
        instructions[DUP_X1] = 1;
        instructions[DUP_X2] = 1;
        instructions[DUP2] = 2;
        instructions[DUP2_X1] = 2;
        instructions[DUP2_X2] = 2;
        instructions[F2D] = 1;
        instructions[F2I] = 1;
        instructions[F2L] = 1;
        instructions[FADD] = 2;
        instructions[FALOAD] = 2;
        instructions[FASTORE] = 3;
        instructions[FCMPG] = 2;
        instructions[FCMPL] = 2;
        instructions[FCONST_0] = 0;
        instructions[FCONST_1] = 0;
        instructions[FCONST_2] = 0;
        instructions[FDIV] = 2;
        instructions[FLOAD] = 0;
        instructions[FMUL] = 2;
        instructions[FNEG] = 1;
        instructions[FREM] = 2;
        instructions[FRETURN] = 1;
        instructions[FSTORE] = 1;
        instructions[FSUB] = 2;
        instructions[GETFIELD] = 1;
        instructions[GETSTATIC] = 0;
        instructions[GOTO] = 0;
        instructions[I2B] = 1;
        instructions[I2C] = 1;
        instructions[I2D] = 1;
        instructions[I2F] = 1;
        instructions[I2L] = 1;
        instructions[I2S] = 1;
        instructions[IADD] = 2;
        instructions[IALOAD] = 2;
        instructions[IAND] = 2;
        instructions[IASTORE] = 3;
        instructions[ICONST_M1] = 0;
        instructions[ICONST_0] = 0;
        instructions[ICONST_1] = 0;
        instructions[ICONST_2] = 0;
        instructions[ICONST_3] = 0;
        instructions[ICONST_4] = 0;
        instructions[ICONST_5] = 0;
        instructions[IDIV] = 2;
        instructions[IF_ACMPEQ] = 2;
        instructions[IF_ACMPNE] = 2;
        instructions[IF_ICMPEQ] = 2;
        instructions[IF_ICMPGE] = 2;
        instructions[IF_ICMPGT] = 2;
        instructions[IF_ICMPLE] = 2;
        instructions[IF_ICMPLT] = 2;
        instructions[IF_ICMPNE] = 2;
        instructions[IFEQ] = 1;
        instructions[IFGE] = 1;
        instructions[IFGT] = 1;
        instructions[IFLE] = 1;
        instructions[IFLT] = 1;
        instructions[IFNE] = 1;
        instructions[IFNONNULL] = 1;
        instructions[IFNULL] = 1;
        instructions[IINC] = 0;
        instructions[ILOAD] = 0;
        instructions[IMUL] = 2;
        instructions[INEG] = 1;
        instructions[INSTANCEOF] = 1;
        instructions[INVOKEDYNAMIC] = -1;
        instructions[INVOKESTATIC] = -1;
        instructions[INVOKEVIRTUAL] = -1;
        instructions[IOR] = 2;
        instructions[IREM] = 2;
        instructions[IRETURN] = 1;
        instructions[ISHL] = 2;
        instructions[ISHR] = 2;
        instructions[ISTORE] = 1;
        instructions[ISUB] = 2;
        instructions[IUSHR] = 2;
        instructions[IXOR] = 2;
        instructions[JSR] = 0;
        instructions[L2D] = 1;
        instructions[L2F] = 1;
        instructions[L2I] = 1;
        instructions[LADD] = 2;
        instructions[LALOAD] = 2;
        instructions[LAND] = 2;
        instructions[LASTORE] = 3;
        instructions[LCMP] = 2;
        instructions[LCONST_0] = 0;
        instructions[LCONST_1] = 0;
        instructions[LDC] = 0;
        instructions[LDIV] = 2;
        instructions[LLOAD] = 0;
        instructions[LMUL] = 2;
        instructions[LNEG] = 1;
        //instructions[LOOKUPSWITCH]
        //instructions[TABLESWITCH]
        instructions[LOR] = 2;
        instructions[LREM] = 2;
        instructions[LRETURN] = 1;
        instructions[LSHL] = 2;
        instructions[LSHR] = 2;
        instructions[LSTORE] = 1;
        instructions[LSUB] = 2;
        instructions[LUSHR] = 2;
        instructions[LXOR] = 2;
        instructions[NEW] = 0;
        instructions[NEWARRAY] = 1;
        instructions[NOP] = 0;
        instructions[POP] = 1;
        instructions[POP2] = 2;
        instructions[PUTFIELD] = 2;
        instructions[PUTSTATIC] = 1;
        instructions[RET] = 0;
        instructions[RETURN] = 0;
        instructions[SALOAD] = 2;
        instructions[SASTORE] = 3;
        instructions[SIPUSH] = 0;
        instructions[SWAP] = 2;
        //instructions[TABLESWITCH]

    }
}