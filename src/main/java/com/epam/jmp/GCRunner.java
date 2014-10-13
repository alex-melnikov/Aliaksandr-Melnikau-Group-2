package com.epam.jmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Siarhei_Komlik on 10/11/14.
 */
public class GCRunner {

    private static List objects = new ArrayList();

    public static void main(String[] args) {
        System.out.println("==== JMP. Module 2. Garbage Collection. ====");

        // print menu
        for (MenuItem item : MenuItem.values()) {
            System.out.println(item.getDesc());
        }

        Scanner sc = new Scanner(System.in);
        MenuItem menuItem = MenuItem.WAITING;

        while (menuItem != MenuItem.EXIT) {
            System.out.print("Please choose option: ");
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Please enter a number!");
            }
            menuItem = MenuItem.findMenuByCode(sc.nextInt());
            switch (menuItem) {
                case WAITING:
                    break;
                case EAT_MEMORY:
                    createObjects();
                    break;
                case CLEANUP_MEMORY:
                    removeObjects();
                    break;
                case CALL_GC:
                    System.out.println("GC is working...");
                    System.gc();
                    System.out.println("Size objects - " + objects.size());
                    break;
                case EXIT:
                    break;
            }
        }
        sc.close();
    }

    private static void createObjects() {
        System.out.println("Creating objects...");
        for (int i = 0; i < 2; i++) {
            objects.add(new byte[10*1024]);
        }
        System.out.println("Size objects - " + objects.size());
    }

    private static void removeObjects() {
        System.out.println("Removing objects...");
        int start = objects.size() - 1;
        int end = start - 2;
        for (int i = start; ((i >= 0) && (i > end)); i--) {
            objects.remove(i);
        }
        System.out.println("Size objects - " + objects.size());
    }

    private enum MenuItem{
        WAITING(0, "Menu:"),
        EAT_MEMORY(1, "1. Eat memory"),
        CLEANUP_MEMORY(2, "2. Clean up memory"),
        CALL_GC(3, "3. Call GC"),
        EXIT(4, "4. Exit");

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
