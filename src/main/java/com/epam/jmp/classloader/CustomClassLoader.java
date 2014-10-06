package com.epam.jmp.classloader;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Siarhei_Komlik on 10/3/14.
 */
public class CustomClassLoader extends ClassLoader {

    public static final Logger LOGGER = Logger.getLogger(CustomClassLoader.class);

    /** The HashMap where the classes will be cached */
    private Map<String, Class<?>> classes = new ConcurrentHashMap<String, Class<?>>();
    private String jarName = "";

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    public CustomClassLoader() {
        this(CustomClassLoader.class.getClassLoader());
    }

    public CustomClassLoader(String jarName) {
        this();
        this.jarName = jarName;
    }

    @Override
    public Class loadClass(String className) throws ClassNotFoundException {
        //check if the class is already loaded
        return findClass(className);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        LOGGER.info("Finding class - " + name);
        Class result = (Class) classes.get(name); //checks in cached classes
        if (result != null) {
            LOGGER.info("% Class " + name + " found in cache");
            return result;
        }

        try {
            result = findSystemClass(name);
            LOGGER.info("Class " + name + " found in system class loader");
            return result;
        } catch (Exception e) {
            LOGGER.info("Class " + name + "  not found in system class loader");
        }
        JarResources jr = new JarResources(jarName);
        byte[] classByte = jr.getResource(name.replace('.', '/') + ".class");

        if (classByte == null) {
            LOGGER.info("Nothing to find in jar file");
            new ClassNotFoundException();
            return null;
        }
        result = defineClass(name, classByte, 0, classByte.length, null);
        classes.put(name, result);
        LOGGER.info("Class " + name + " was add to cash of custom class loader");
        return result;
    }
}
