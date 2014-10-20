package com.epam.jmp.memory;

import org.apache.log4j.Logger;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siarhei_Komlik on 10/20/14.
 */
public class MemoryEater {

    private static final Logger logger = Logger.getLogger(MemoryEater.class);

    public static void main(String[] args) {
        List<SoftReference<byte[]>> softByteArrayList = new ArrayList<SoftReference<byte []>>();
        ReferenceQueue<byte[]> refQueue = new ReferenceQueue<byte[]>();
        while (true) {
            byte b[] = new byte[1048576];
            softByteArrayList.add(new SoftReference<byte[]>(b,refQueue));
            Runtime rt = Runtime.getRuntime();
            for (Reference ref = refQueue.poll(); ref != null; ref = refQueue.poll()) {
                softByteArrayList.remove(ref);
            }
            logger.info("free memory: " + rt.freeMemory());
            logger.info(softByteArrayList.size());
        }
    }

}
