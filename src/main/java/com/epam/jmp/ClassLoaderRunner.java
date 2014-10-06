package com.epam.jmp;

import com.epam.jmp.classloader.CustomClassLoader;
import com.epam.jmp.classloader.ITestModule;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Siarhei_Komlik on 10/3/14.
 */
public class ClassLoaderRunner {

    public static final Logger LOGGER = Logger.getLogger(ClassLoaderRunner.class);

    private static final String FIRST_FILE_NAME = "jar/TestJar1.jar";
    private static final String SECOND_FILE_NAME = "jar/TestJar2.jar";
    private static final String FIRST_CLASS_NAME = "com.epam.jmp.classloader.FirstTestModule";
    private static final String SECOND_CLASS_NAME = "com.epam.jmp.classloader.SecondTestModule";

    public static void main(String[] args) throws IOException {
        System.out.println("==== JMP. Module 1. Classloading. ====");

        // print menu
        for (MenuItem item : MenuItem.values()) {
            System.out.println(item.getDesc());
        }

        ClassLoader loader1 = new CustomClassLoader(FIRST_FILE_NAME);
        ClassLoader loader2 = new CustomClassLoader(SECOND_FILE_NAME);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MenuItem menuItem = MenuItem.WAITING;

        while (menuItem != MenuItem.EXIT) {
            System.out.print("Please choose option: ");
            menuItem = MenuItem.findMenuByCode(getIntInput(br));

            switch (menuItem) {
                case WAITING:
                    break;
                case LOAD_FIRST_JAR:
                    findAndPerformClass(loader1, FIRST_CLASS_NAME);
                    break;
                case LOAD_SECOND_JAR:
                    findAndPerformClass(loader2, SECOND_CLASS_NAME);
                    break;
                case RELOAD_FIRST_JAR:
                    loader1 = new CustomClassLoader(FIRST_FILE_NAME);
                    findAndPerformClass(loader1, FIRST_CLASS_NAME);
                    break;
                case LOAD_CUSTOM_JAR:
                    System.out.println("Please enter path to jar:");
                    String pathToJar = getStringInput(br);
                    try {
                        JarFile jar = new JarFile(pathToJar.replace("/", "\\"));
                        Enumeration e = jar.entries();
                        System.out.println("Classes in jar:");
                        while (e.hasMoreElements()) {
                            JarEntry je = (JarEntry) e.nextElement();
                            if(je.isDirectory() || !je.getName().endsWith(".class")){
                                continue;
                            }
                            // -6 because of .class
                            System.out.println(je.getName().substring(0, je.getName().length() - 6).replace('/', '.'));
                        }
                        System.out.println("Please choose class:");
                        String className = getStringInput(br).replace('/', '.');
                        ClassLoader loader3 = new CustomClassLoader(pathToJar);
                        //className = className.replace('/', '.');
                        Class clazz = loader3.loadClass(className);
                        Method[] m = clazz.getDeclaredMethods();
                        System.out.println("All methods of class:");
                        for (int i = 0; i < m.length; i++){
                            System.out.println(m[i].toString());
                        }
                        System.out.println("Please choose method to perform (only name of method):");
                        String methodStr = getStringInput(br);
                        Object obj = clazz.newInstance();
                        Method method = clazz.getDeclaredMethod(methodStr, null);
                        method.invoke(obj, null);
                    } catch (FileNotFoundException ex) {
                        LOGGER.info("Can't find JAR file by path - " + pathToJar);
                        break;
                    } catch (ClassNotFoundException e) {
                        LOGGER.info("Can't find class");
                        break;
                    } catch (NoSuchMethodException e) {
                        LOGGER.info("Can't find method");
                        break;
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                    break;
                case EXIT:
                    System.out.println("The end. Thanks.");
                    break;
            }
        }
    }

    private static void findAndPerformClass(ClassLoader loader, String className) {
        try {
            Class clazz1 = Class.forName(className, true, loader);
            ITestModule object1 = (ITestModule) clazz1.newInstance();
            object1.sayHello();
            LOGGER.info(object1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static int getIntInput(BufferedReader br) throws IOException {
        try{
            return Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format! Please enter integer value.");
            System.out.println("Please choose option: ");
            getIntInput(br);
        }
        return 0;
    }

    private static String getStringInput(BufferedReader br) throws IOException {
        try{
            return br.readLine();
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format! Please retry");
            getStringInput(br);
        }
        return null;
    }

    private enum MenuItem{
        WAITING(0, "Menu:"),
        LOAD_FIRST_JAR(1, "1. Load data from first jar"),
        LOAD_SECOND_JAR(2, "2. Load data from second jar"),
        RELOAD_FIRST_JAR(3, "3. Reload data from first jar"),
        LOAD_CUSTOM_JAR(4, "4. Load custom jar"),
        EXIT(5, "5. Exit");

        private final Integer code;
        private final String desc;

        MenuItem(Integer i, String desc) {
            this.code = i;
            this.desc = desc;
        }

        static MenuItem findMenuByCode(Integer i) {
            for (MenuItem value : values()) {
                if (i == 0) {
                    break;
                }
                if (i == value.code) {
                    return value;
                }
            }
            System.out.println("Number is incorrect. Please retry");
            return WAITING;
        }

        public String getDesc() {
            return desc;
        }
    }
}
