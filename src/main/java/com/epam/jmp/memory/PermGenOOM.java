package com.epam.jmp.memory;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Siarhei_Komlik on 10/20/14.
 */
public class PermGenOOM {

    static final Logger logger = Logger.getLogger(PermGenOOM.class);

    public static void main(String[] args) throws Exception {
        Class<?> clazz = PermGenOOM.class;
        byte[] buffer = loadByteCode(clazz, clazz.getName());
        MemoryClassLoader loader = new MemoryClassLoader();

        for (long index = 0; index < Long.MAX_VALUE; index++) {
            String newClassName = "_" + String.format("%0"
                    + (clazz.getSimpleName().length() - 1) + "d", index);
            byte[] newClassData = new String(buffer, "latin1")
                    .replaceAll(clazz.getSimpleName(), newClassName).getBytes("latin1");
            loader._defineClass(clazz.getName().replace(clazz.getSimpleName(), newClassName), newClassData);
        }
    }

    public static byte[] loadByteCode(Class loader, String className)
            throws IOException {
        String fileName = "/" + className.replaceAll("\\.", "/") + ".class";
        InputStream is = loader.getResourceAsStream(fileName);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(4096);
        byte[] buff = new byte[1024];
        int i;
        try {
            while ((i = is.read(buff)) >= 0) {
                buffer.write(buff, 0, i);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (buffer != null) {
                buffer.close();
            }
        }
        return buffer.toByteArray();
    }
}
