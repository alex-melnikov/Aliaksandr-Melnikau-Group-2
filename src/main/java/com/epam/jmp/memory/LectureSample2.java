package com.epam.jmp.memory;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siarhei_Komlik on 10/20/14.
 */
public class LectureSample2 {

    static final Logger logger = Logger.getLogger(LectureSample2.class);

    public static void main(String[] args) {
        int a = 0;
        int[] b = {20};
        a = f(a, g(a, b));
        logger.info(a + " " + b[0]);

        //OutOfMemoryError:
        //long[] s = new long[Integer.MAX_VALUE];


        // Sample 4
        /*while(true) {
            new Object();
        }*/

        // Sample 5
        /*Object obj;
        while (true) {
            obj = new Object();
        }*/

        // Sample 6
        /*List<Object> list = new ArrayList();
        while (true) {
            list.add(new long[3000]);
            *//*if (list.size()>100){
                logger.info(list.size());
                list.clear();
            }*//*
        }*/

        // Sample 7
        /*Object[] ref = new Object[1];
        while (true) {
            Object[] a = new Object[1];
            Object[] b = new Object[1];
            a[0] = b;
            b[0] = a;
            ref[0] = a;
        }*/



    }

    private static int f(int a, int[] b) {
        a += 30;
        b[0] = 40;
        return 42;
    }

    private static int[] g(int a, int[] b) {
        a = 50;
        b = new int[]{60};
        return b;
    }


}
