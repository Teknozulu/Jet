package org.jet.tests.debug;

import org.objectweb.asm.Opcodes;

import java.lang.reflect.Field;

public class InstructionNames implements Opcodes {

    private static Field[] fields = Opcodes.class.getDeclaredFields();

    public static String getName(int opcode) {
        for (Field f : fields) {
            if (f.getType().toString().equals("int")) {
                if (f.getName().startsWith("ACC"))
                    continue;
                if (f.getName().contains("_")) {
                    if (f.getName().indexOf("_") < 2)
                        continue;
                }
                f.setAccessible(true);
                try {
                    if (f.getInt(null) == opcode) {
                        return f.getName();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "UNKNOWN: " + opcode;
    }
}
