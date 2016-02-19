package org.collectd.extension;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.EnumMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.collectd.mx.MBeanSender;

/**
 * Collector for JVM thread states.
 */
public class ThreadStateCollector implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ThreadStateCollector.class.getName());

    private MBeanSender sender;

    public void setSender(MBeanSender sender) {
        this.sender = sender;
    }

    private static final String STATE_NATIVE = "NATIVE";
    private static final String STATE_SUSPENDED = "SUSPENDED";
    private final EnumMap<Thread.State, AtomicLong> times = new EnumMap<>(Thread.State.class);
    private final Map<String, AtomicInteger> states = new TreeMap<>();

    private void reset() {
        for (final Thread.State s : Thread.State.values()) {
            states.put(s.name(), new AtomicInteger(0));
            times.put(s, new AtomicLong(0));
        }
        states.put(STATE_NATIVE, new AtomicInteger(0));
        states.put(STATE_SUSPENDED, new AtomicInteger(0));
    }

    private void collectThreadStates(final ThreadMXBean threadMXBean) throws IOException {
        reset();

        for (final ThreadInfo ti : threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds())) {
            final Thread.State state = ti.getThreadState();
            final String key;
            if (ti.isInNative()) {
                key = STATE_NATIVE;
            } else if (ti.isSuspended()) {
                key = STATE_SUSPENDED;
            } else {
                key = state.name();
            }
            states.get(key).incrementAndGet();

            final long blockedTime = ti.getBlockedTime();
            final long waitedTime = ti.getWaitedTime();

            if (blockedTime != -1) {
                times.get(Thread.State.BLOCKED).addAndGet(blockedTime);
            }

            if (waitedTime != -1) {
                final Thread.State waitedType = state == Thread.State.TIMED_WAITING ? Thread.State.TIMED_WAITING : Thread.State.WAITING;
                times.get(waitedType).addAndGet(waitedTime);
            }
        }
    }

    @Override
    public void run() {
        try {
            final ThreadMXBean threadMXBean = ManagementFactory.newPlatformMXBeanProxy(sender.getMBeanServerConnection(), ManagementFactory.THREAD_MXBEAN_NAME, ThreadMXBean.class);
            
            collectThreadStates(threadMXBean);
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "unable to get stats", ex);
        }
    }
}
