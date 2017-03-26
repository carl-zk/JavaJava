package chapter04;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by hero on 17-3-14.
 */
public class MultiThread {

    public static void main(String[] args) {
        MultiThread.showThreadsInfo(true, true);
    }

    public static void showThreadsInfo(boolean lockedMonitors, boolean lockedSynchronizers) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(lockedMonitors, lockedSynchronizers);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
