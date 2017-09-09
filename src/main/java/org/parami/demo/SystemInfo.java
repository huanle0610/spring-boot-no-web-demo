package org.parami.demo;


import org.slf4j.LoggerFactory;

import java.io.File;

public class SystemInfo {
    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(SystemInfo.class);

    public static String humanReadableByteCount(final long bytes) {
        return humanReadableByteCount(bytes,true);
    }

    public static String humanReadableByteCount(final long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public void getInfo()
    {
          /* Total number of processors or cores available to the JVM */
        logger.info("Available processors (cores): " +
                Runtime.getRuntime().availableProcessors());

    /* Total amount of free memory available to the JVM */
        logger.info("Free memory : " +
                humanReadableByteCount(Runtime.getRuntime().freeMemory()));

    /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory();
    /* Maximum amount of memory the JVM will attempt to use */
        logger.info("Maximum memory : " +
                (maxMemory == Long.MAX_VALUE ? "no limit" : humanReadableByteCount(maxMemory)));

    /* Total memory currently available to the JVM */
        logger.info("Total memory available to JVM : " +
                humanReadableByteCount(Runtime.getRuntime().totalMemory()));

    /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

    /* For each filesystem root, print some info */
        for (File root : roots) {
            logger.info("File system root: {} Total space: {} Free space :{} Usable space :{}",
                    root.getAbsolutePath(),
                    humanReadableByteCount(root.getTotalSpace()),
                    humanReadableByteCount(root.getFreeSpace()),
                    humanReadableByteCount(root.getUsableSpace())
            );
        }
    }
}
