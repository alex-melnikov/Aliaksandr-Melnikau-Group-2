package com.epam.jmp.memory;

/**
 * Created by Siarhei_Komlik on 10/20/14.
 */
public class MemoryClassLoader extends ClassLoader {

    public MemoryClassLoader() {
        super();
    }

    public Class<?> _defineClass(String name, byte[] byteCodes) {
        return super.defineClass(name, byteCodes, 0, byteCodes.length);
    }

}
