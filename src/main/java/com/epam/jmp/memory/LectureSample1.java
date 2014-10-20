package com.epam.jmp.memory;

import org.apache.log4j.Logger;

/**
 * Created by Siarhei_Komlik on 10/20/14.
 */
public class LectureSample1 {

    static final Logger logger = Logger.getLogger(LectureSample1.class);

    public static void main(String[] args) {

        int a = 0;
        int[] b = {20};
        f(a, b);
        logger.info(a + " " + b[0]);
        g(a, b);
        logger.info(a + " " + b[0]);
    }

    private static void f(int a, int[] b) {
        a += 30;
        b[0] = 40;
    }

    private static void g(int a, int[] b) {
        a = 50;
        b = new int[]{60};
    }
}
