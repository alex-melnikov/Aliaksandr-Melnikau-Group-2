package com.epam.jmp.memory;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by Siarhei_Komlik on 10/20/14.
 */
public class HeapOOM {

    private static final Logger logger = Logger.getLogger(HeapOOM.class);

    public static void main(String[] args) {
        List v = new ArrayList();
        while (true) {
            byte b[] = new byte[1048576];
            v.add(b);
            Runtime rt = Runtime.getRuntime();

            System.out.println("free memory: " + rt.freeMemory());
        }
    }

}
